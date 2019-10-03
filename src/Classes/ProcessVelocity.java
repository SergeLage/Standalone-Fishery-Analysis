package Classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import DBAccess.ImportData;
import DBAccess.ImportDataPostgresSQL;
import Public.Limits;
import Utils.Tuple;
import Utils.Utils;
import weka.estimators.KernelEstimator;

/**
 * Class that uses VMS velocity data (SOG) to create a model for sorting new velocity data
 * @author Serge Lage
 */
class ProcessVelocity {
	
	/**
	 * SOG Limits
	 */
	private Limits limits;
	
	/**
	 * Configuration parameter for velocity model
	 */
    private double limit = 0.1;
    
    /**
     * Defines if in debug mode 
     */
    private boolean debug = false;
    
    /**
     * Dictionary with density of any point of SOG
     */
    private Map<Double, Double> density; 
    
    /**
    * Velocity histogram
     */
	double[] histogram;
	
	/**
	 * Vessel identification
	*/
	int vesselId;
	
	/**
	 * Utils object
	 */
	private Utils utils = new Utils();

	/**
	 * Karnel estimator object
	 */
	KernelEstimator kernel;
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param limit SOG limit
	 */
	ProcessVelocity(double limit){
		this.limit = limit;
		setHistogram();
	}
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param limit SOG limit
	 *  @param vesselId vessel identification
	 */
	ProcessVelocity(int vesselId, double limit){
		this.limit = limit;
		this.limits = new Limits(99, 0); 
		this.vesselId = vesselId;
		setHistogram();
	}
	
	/**
	 * Get defined SOG limits
	 * @return Limits
	 */
	Limits getLimit() {
		return this.limits;
	}
	
	/**
	 * Used to classify new SOG data
	 * @param velocity new SOG data
	 * @return boolean is fishing
	 */
	boolean newData(double velocity) {
		if (this.limits.min<=velocity && this.limits.max>=velocity) 
		{
			return true;
		}
		return false;
	}
	
