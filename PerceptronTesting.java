import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class PerceptronTesting {
	String readWeightsFile, readDataFile, writeFile;
	BufferedReader reader;
	double theta;
	int inputSize, outputSize, numPairs;
	double[][] weights = null;
	double[] weightBias = null;	
	
	public PerceptronTesting(String readWeightsFile, String readDataFile, String writeFile) {
		this.readWeightsFile = readWeightsFile;
		this.readDataFile = readDataFile;
		this.writeFile = writeFile;

		// read through weights and data file and store the 6 variables: theta, weights, bias, numpairs, input/output dimensions
		// get initial base values from file
		try{
			reader = new BufferedReader(new FileReader(readWeightsFile));
			inputSize = Integer.parseInt(reader.readLine());
			outputSize = Integer.parseInt(reader.readLine());
			theta = Double.parseDouble(reader.readLine());

			reader.readLine();

			weights = new double[inputSize][outputSize];
			for (int i = 0; i < inputSize; i++) {
                String[] values = br.readLine().trim().split("\\s+");
                for (int j = 0; j < outputSize; j++) {
                    weights[i][j] = Double.parseDouble(values[j]);
                }
            }
			reader.readLine();

			String[] biasValues = br.readLine().trim().split("\\s+");
            weightBias = new double[ouputSize];
            for (int i = 0; i < biasValues.length; i++) {
                weightBias[i] = Double.parseDouble(biasValues[i]);
            }

			reader.close();
			reader = new BufferedReader(new FileReader(readDataFile));
			// already have input/output dimensions saved so pass these in the data file
			reader.readLine();
			reader.readLine();
			numPairs = Integer.parseInt(reader.readLine());

		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
	}
	
	public void Test(){
		// read in the testing data file which has the nxm matrix and the actual result/letter
	}
}
