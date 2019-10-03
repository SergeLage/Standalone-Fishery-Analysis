import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import Public.Input;

class ConfusionMatrix {
	static List<Input> getInputArray()
	{
		List<Input> inputArray = new ArrayList<Input>();
		inputArray.add(new Input(50, 0.2, 30.15, -40.2 ));
		inputArray.add(new Input(50, 0.5, 30.15, -40.2 ));
		inputArray.add(new Input(50, 1, 30.15, -40.2 ));
		inputArray.add(new Input(50, 2, 38.6175, -27.0108 ));
		inputArray.add(new Input(50, 3, 38.6175, -29.2108 ));
		inputArray.add(new Input(50, 4, 3.6175, -2.2108 ));
		inputArray.add(new Input(50, 5, 38.6175, -29.2108 ));
		inputArray.add(new Input(50, 6, 30.15, -40.2 ));
		inputArray.add(new Input(50, 7, 30.15, -40.2 ));
		inputArray.add(new Input(50, 8, 35.15, -35.2 ));
		return inputArray;
	}
	
	static int[] getIvesselArray()
	{
		int[] vesselArray = new int[10];
		vesselArray[0]=22;
		vesselArray[1]=10;
		vesselArray[2]=2;
		vesselArray[3]=13;
		vesselArray[4]=30;
		vesselArray[5]=5;
		vesselArray[6]=28;
		vesselArray[7]=4;
		vesselArray[8]=18;
		vesselArray[9]=25;
		return vesselArray;
	}

