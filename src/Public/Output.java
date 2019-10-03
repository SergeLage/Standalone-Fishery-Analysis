package Public;


/**
*  Class used to return the result of the application
*  
* @author  Serge Lage
* @version 1.0
* @since   2018-03-31 
*/
 
public class Output {
	 /**
     * The vessel is fishing
     */
	public boolean isFishing;
	
	 /**
     * The vessel is fishing in a new area
     */
	public boolean isNewArea;
	
	/** 
	 * Default constructor that initializes the class members with false
	 */
	public Output()
	{
		this.isFishing = false;
		this.isNewArea = false;
	}
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param isFishing the vessel is fishing.
	 *  @param isNewArea the vessel is fishing in a new area.
	 */
	public Output(boolean isFishing, boolean isNewArea)
	{
		this.isFishing = isFishing;
		this.isNewArea = isNewArea;
	}
}
