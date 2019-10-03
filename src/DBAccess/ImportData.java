package DBAccess;

import java.util.ArrayList;

import Public.Limits;
import weka.core.Instances;

/**
*  Interface used to access VMS data
*  
* @author  Serge Lage
* @version 1.0
* @since   2018-03-31 
*/

public interface ImportData {
	public ArrayList<Double> GetVelocity(int vesselId);
	public Instances GetArea(Limits limits, int vesselId) ;

}
