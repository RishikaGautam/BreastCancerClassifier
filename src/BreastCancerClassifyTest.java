/**
 * Name: Rishika Gautam
 * Period: 1
 * Project: Breast Cancer Classifier
 * Date last updated: 9/22/22
 * 
 * Use this class for your personal jUnit test suite.
 *
 */
 
import static org.junit.Assert.*;

public class BreastCancerClassifyTest {
  
	
	//test case for different lengths of csv file (empty array) (and long arrays)
	//test case with commas in the files
	//
	
	public void calculateDistanceTest() {
		int[] firstTest = {1234,3,2};
		int[] secondTest = {1212, 14, 4};
		double actDist = 11.0;
		double Test1Result = BreastCancerClassify.calculateDistance(firstTest, secondTest);
		assertEquals("This fails as can not calculate distance in a 1-Dimensional area. Need two or more points in order to calculate distance ", actDist, Test1Result);
		
	}
	
	
	
}
