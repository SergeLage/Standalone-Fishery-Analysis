import java.util.List;

import Classes.SFA;
import Classes.SFAInterface;
import Public.Limits;
import Public.Output;
import Utils.Log;

public class Test {
	
	public static void main(String[] args) {
		int[][] confusionMatrix = new int[3][3];
		int ok = 0;
		int nok = 0;
		Log.debugConsole("Start test SFA");
		SFAInterface dsa;
		int vessalId = 2;
		try {
			dsa = new SFA(vessalId, 0.0, 0.1);//0.05,0.1 
			Log.debugConsole("\nSFA Started ");
			Limits l = dsa.GetLimit();
			Log.debugConsole(  "limit: "+ l.min + " "+ l.max);
			List<TestData> records  = ConfusionMatrix.getInputFromFile();
			
			for (int i =0;i< records.size(); i++) {
				TestData testData = records.get(i);
				Output out = dsa.newData(testData.input);
				//Log.debugConsole(testData.cl +" "+ testData.input.velocity + " "+out.isFishing);
				int newClass = 1;
				if(out.isFishing)
				{
					newClass = ((out.isNewArea) ? 2 : 0);
				}
				if(testData.cl == newClass)
				{
					ok+=1;
				}else {
					nok+=1;
				}
				//confusionMatrix[testData.cl][newClass]+=1;
				confusionMatrix[newClass][testData.cl]+=1;
			}
		}catch(Exception ex) { 
			ex.printStackTrace();
			Log.errorConsole(ex);
		}
		Log.debugConsole("OK: "+ ok + " NOK: "+nok);
		ConfusionMatrix.evaluateConfusionMatrix(confusionMatrix);
		Log.debugConsole("\nEND");
	}
	
	
}
