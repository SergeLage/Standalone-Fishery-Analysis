package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Public.Limits;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

public class ImportDataPostgresSQL implements ImportData {

	 /**
     * BD userName
     */
	static String userName = "postgres";
	
	 /**
     * BD password
     */
	static String password = "1";
	
	 /**
     * BD query to get SOG data
     */
	static String queryStringSog = "SELECT *  FROM VMSRecords ";

	 /**
     * BD query to get Log and Lat data
     */
	static String queryStringGps = "SELECT Lat, Lon  FROM VMSRecords ";
	
	 /**
     * DB url to access database Pescas
     */
	//static String url = "jdbc:postgresql://192.168.99.100:32770/pescas";
	static String url = "jdbc:postgresql://localhost:5432/pescas";
	
	/**
	 * Get SOG data of vessel.
	 * 
	 * @param vesselId Vessel identification(if -1 then return all data from table)
	 * @return ArreyList with all SOG data of requested vessel
	 */
	@Override
	public ArrayList<Double> GetVelocity(int vesselId) {
		String queryString = queryStringSog;
		if(vesselId != -1) {
			queryString = queryString + "where VesselID = "+vesselId;
		}
		
		ArrayList<Double> response = new ArrayList<Double>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url, userName, password);
	        Statement statement = conn.createStatement();
	        ResultSet rs = statement.executeQuery(queryString);
	        while (rs.next()) {
	        	response.add(rs.getDouble("sog"));
	        }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Get Lat and Log data of vessel.
	 * 
	 * @param vesselId Vessel identification(if -1 then return all data from table)
	 * @param limits SOG limits to filter data
	 * @return Instances with all Lat and Log data of requested vessel
	 */
	@Override
	public Instances GetArea(Limits limits, int vesselId) {
		String queryString = queryStringGps;
		if(vesselId != -1) {
			queryString = queryString + "where VesselID = "+vesselId;
		}
		InstanceQuery query;
		try {
			 query = new InstanceQuery();	
			 query.setUsername(userName);
			 query.setPassword(password);
			 queryString = queryString + "and sog > "+ limits.min + "and sog < "+ limits.max;
			 query.setQuery( queryString );
			 return query.retrieveInstances();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	

}
