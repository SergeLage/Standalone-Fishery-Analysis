package Classes;

import Public.GPS;
import Public.Input;
import Public.Limits;
import Public.Output;

/**
* The Decision Support Alerts library implements an application that get VMS data from a DB 
* to create an internal model and receives new data responding with 
* isFishing and isNewArea informing if this information has been classified as fishing and in a new fishing area 
* 
* @author  Serge Lage
* @version 1.0
* @since   2018-03-31 
*/
public interface SFAInterface {

	/**
	 * Used to process new data and classify vessel operation as isFishing and isNewArea
	 * @param input Input data
	 * @return Output data
	 * @see Public.Output
	 * @see Public.Input
	 */
	Output newData(Input input);

	/**
	 * Used to process new SOG data and classify if vessel is fishing
	 * @param velocity SOG data
	 * @return boolean is fishing
	 */
	boolean isFishing(double velocity);
	
	/**
	 * Used to process new locations data and classify if vessel is fishing in a new area
	 * @param gps location points
	 * @return boolean is fishing in new area
	*/
	boolean isNewArea(GPS gps);
	
	/**
	 * Restart the internal models
	 */
	void restart();
	
	/**
	 * Get SOG limits
	 * @return Limits SOG limits
	 * @see Classes.SFAInterface#GetLimit()
	 */
	Limits GetLimit(); 
	
}
