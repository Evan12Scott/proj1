import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PerceptronTesting {
	String readWeightsFile, readDataFile, writeFile;
	BufferedReader reader;	
	
	public PerceptronTesting(String readWeightsFile, String readDataFile, String writeFile) {
		this.readWeightsFile = readWeightsFile;
		this.readDataFile = readDataFile;
		this.writeFile = writeFile;

		// read through weights and data file and store the 6 variables: theta, weights, bias, numpairs, input/output dimensions
		// get initial base values from file
	}
	
	public void Test(){
		// read in the testing data file which has the nxm matrix and the actual result/letter
	}
}
