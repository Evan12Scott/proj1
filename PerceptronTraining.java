import java.io.BufferedReader;
import java.io.FileReader;

public class PerceptronTraining {
	int weight, epoch, inputDimension, outputDimension, numPairs;
	BufferedReader reader;
	double learningRate, theta, threshold;
	String readFile, writeFile;
		
	public PerceptronTraining(String readFile, int weight, int epoch, String writeFile, double learningRate, double theta, double threshold) {
		this.readFile = readFile;
		this.weight = weight;
		this.epoch = epoch;
		this.writeFile = writeFile;
		this.learningRate = learningRate;
		this.theta = theta;
		this.threshold = threshold;

		try{
			reader = new BufferedReader(new FileReader(readFile)); //CLOSE READER AT SOME POINT
			inputDimension = Integer.parseInt(reader.readLine());
			outputDimension = Integer.parseInt(reader.readLine());
			numPairs = Integer.parseInt(reader.readLine());
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
		
		System.out.println("Input Dimension: " + inputDimension + "\nOutput Dimension: " + outputDimension + "\nNumber of Pairs: " + numPairs); //REMOVE LATER
	}

	// implement main training algorithm and call any necessary helper methods (do 1 thing per method)
	public void Train() {
		int[] inputArr = getInputArr();
		int[] expected = getExpected();
		for(int exp: expected){
			System.out.println(exp);
		}
	}

	private int[] getInputArr(){
		int[] inputArr = new int[inputDimension];
		try{
			int readIn = 0;
			reader.readLine(); //remove blank line
			while(readIn < inputDimension){
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


	private int[] getExpected(){
		int[] expected = new int[outputDimension];
		try{
			reader.readLine(); //remove blank line
			String expectedStr = reader.readLine();
			String[] expectedArr = expectedStr.split(" ");
			reader.readLine(); //remove character representation
			for(int i = 0; i < outputDimension; i++){
				expected[i] = Integer.parseInt(expectedArr[i]);
			}
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}

		return expected;
	}
}
