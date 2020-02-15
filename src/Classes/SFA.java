package Classes;

import Public.GPS;
import Public.Input;
import Public.Limits;
import Public.Output;

/**
* The Standalone Fishery Analysis library implements an application that get VMS data from a DB 
* to create an internal model and receives new data responding with 
* isFishing and isNewArea informing if this information has been classified as fishing and in a new fishing area 
* 
* @author  Serge Lage
* @version 1.0
* @since   2018-03-31 
*/

public class SFA implements SFAInterface {

	/**
	 * ProcessVelocity object
	 * @see ProcessVelocity
	 */
	private ProcessVelocity processVelocity;
	
	/**
	 * ProcessNewArea object
	 * @see ProcessNewArea
	 */
	private ProcessNewArea processNewArea;
	
	/** 
	 * Configuration parameter for velocity model
	 */
	private double limitVelocity; 
	
	/**
	 * Configuration parameter for area model
	 */
	private double limitArea;
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  @param limitVelocity configuration parameter for velocity model
	 *  @param limitArea configuration parameter for area model
	 */
	public SFA(double limitVelocity, double limitArea) {
		this.limitArea = limitArea;
		this.limitVelocity = limitVelocity;
		limitVelocity = checkLimit(limitVelocity);
		this.processVelocity = new ProcessVelocity(limitVelocity);
		this.processNewArea = new ProcessNewArea(this.processVelocity.getLimit(), limitArea);
	}
	
	/** 
	 * Constructor that initializes the class members with the received parameters
	 *  Used for debug
	 *  @param limitVelocity configuration parameter for velocity model
	 *  @param limitArea configuration parameter for area model
	 *  @param vesselId vessel identification to use as test
	 */
	public SFA(int vesselId, double limitVelocity, double limitArea)
	{
		this.limitArea = limitArea;
		this.limitVelocity = limitVelocity;
		limitVelocity = checkLimit(limitVelocity);
		this.processVelocity = new ProcessVelocity(vesselId, limitVelocity);
		this.processNewArea = new ProcessNewArea(vesselId, this.processVelocity.getLimit(), checkLimit(limitArea));
	}
	
	@Override
	/**
	 * Used to process new data and classify vessel operation as isFishing and isNewArea
	 * @param input Input data
	 * @return Output data
	 * @see Public.Output
	 * @see Public.Input
	 */
	public Output newData(Input input) {
		Output output = new Output();
		output.isFishing = this.isFishing(input.velocity);
		if (output.isFishing) {
			output.isNewArea = this.isNewArea(input.gps);
		}
		return output;
	}

	@Override
	/**
	 * Used to process new SOG data and classify if vessel is fishing
	 * @param velocity SOG data
	 * @return boolean is fishing
	*/
	public boolean isFishing(double velocity) {
		return this.processVelocity.newData(velocity);
	}

	@Override
	/**
	 * Used to process new locations data and classify if vessel is fishing in a new area
	 * @param gps location points
	 * @return boolean is fishing in new area
	*/
	public boolean isNewArea(GPS gps) {
		return this.processNewArea.newData( gps);
	}

	/**
	 * Restart the internal models
	 */
	public void restart() {
		this.processVelocity = new ProcessVelocity(this.limitVelocity);
		this.processNewArea = new ProcessNewArea(this.processVelocity.getLimit(), this.limitArea);
	}
	
	/**
	 * Get SOG limits
	 * @return Limits SOG limits
	 * @see Classes.SFAInterface#GetLimit()
	 */
	public Limits GetLimit() {
		return this.processVelocity.getLimit();
	}
	
	/**
	 * Ensure that variable is between 0 and 0.9
	 * @param limit double to check value
	 * @return double variable
	 */
	private double checkLimit( double limit)
	{
		if (limit<0)
		{
			return 0; 
		}
		if (limit>0.9)
		{
			return 1    ; 
		}
		return limit;
	}
}
