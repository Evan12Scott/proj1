import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

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

		// get initial base values from file
		try{ //Possibly improve exception handling
			reader = new BufferedReader(new FileReader(readFile));
			inputDimension = Integer.parseInt(reader.readLine());
			outputDimension = Integer.parseInt(reader.readLine());
			numPairs = Integer.parseInt(reader.readLine());
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}

	}

	public void Train() {
		double[][] weights = initializeWeights();
		double[] weightBias = new double[outputDimension];

		if(weight == 1){ //possibly move to function
			for(int i = 0; i < outputDimension; i++){
				Random rand = new Random();
				weightBias[i] = 0.5 * rand.nextDouble(); //Random number between 0-0.5
				if(rand.nextInt(2) == 0){
					weightBias[i] *= -1;
				}
			}
		}

		int currEpoch = 0;
		boolean epochConverged = false, currEpochConvergence = true;
		while(!epochConverged && currEpoch < epoch){
			for(int i = 0; i < numPairs; i++){
				int[] inputArr = getInputArr();
				int[] expected = getExpected();
				
				for(int j = 0; j < outputDimension; j++){ //Might need to do something about two 1s
					double yj = calcYj(weightBias[j], weights, inputArr, j);
					if(Math.abs(yj - expected[j]) > threshold){
						updateWeights(inputArr, weights, weightBias, expected[j], j);
						currEpochConvergence = false;
					}
				}
			}

			try{ //Close the file
				reader.close();
			}catch(Exception e){
				System.out.println("ERROR: " + e);
			}

			if(currEpochConvergence){
				epochConverged = true;
			}else{
				try{ //Reopen the file to reset reading progress
					reader = new BufferedReader(new FileReader(readFile));
					for(int p = 0; p < 3; p++){
						reader.readLine();
					}
				}catch(Exception e){
					System.out.println("ERROR: " + e);
				}
			}
			currEpochConvergence = true;
			currEpoch++;
		}
		
		if(epochConverged){
			System.out.println("Training converged after " + currEpoch + " epochs.");
		}

	}


	private void updateWeights(int[] inputArr, double[][] weights, double[] weightBias, int t, int j){
		weightBias[j] += learningRate*t;
		for(int i = 0; i < inputDimension; i++){
			weights[i][j] += learningRate*t*inputArr[i];
		}
	}


	private double calcYj(double weightBias, double[][] weights, int[] inputArr, int j){ //MAKE 2D
		double yIn = weightBias;
		for(int i = 0; i < inputDimension; i++){
			yIn += inputArr[i] * weights[i][j];
		}

		if(yIn > theta){
			yIn = 1;
		}else if(yIn < -1 * theta){
			yIn = -1;
		}else{
			yIn = 0;
		}

		return yIn;
	}


	private double[][] initializeWeights(){
		double[][] weights = new double[inputDimension][outputDimension];
		if(weight == 1){
			Random rand = new Random();
			for(int i = 0; i < inputDimension; i++){
				for(int j = 0; j < outputDimension; j++){
					double weightRand = 0.5 * rand.nextDouble(); //Random number between 0-0.5
					if(rand.nextInt(2) == 0){
						weightRand *= -1;
					}
					weights[i][j] = weightRand;
				}
			}
		}

		return weights;
	}


	private int[] getInputArr(){ //Possibly improve exception handling
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


	private int[] getExpected(){ //Possibly improve exception handling
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
