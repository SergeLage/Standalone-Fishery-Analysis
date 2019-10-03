package Classes;

import DBAccess.ImportData;
import DBAccess.ImportDataPostgresSQL;
import Public.GPS;
import Public.Limits;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.clusterers.MakeDensityBasedClusterer;

 /**
 * Class that uses VMS location data to create a cluster-based model to sort new location data
 * @author Serge Lage
 */
class ProcessNewArea {

	
	/**
	 * Model object
	 */
	MakeDensityBasedClusterer density;
	
	/**
	 * Vessel identification
	*/
	int vesselId;
	
	/**
	 * Configuration parameter for area model
	 */
	double limit;
	
	/**
	 * SOG limits
	 */
	Limits limits;
	
	/**
	 * number of clusters to use in model
	*/
	int nClusters = 6;
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param limits SOG limits
	 *  @param limit configuration parameter for area model
	 */
	ProcessNewArea(Limits limits, double limit)
	{
		this.limits = limits;
		this.limit = limit;
		this.vesselId = -1;
		this.setCluster();
	}
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param limits SOG limits
	 *  @param limit configuration parameter for area model
	 *  @param vesselId vessel identification
	 */
	ProcessNewArea(int vesselId, Limits limits, double limit){
		this.limit = limit;
		this.limits = limits;
		this.vesselId=vesselId;
		this.setCluster();
	}
	
	/**
	 * Use previous VMS data to create model
	 */
	private void setCluster()
	{
		try {
		    this.density = new MakeDensityBasedClusterer();
		    this.density.setNumClusters(nClusters);
			
			Instances data;
			ImportData dbAcess = new ImportDataPostgresSQL();
			if (this.vesselId == -1) {
				data = dbAcess.GetArea(this.limits, this.vesselId);
			}else {
				data = dbAcess.GetArea(this.limits, this.vesselId);
			}
			
			this.density.buildClusterer(data); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Recebe dados de localização e classifica se embaração esta em actividade em nova area
	 * @param gps new location points 
	 * @return boolean is fishing in new area
	*/
	boolean newData(GPS gps) {
		try {
		
			 double[] val = new double[2];
			 val[0]= gps.GetLatitude();
			 val[1] = gps.GetLongitude();
			Instance test = new DenseInstance(2, val); 
		
			 double[] distibutionI = this.density.distributionForInstance(test);
			 double logDensity;
			 for(int i = 0; i<distibutionI.length;i++) {
				logDensity = -this.density.logDensityForInstance(test);
				 if (this.limit < distibutionI[i] && logDensity <100) {
					 return false;
				 }
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
