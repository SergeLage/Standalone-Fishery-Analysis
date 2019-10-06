import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Public.Input;
import Utils.Log;

class ConfusionMatrix {

	static void evaluateConfusionMatrix(int[][] confusionMatrix)
	{
		 Log.debugConsole("\nConfusionMatrix ");
		for (int i = 0; i < confusionMatrix.length; i++) {
		    for (int j = 0; j < confusionMatrix[i].length; j++) {
		        Log.debugConsole(confusionMatrix[i][j] + " ", false);
		    }
		    Log.debugConsole(" ");
		}
		getPrecision(confusionMatrix);
		getRecall(confusionMatrix);
	}
	
	private static void getPrecision(int[][] confusionMatrix)//pt / tp+fp 
	{ 
		 Log.debugConsole("\nPrecision ");
		Log.debugConsole("0 "+confusionMatrix[0][0] / 500.); //fishing
		Log.debugConsole("1 "+ confusionMatrix[1][1] / 500.); // no fishing
		Log.debugConsole("2 "+confusionMatrix[2][2] / 500.); // fishing in new area	
	}
	
	private static void getRecall(int[][] confusionMatrix)// tp / tp+fn 
	{
		Log.debugConsole("\nRecall ");
		Log.debugConsole("0 "+confusionMatrix[0][0] / (confusionMatrix[0][0] +confusionMatrix[1][0]+confusionMatrix[2][0] +0.0)); //fishing
		Log.debugConsole("1 "+confusionMatrix[1][1] / (confusionMatrix[0][1] +confusionMatrix[1][1]+confusionMatrix[2][1] +0.0)); //fishing
		Log.debugConsole("2 "+confusionMatrix[2][2] / (confusionMatrix[0][2] +confusionMatrix[1][2]+confusionMatrix[2][2]+0.0 )); //fishing
		
	}
	
	
	
	static List<TestData> getInputFromFile()
	{
		List<TestData> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\serge\\Documents\\ISEL\\Tese\\Dados\\relatorios\\Input.csv"))) {
		    String line;
		    boolean header = true;
		    while ((line = br.readLine()) != null) {
		    	if(header)
		    	{
		    		header= false;
		    		continue;
		    	}
		        String[] values = line.split(";");
		        records.add(new TestData(values));
		        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return records;	
	}
}

class TestData{
	 public Input input;
	 
	 public int cl;
	
	 public TestData(String[] values)
	 {
		 try {
		 this.input = new Input(0, Double.parseDouble(values[0]),Double.parseDouble(values[1]),Double.parseDouble(values[2]));
		 this.cl = Integer.parseInt( values[3]);
		 } catch (Exception e) {
		}
	 }
}
