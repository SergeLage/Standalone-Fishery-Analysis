package Public;

/**
 *  Class used to receive new data
 *  @author Serge Lage
 * 
 */
public class Input {

	 /**
     * Route of the vessel
     */
    public int route ;
    
	 /**
     * Velocity of the vessel (SOG)
     */
    public double velocity ;
    
	 /**
     * GPS coordinates
     */
    public GPS gps ;
    
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param  route of the vessel.
	 *  @param  velocity of the vessel(SOG).
	 *  @param  latitude position
	 *  @param  longitude position
	 */
    public Input(int route, double velocity, double latitude, double longitude)
    {
    	this.route = route;
    	this.velocity = velocity;
    	this.gps = new GPS(latitude, longitude);
    }
    
	/** 
	 * Used to view class data in string 
	 * @return class data in string
	 */
    public String toString() {
		return ("route: "+this.route+" velocity: "+this.velocity + " gps"+this.gps.toString());
	}
}
