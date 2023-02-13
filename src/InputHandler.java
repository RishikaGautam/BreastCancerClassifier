
/**
 * Name:Rishika Gautam
 * Period:1
 * Project:Breast Cancer Classifier
 * Date last updated: 9/22/22
 *
 * This class handles reading and writing test data from a file.
 *
 */
 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * InputHandler processes all input files and also prints the accuracy of results.
 */
public class InputHandler
{
     /**
     * Returns a two dimensional int array corresponding to a csv file (defined by filename) of
     * ints.
     */
    public static int[][] populateData(String filename)
    {
    	ArrayList<String> input = new ArrayList<String>();
    	
    	try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			
			String value;
			while((value = br.readLine()) != null) {
				input.add(value);
			}
			
			br.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    	
    	int[][] dataValues;
    	
    	if(input.isEmpty()) {
    		dataValues = new int[0][0];
    	}else {
    		int num = input.size();
    		String[] len = input.get(0).split(",");
    		dataValues = new int[num][len.length];
    		for(int i = 0; i < dataValues.length; i++) {
    			String[]lines = input.get(i).split(",");
    			for(int j = 0; j < dataValues[0].length; j++) {
    				dataValues[i][j] = Integer.parseInt(lines[j]);
    			}
    		}
    		
    	}
    	
    	
    	
        return dataValues;
        
        
    }

}