	static Map<Integer, Map<Double, Boolean>> getVelClass()  {
		 Map<Integer, Map<Double, Boolean>> classVess  = new HashMap<Integer, Map<Double, Boolean>>();
		 Map<Double, Boolean> classVess2  = new HashMap<Double, Boolean>();
		 //vessel 2
		 classVess2.put(0.2, false);
		 classVess2.put(0.5, true);
		 classVess2.put(1.0, true);
		 classVess2.put(2.0, true);
		 classVess2.put(3.0, true);
		 classVess2.put(4.0, false);
		 classVess2.put(5.0, false);
		 classVess2.put(6.0, false);
		 classVess2.put(7.0, false);
		 classVess2.put(8.0, false);
		 classVess.put(2, classVess2);
		 //vessel 4
		 Map<Double, Boolean> classVess4  = new HashMap<Double, Boolean>();
		 classVess4.put(0.2, false);
		 classVess4.put(0.5, true);
		 classVess4.put(1.0, true);
		 classVess4.put(2.0, true);
		 classVess4.put(3.0, false);
		 classVess4.put(4.0, false);
		 classVess4.put(5.0, false);
		 classVess4.put(6.0, false);
		 classVess4.put(7.0, false);
		 classVess4.put(8.0, false);
		 classVess.put(4, classVess4);
		 //vessel 5
		 Map<Double, Boolean> classVess5  = new HashMap<Double, Boolean>();
		 classVess5.put(0.2, false);
		 classVess5.put(0.5, false);
		 classVess5.put(1.0, false);
		 classVess5.put(2.0, false);
		 classVess5.put(3.0, true);
		 classVess5.put(4.0, false);
		 classVess5.put(5.0, false);
		 classVess5.put(6.0, false);
		 classVess5.put(7.0, false);
		 classVess5.put(8.0, false);
		 classVess.put(5, classVess5);
		 //vessel 10
		 Map<Double, Boolean> classVess10  = new HashMap<Double, Boolean>();
		 classVess10.put(0.2, false);
		 classVess10.put(0.5, false);
		 classVess10.put(1.0, false);
		 classVess10.put(2.0, true);
		 classVess10.put(3.0, true);
		 classVess10.put(4.0, true);
		 classVess10.put(5.0, false);
		 classVess10.put(6.0, false);
		 classVess10.put(7.0, false);
		 classVess10.put(8.0, false);
		 classVess.put(10, classVess10);
		 //vessel 13
		 Map<Double, Boolean> classVess13  = new HashMap<Double, Boolean>();
		 classVess13.put(0.2, true);
		 classVess13.put(0.5, true);
		 classVess13.put(1.0, true);
		 classVess13.put(2.0, true);
		 classVess13.put(3.0, false);
		 classVess13.put(4.0, false);
		 classVess13.put(5.0, false);
		 classVess13.put(6.0, false);
		 classVess13.put(7.0, false);
		 classVess13.put(8.0, false);
		 classVess.put(13, classVess13);
		 //vessel 18
		 Map<Double, Boolean> classVess18  = new HashMap<Double, Boolean>();
		 classVess18.put(0.2, false);
		 classVess18.put(0.5, false);
		 classVess18.put(1.0, false);
		 classVess18.put(2.0, true);
		 classVess18.put(3.0, true);
		 classVess18.put(4.0, true);
		 classVess18.put(5.0, false);
		 classVess18.put(6.0, false);
		 classVess18.put(7.0, false);
		 classVess18.put(8.0, false);
		 classVess.put(18, classVess18);
		 //vessel 22
		 Map<Double, Boolean> classVess22  = new HashMap<Double, Boolean>();
		 classVess22.put(0.2, false);
		 classVess22.put(0.5, false);
		 classVess22.put(1.0, false);
		 classVess22.put(2.0, true);
		 classVess22.put(3.0, true);
		 classVess22.put(4.0, false);
		 classVess22.put(5.0, false);
		 classVess22.put(6.0, false);
		 classVess22.put(7.0, false);
		 classVess22.put(8.0, false);
		 classVess.put(22, classVess22);
		 //vessel 25
		 Map<Double, Boolean> classVess25  = new HashMap<Double, Boolean>();
		 classVess25.put(0.2, false);
		 classVess25.put(0.5, false);
		 classVess25.put(1.0, false);
		 classVess25.put(2.0, true);
		 classVess25.put(3.0, true);
		 classVess25.put(4.0, true);
		 classVess25.put(5.0, false);
		 classVess25.put(6.0, false);
		 classVess25.put(7.0, false);
		 classVess25.put(8.0, false);
		 classVess.put(25, classVess25);
		 //vessel 28
		 Map<Double, Boolean> classVess28  = new HashMap<Double, Boolean>();
		 classVess28.put(0.2, false);
		 classVess28.put(0.5, true);
		 classVess28.put(1.0, true);
		 classVess28.put(2.0, true);
		 classVess28.put(3.0, false);
		 classVess28.put(4.0, false);
		 classVess28.put(5.0, false);
		 classVess28.put(6.0, false);
		 classVess28.put(7.0, false);
		 classVess28.put(8.0, false);
		 classVess.put(28, classVess28);
		 //vessel 30
		 Map<Double, Boolean> classVess30  = new HashMap<Double, Boolean>();
		 classVess30.put(0.2, false);
		 classVess30.put(0.5, true);
		 classVess30.put(1.0, true);
		 classVess30.put(2.0, true);
		 classVess30.put(3.0, true);
		 classVess30.put(4.0, false);
		 classVess30.put(5.0, false);
		 classVess30.put(6.0, false);
		 classVess30.put(7.0, false);
		 classVess30.put(8.0, false);
		 classVess.put(30, classVess30);
		return (classVess);
	}

	static int[][] getConfusionMatrix(Map<Double, Boolean> classes, Map<Double, Boolean> classefied)
	{
		//TP FN FN= P class as N
		//FP TN PF= N class as P
		int[][] confusionMatrix = new int[2][2];
		for(Map.Entry<Double, Boolean> entry : classes.entrySet()) {
			Double velocity = entry.getKey();
			Boolean classesValue = entry.getValue();
			Boolean classefiedValue = classefied.get(velocity);
			if (classesValue )
			{
				if (classefiedValue )
				{
					confusionMatrix[0][0] ++;
				}else {
					confusionMatrix[0][1] ++;
				}
			}else {
				if (classefiedValue )
				{
					confusionMatrix[1][0] ++;
				}else {
					confusionMatrix[1][1] ++;
				}
			}
		}
		
		for (int i = 0; i < confusionMatrix.length; i++) {
		    for (int j = 0; j < confusionMatrix[i].length; j++) {
		        System.out.print(confusionMatrix[i][j] + " ");
		    }
		    System.out.println();
		}
		return confusionMatrix;
	}

}
