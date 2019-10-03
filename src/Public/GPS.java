package Public;

/**
 *  Class used to store GPS data
 *  @author Serge Lage
 * 
 */
public class GPS {
	
	 /**
     * Longitude position
     */
	private double longitude;
	
	 /**
     * Latitude position
     */
	private double latitude;
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param  latitude position
	 *  @param  longitude position
	 */
	public GPS(double latitude, double longitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	/** 
	 * Get longitude position
	 *  @return longitude (double)
	 */
	public double GetLongitude() {
		return this.longitude;
	}
	
	/** 
	 * Get latitude position
	 * @return latitude (double)
	 */
	public double GetLatitude() {
		return this.latitude;
	}
	
	/** 
	 * Get longitude position
	 * @return longitude (string)
	 */
	public String GetLongitudeStr() {
		return String.valueOf(this.longitude);
	}
	
	/** 
	 * Get latitude position
	 * @return latitude (string)
	 */
	public String GetLatitudeStr() {
		return String.valueOf(this.latitude);
	}
	
	/** Used to view class data in string 
	 * @return class data in string
	 */
	public String toString() {
		return ("Lat: "+this.latitude+" Lon: "+this.longitude);
	}

}
