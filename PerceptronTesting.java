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
                String[] values = reader.readLine().trim().split("\\s+");
                for (int j = 0; j < outputSize; j++) {
                    weights[i][j] = Double.parseDouble(values[j]);
                }
            }
			reader.readLine();

			String[] biasValues = reader.readLine().trim().split("\\s+");
            weightBias = new double[outputSize];
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
		int[] resultValues = new int[outputSize];
		String resultAnswer = "Undecided";
		String[] potentialAnswers = {"A", "B", "C", "D", "E", "J", "K"};
		
		for(int i = 0; i < numPairs; i++){
			int count1s = 0;
			int[] inputArr = getInputArr();
			int[] expectedValues = getExpectedValues();
			String expectedAnswer = getExpectedLetter();
			
			for(int j = 0; j < outputSize; j++){ 
				double yj = calcYj(weightBias[j], weights, inputArr, j);
				resultValues[j] = (int)yj;
			}
			for(int j = 0; j < outputSize; j++){
				if(resultValues[j] == 1){
					resultAnswer = potentialAnswers[j];
					count1s += 1;
				}
			}
			if(count1s == 0 || count1s > 1){
				resultAnswer = "Undecided";
			}
				
			writeToFile(resultValues, resultAnswer, expectedValues, expectedAnswer);
		}

		try{ //Close the file
			reader.close();
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}

		System.out.println("\nTesting has finished. View the results of the perceptron net in the testResults subdirectory!\n");

	}

	private void writeToFile(int[] resultValues, String resultAnswer, int[] expectedValues, String expectedAnswer) {
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile, true));
			writer.write("Actual Output:\n");
			writer.write(resultAnswer + "\n");
			for(int i = 0; i < resultValues.length; i++){
				writer.write(resultValues[i] + " ");
			}
			writer.write("\n");
			writer.write("Expected Output:\n");
			writer.write(expectedAnswer + "\n");
			for(int i = 0; i < expectedValues.length; i++){
				writer.write(expectedValues[i] + " ");
			}
			writer.write("\n\n");
			
			writer.flush();
			writer.close();
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
	}

	private double calcYj(double weightBias, double[][] weights, int[] inputArr, int j){ //MAKE 2D
		double yIn = weightBias;
		for(int i = 0; i < inputSize; i++){
			yIn += inputArr[i] * weights[i][j];
		}

		if(yIn > theta){
			yIn = 1;
		}else {
			yIn = -1;
		}

		return yIn;
	}

	private int[] getInputArr(){ //Possibly improve exception handling
		int[] inputArr = new int[inputSize];
		try{
			int readIn = 0;
			reader.readLine(); //remove blank line
			while(readIn < inputSize){
				String currLine = reader.readLine();
				String[] inputs = currLine.split(" ");
				for(String input: inputs){
					inputArr[readIn] = Integer.parseInt(input);
					readIn++;
				}
			}
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
		
		return inputArr;
	}

	private String getExpectedLetter() {
		String expectedLetter = "";
		try {
			expectedLetter = reader.readLine();
		} catch(Exception e){
			System.out.println("ERROR: " + e);
		}

		return expectedLetter;
	}
	private int[] getExpectedValues(){ //Possibly improve exception handling
		int[] expected = new int[outputSize];
		try{
			reader.readLine(); // skip blank line
			String expectedStr = reader.readLine();
			String[] expectedArr = expectedStr.split(" ");
			for(int i = 0; i < outputSize; i++){
				expected[i] = Integer.parseInt(expectedArr[i]);
			}
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}

		return expected;
	}
}