	//PRIVATE METHODS	
	/**
	 * Used to set SOG limits using previous SOG data
	 * 
	 * Creates a histogram with all the SOG data, find the maximum and minimum value 
	 * of the histogram, applies a karnel distribution in the remain histogram
	 * to define the limits comparing the configuration limit with the distribution
	 */
	private void setHistogram() {		
		density =  new HashMap<Double, Double>();
		double[] limits = new double[2];
		ArrayList<Double> valueList = new ArrayList<Double>();
		ArrayList<Double> valueListFishing = new ArrayList<Double>();
		kernel = new KernelEstimator(0.05);//0.25 //0.05
        int maxGlobal = 0, maxLocal = 10;
        double maxKey = 0.0;
        ImportData dbAcess = new ImportDataPostgresSQL();
        ArrayList<Double> items = dbAcess.GetVelocity(this.vesselId);
        if(debug) { 
        	System.out.println(this.vesselId);
        	System.out.println(items.size());
        }
        Map<Double, Integer> map = new HashMap<Double, Integer>();

        //create histogram
        for (Iterator<Double> i = items.iterator(); i.hasNext();) {
        	Double item0 = i.next();
        	valueList.add(item0);
        	
        	double item = this.utils.round(item0, 1); 
        	
        	if (map.containsKey(item)) 
        	{
        		map.put(item, map.get(item)+1);
        	}else {
        		map.put(item, 1);
        	}
        } 
        Map<Double, Integer> histogram = new TreeMap<Double, Integer>(map);
        Map<Double, Integer> cleanHistogram0 =  new HashMap<Double, Integer>();
        if(debug) { this.utils.printMap(histogram, this.vesselId);}
        for(Map.Entry<Double, Integer> entry : histogram.entrySet()) {
        	Double key = entry.getKey();
            Integer value = entry.getValue();
            if (key < 0.15) {
            	continue;
            }
            if(debug) { System.out.println(key +" -> " +value + " - "+ maxGlobal);}
            if(value >= maxGlobal)
            {
            	maxGlobal = value;
            	maxKey = key;
            	maxLocal = maxGlobal;
            }else {
            	
            	if(value < (maxGlobal*limit)){
            		break;
            	}		
            	if( value > maxLocal)
            	{
        			if(breakHillClimbing(histogram, key,maxGlobal))
        			{
        				break;
        			}
            	}
            	maxLocal = value;
            }
            if(debug) {System.out.println(key +" -> " +value + " IN");}
            cleanHistogram0.put(key, value);
        }
          
        Map<Double, Integer> cleanHistogram =  new TreeMap<Double, Integer>(cleanHistogram0);
        double minValue = maxGlobal*limit;
       
        boolean pass = true;
        for(Map.Entry<Double, Integer> entry : cleanHistogram.entrySet()) {
        	if(debug) {System.out.println("entry.getValue() "+entry.getValue());}
        	if(entry.getValue()>=minValue) {
        		if(debug) {System.out.println("entry.getKey() "+entry.getKey());}
            		
        		if(entry.getKey() < maxKey)
        		{
        			if(pass)
        			{
        				limits[0]=entry.getKey();
        				if(debug) {System.out.println("MIN "+limits[0]);}
        			}
        		}
        		else
        		{
        			if(!pass)
        			{
        				limits[1]=entry.getKey();
        				if(debug) {System.out.println("MAX "+limits[1]);}
        			}
        			else 
        			{
        				if(debug) {System.out.println("break");}
        				break;
        			}
        		}
        		kernel.addValue(entry.getKey(), entry.getValue());
        		if(debug) {System.out.println(entry.getKey());}
	            for (int i = 0; i <= entry.getValue(); i++)
	            {
	                valueListFishing.add(entry.getKey());
	            }
	            pass=false;
        	}else {
        		pass = true;
        	}
        }
        
        double[] means = kernel.getMeans();
        
		 
        Map<Double, Double> density2 =  new HashMap<Double, Double>();

        double total = 0;
        for (double d : means) {
        	if (d != 0.0) {
        		double prob = kernel.getProbability(d);
        		total+=prob;
        		density.put(d, total);
        		density2.put(d, prob);
        	}
		}
        
        //utils.printMap(density2,2 );
        
        double densityA; double velocityA;
		 for(Map.Entry<Double, Double> entry : density.entrySet()) {
			 densityA = entry.getValue(); velocityA = entry.getKey();
			 if(densityA +limit<=1 && densityA-limit>0) {
				 if(this.limits.min > velocityA ) {
					 this.limits.min = velocityA;
				 }
				 if (this.limits.max < velocityA) {
					 this.limits.max = velocityA;
				 }
			 }
		 }
	}

	/**
	 * Used to set and end to the HillClimbing algorithm and define the maximum value
	 * @param histogram velocity histogram
	 * @param key SOG value to test
	 * @param maxValue defined histogram maximum
	 * @return boolean if is new maximum
	 */
	private boolean breakHillClimbing( Map<Double, Integer> histogram, double key, int maxValue) {		
		Tuple nValue1 = getNextKey(histogram,key); 
		Tuple nValue2 = getNextKey(histogram,nValue1.x); 
		Tuple nValue3 = getNextKey(histogram,nValue2.x); 
		//int newValue1 = nValue1.y;
		//int newValue2 = nValue2.y;
		//int newValue3 = nValue3.y;
		/*int newValue1 = histogram.get(key+0.5);
		int newValue2 = histogram.get(key+ 1);
		int newValue3 = histogram.get(key+ 2);*/
		int value = histogram.get(key);
		if(nValue1.y > maxValue || nValue2.y > maxValue || nValue3.y > maxValue) {
			return false;
		}
		if( nValue1.y < value || nValue2.y < value || nValue3.y < value) {
			return false;
		}
		return true;
	}
	
	private Tuple getNextKey( Map<Double, Integer> histogram, double key)
	{
		key = Utils.round(key+0.1,1);
		if(histogram.containsKey(key))
		{
			return new Tuple( key,histogram.get(key));
		}else
		{
			return getNextKey(histogram, key);
		}
	}

	
}
