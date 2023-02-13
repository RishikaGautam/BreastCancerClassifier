/**
 * Name: Rishika Gautam
 * Period: 1
 * Project: Breast Cancer Classifier
 * Date last updated: 9/22/22
 * 
 * BreastCancerClassify contains the core implementation of the 
 * kNearestNeighbors algorithm to classify cell clumps as malignant
 * or benign. 
 * 
 * It is suggested that you work on the methods in the following order:
 * 	1) calculateDistance - once you finish this, you should see a
 * 	   graph of distances appear!
 * 	2) getAllDistances
 * 	3) findKClosestEntries
 * 	4) classify
 *  5) kNearestNeighbors (use your helpers correctly!)
 *  6) getAccuracy
 */
public class BreastCancerClassify {
	
	public static final Integer K = 5;
    public static final Integer BENIGN = 2;
    public static final Integer MALIGNANT = 4;
	
	/**
	 * calculateDistance computes the distance between the two data
	 * parameters. 
	 */
	public static double calculateDistance(int[] first, int[] second)
	{
		double total = 0;
		//traverses through the elements in the first Array and uses distance formula
		//to compute distance between the elements in the first and second array
		for(int i =1; i <first.length-1; i++) {
			total = total + Math.pow((first[i]-second[i]), 2);
				
		}
		return Math.sqrt(total);
		
	
		
		
	}
	
	/**
	 * getAllDistances creates an array of doubles with the distances
	 * to each training instance. The double[] returned should have the 
	 * same number of instances as trainData. 
	 */
	public static double[] getAllDistances(int[][] trainData, int[] testInstance)
	{
		double[] allDistances = new double[trainData.length];
		
		//traverses through the trainData columns 
		for(int i = 0; i< trainData.length; i++) {
			//traverses through the trainData rows and find uses distance formula
			//to find distance between the two points
			for(int j = 0; j < trainData[i].length;j++) {
				allDistances[i] = calculateDistance(testInstance, trainData[i]);
				
			}
			
		}
		
		return allDistances;
	}
	
	/**
	 * findKClosestEntries finds and returns the indexes of the 
	 * K closest distances in allDistances. Return an array of size K, 
	 * that is filled with the indexes of the closest distances (not
	 * the distances themselves). 
	 * 
	 * Be careful! This method can be tricky.
	 */
	public static int[] findKClosestEntries(double[] allDistances)
	{
		
		int[] kClosestIndexes = new int[K];
		
		//for loop to only find the five values that are the closest entries
		for(int i = 0; i < K; i++) {
			
			int x = 0; 
			if( i == 0) {
				for(int j = 0; j < allDistances.length; j++) {
					if(allDistances[j] < allDistances[x]) {
						x = j;
					}
				}
				
			}else{
				
			
			double low = 0;
			for(int j = 0; j < allDistances.length;j++) {
				double distance = allDistances[j] - allDistances[kClosestIndexes[i-1]];
				
				if((distance > 0) && ((low == 0) || (distance < low))) {
					low = distance;
					x = i;
					
				}
				
				
			}
		}
			
			kClosestIndexes[i] = x;
			
		}	
		
		return kClosestIndexes;
		
	}

	
	/**
	 * classify makes a decision as to whether an instance of testing 
	 * data is BENIGN or MALIGNANT. The function makes this decision based
	 * on the K closest train data instances (whose indexes are stored in 
	 * kClosestIndexes). If more than half of the closest instances are 
	 * malignant, classify the growth as malignant. Otherwise classify
	 * as benign.
	 * 
	 * Return one of the global integer constants defined in this function. 
	 */
	public static int classify(int[][] trainData, int[] kClosestIndexes)
	{
		int two = 0; //benign
		int four = 0; //malignant
		
		//traverses through the KClosesIndexes array (five elements)
		for(int i = 0; i <kClosestIndexes.length; i++) {
			//if the largest p
			if(trainData[kClosestIndexes[i]][trainData[i].length-1] == 2) {
				two++;
			}else if(trainData[kClosestIndexes[i]][trainData[i].length-1] == 4) {
				four++;
			}
			
		}
		if(two>four) {
			return BENIGN;
		}
		
		return MALIGNANT;
		
	}
	
	/**
	 * kNearestNeighbors classifies all the data instances in testData as 
	 * BENIGN or MALIGNANT using the helper functions you wrote and the kNN 
	 * algorithm.
	 * 
	 * For each instance of your test data, use your helper methods to find the
	 * K closest points, and classify your result based on that!
	 * @param trainData: all training instances
	 * @param testData: all testing instances
	 * @return: int array of classifications (BENIGN or MALIGNANT)
	 */
	public static int[] kNearestNeighbors(int[][] trainData, int[][] testData){
		int[] myResults = new int[testData.length];
		
		for(int i = 0; i < testData.length; i++ ) {
			myResults[i] = classify(trainData, findKClosestEntries(getAllDistances(trainData, testData[i])));
		}
		
		return myResults;
	}

	/**
	 * getAccuracy returns a String representing the classification accuracy.
	 *
	 * The return String should be rounded to two decimal places followed by the % symbol.
	 * Examples:
	 * If 4 out of 5 outcomes were correctly predicted, the returned String should be: "80.00%"
	 * If 3 out of 9 outcomes were correctly predicted, the returned String should be: "33.33%"
	 * If 6 out of 9 outcomes were correctly predicted, the returned String should be: "66.67%"
	 * Look up Java's String Formatter to learn how to round a double to two-decimal places.
	 *
	 * This method should work for any data set, given that the classification outcome is always
	 * listed in the last column of the data set.
	 * @param: myResults: The predicted classifcations produced by your KNN model
	 * @param: testData: The original data that contains the true classifications for the test data
	 */
	public static String getAccuracy(int[] myResults, int[][] testData) {
		double correct = 0;
	
		//finding the number of correct results
		for(int i = 0; i <testData.length; i++) {
			
			if(myResults[i] == testData[i][testData[0].length-1]) {
				correct++;
			}
			
		}
		//calculating the percentage of correct results
		double accuracy = (correct/testData.length) * 100.0;
		String output = String.format("%.2f", accuracy) + "%";
		return output;
	}
	
	 
	//DO NOT MODIFY THE MAIN METHOD
	public static void main(String[] args) {

		int[][] trainData = InputHandler.populateData("./datasets/train_data.csv");
		int[][] testData = InputHandler.populateData("./datasets/test_data.csv");
		
		//Display the distances between instances of the train data. 
		//Points in the upper left corner (both benign) or in the bottom
		//right (both malignant) should be darker. 
		Grapher.createGraph(trainData);
		
		int[] myResults = kNearestNeighbors(trainData, testData);
		
		System.out.println("Model Accuracy: " + getAccuracy(myResults, testData));
	}

}
