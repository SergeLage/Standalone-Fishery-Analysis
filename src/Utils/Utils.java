
package Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *  Class used to help with debug and mat utilities
 *  @author Serge Lage
 * 
 */
public class Utils {
	
	public  <K, V> void printMap(Map<K, V> map, int vesselId) {
	        PrintWriter pw;
	        String path = "docs\\"+vesselId+".csv";//this.vesselId
			try {
				pw = new PrintWriter(new File(path));
				StringBuilder sb = new StringBuilder();
		        sb.append("Key");
		        sb.append(';');
		        sb.append("Value");
		        sb.append('\n');
				for (Map.Entry<K, V> entry : map.entrySet()) {
					System.out.println("Key : " + entry.getKey()
						+ " Value : " + entry.getValue());
			        sb.append(entry.getKey());
			        sb.append(';');
			        sb.append(entry.getValue());
			        sb.append('\n');
				}
		        pw.write(sb.toString());
		        pw.close();
		        System.out.println("done!");
		        
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
	    }
	
	public static double round(double value, int places) {
		    if (places < 0) throw new IllegalArgumentException();
		    long factor = (long) Math.pow(10, places);
		    value = value * factor;
		    long tmp = Math.round(value);
		    return (double) tmp / factor;
		}
	  
	public void SaveString(String msg, String title) {
		  String path = "docs\\"+title+".txt";
		  try {
			PrintWriter pw = new PrintWriter(path,  "UTF-8");
			pw.print(msg);
			pw.close();
		} catch (Exception e) {
		}
	     
	  }
	  
	public void SaveList(double[] means, double[] weights, int vesselId) {
		        PrintWriter pw;
		        String path = "docs\\Kernel_"+vesselId+".csv";//this.vesselId
				try {
					pw = new PrintWriter(new File(path));
					StringBuilder sb = new StringBuilder();
			        sb.append("Key");
			        sb.append(';');
			        sb.append("Value");
			        sb.append('\n');
					for (int i = 0; i < means.length; i++) {
				        sb.append(means[i]);
				        sb.append(';');
				        sb.append((int)weights[i]);
				        sb.append('\n');
					}
			        pw.write(sb.toString());
			        pw.close();
			        System.out.println("done!");
			        
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		    }

	public double calculateAverage(ArrayList<Double> marks) {
		Double sum = 0.0;
		  if(!marks.isEmpty()) {
		    for (Double mark : marks) {
		        sum += mark;
		    }
		    return sum / marks.size();
		  }
		  return sum;
		}
		
	public double calculateStdDev(ArrayList<Double> values)
	{
	    double ret = 0;
	    if (values.size() > 0)
	    {  
	        double avg = calculateAverage(values);
	        double sum = 0.0;
	        for (Iterator<Double> i = values.iterator(); i.hasNext();) {
	        	Double item0 = i.next();
	        	sum+=Math.pow(item0-avg, 2);
	        }
	        ret = Math.sqrt((sum) / (values.size() - 1));
	    }
	    return ret;
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}
		return (dist);
	}
	
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}
}