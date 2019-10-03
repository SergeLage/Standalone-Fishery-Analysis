package Public;

/**
 *  Class used to store SOG limits of vessel
 *  @author Serge Lage
 * 
 */
public class Limits { 
	
	 /**
     * minimum SOG
     */
	  public double min; 
	  
	 /**
     * Maximum SOG
     */
	  public double max; 
	  
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param min minimum SOG
	 *  @param max maximum SOG
	 */
	  public Limits(double min, double max) { 
	    this.min = min; 
	    this.max = max; 
	  } 
	} 
