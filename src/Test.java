import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Classes.SFA;
import Classes.SFAInterface;
import Public.Input;
import Public.Limits;
import Public.Output;

public class Test {
	
	public static void main(String[] args) {
		//ConfusionMatrix confMatrix = new ConfusionMatrix();
		List<Input> inputArray = ConfusionMatrix.getInputArray();
		//int[] vesselArray = ConfusionMatrix.getIvesselArray();
		//Map<Integer, Map<Double, Boolean>> classesVess = ConfusionMatrix.getVelClass();
		
		System.out.println("Start test SFA");
		
		//Map<Integer, Map<Double, Boolean>> classVess  = new HashMap<Integer, Map<Double, Boolean>>();
		Map<Double, Boolean> classVessID;
		SFAInterface dsa;
		for (int vesselIndex = 2;vesselIndex<3;vesselIndex++)
		{
			int vessalId = vesselIndex;//vesselArray[vesselIndex];
			classVessID  = new HashMap<Double, Boolean>();
			try {
				System.out.println("\nStart test isFishing "+vessalId);
				dsa = new SFA(vessalId, 0.05, 0.1); 
				System.out.println("\nSFA Started ");
				Limits l = dsa.GetLimit();
				dsa.isFishing(2);
				System.out.println(  "limit: "+ l.min + " "+ l.max);
				for (Iterator<Input> i = inputArray.iterator(); i.hasNext();) {
					Input item = i.next();
					System.out.println("\nvelocity "+ item.velocity );
					boolean isFishing = dsa.isFishing(item.velocity);
					boolean isNewArea = dsa.isNewArea(item.gps);
					System.out.println(  "isFishing: "+isFishing );
					System.out.println(  "isNewArea: "+isNewArea );
					Output res = dsa.newData(item);
					classVessID.put(item.velocity, res.isFishing);
					System.out.println(  "isFishing: "+res.isFishing );
					System.out.println(  "isNewArea: "+res.isNewArea );
				}
			}catch(Exception ex) { 
				ex.printStackTrace();
			    System.err.println(ex);
			}
			//classVess.put(vessalId, classVessID);
			//ConfusionMatrix.getConfusionMatrix(classesVess.get(vessalId), classVessID);

		}
		System.out.println("\nEND");
		
		
		
	}
	
	
}
