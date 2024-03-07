/*
Authors: Evan Scott, Kieran Kennedy, Sean Pala
Last Date Modified: 3/7/24
Description: PerceptronTraining handles the training for the perceptron net
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Random;

public class PerceptronTraining {
	int weight, epoch, inputDimension, outputDimension, numPairs;
	BufferedReader reader;
	double learningRate, theta, threshold;
	String readFile, writeFile;
	
	/*
	Description: constructor which initializes and gets necessary starter values from param file reading
	PARAMS: readFile: String (filename storing data for training)
			weight: int
			epoch: int
			writeFile: String (filename for trained weights to be written to after training)
			learningRate: double
			theta: double
			threshhold: double
	RETURN: None
	*/
	public PerceptronTraining(String readFile, int weight, int epoch, String writeFile, double learningRate, double theta, double threshold) {
		this.readFile = readFile;
		this.weight = weight;
		this.epoch = epoch;
		this.writeFile = writeFile;
		this.learningRate = learningRate;
		this.theta = theta;
		this.threshold = threshold;

		// get initial base values from file
		try{
			reader = new BufferedReader(new FileReader(readFile));
			inputDimension = Integer.parseInt(reader.readLine());
			outputDimension = Integer.parseInt(reader.readLine());
			numPairs = Integer.parseInt(reader.readLine());
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}

	}

	/*
	Description: implements algorithm for training the perceptron net. Calls a number of helper methods for one task per function methodology.
	PARAMS: None
	RETURN: None
	*/
	public void Train() {
		double[][] weights = initializeWeights();
		double[] weightBias = initializeWeightBias();

		int currEpoch = 0;
		boolean epochConverged = false, currEpochConvergence = true;
		while(!epochConverged && currEpoch < epoch){
			for(int i = 0; i < numPairs; i++){
				int[] inputArr = getInputArr();
				int[] expected = getExpected();
				
				for(int j = 0; j < outputDimension; j++){
					double yj = calcYj(weightBias[j], weights, inputArr, j);
					if(Math.abs(yj - expected[j]) > threshold){
						updateWeights(inputArr, weights, weightBias, expected[j], j);
						currEpochConvergence = false;
					}
				}
			}

			// close the file
			try{ 
				reader.close();
			}catch(Exception e){
				System.out.println("ERROR: " + e);
			}

			if(currEpochConvergence){
				epochConverged = true;
			}else{
				// reopen the file to reset reading progress
				try{
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
		
		writeToFile(weights, weightBias);
	}

	/*
	Description: writes the trained weights of perceptron net after convergence out to specified file.
	PARAMS: weights: double[][] (2D array storing the trained weights)
		    weightBias: double[] (array of bias values for output units)
	RETURN: None
	*/
	private void writeToFile(double[][] weights, double[] weightBias){
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
			writer.write(inputDimension + "\n");
			writer.write(outputDimension + "\n");
			writer.write(theta + "\n\n");
			for(int i = 0; i < weights.length; i++){
				for(int j = 0; j < weights[i].length; j++){
					writer.write(weights[i][j] + " ");
				}
				writer.write("\n");
			}
			writer.write("\n");
			for(int i = 0; i < weightBias.length; i++){
				writer.write(weightBias[i] + " ");
			}
			writer.flush();
			writer.close();
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
	}

	/*
	Description: updates biases and weights
	PARAMS: inputArr: int[] (array storing testing data by line)
		    weights: double[][] (2D array storing the trained weights)
		    weightBias: double[] (array of bias values for output units)
			t: int
			j: int
	RETURN: None
	*/
	private void updateWeights(int[] inputArr, double[][] weights, double[] weightBias, int t, int j){
		weightBias[j] += learningRate*t;
		for(int i = 0; i < inputDimension; i++){
			weights[i][j] += learningRate*t*inputArr[i];
		}
	}

	/*
	Description: computes activation of each output unit.
	PARAMS: weightBias: double (bias value calculated from training)
		    weights: double[][] (2D array storing the trained weights)
			inputArr: int[] (array storing testing data by line)
			j: int
	RETURN: double - activation
	*/
	private double calcYj(double weightBias, double[][] weights, int[] inputArr, int j){ 
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

	/*
	Description: initializes the training weights for the 2D array randomly between 0-0.5
	PARAMS: None
	RETURN: double[][] - 2D array which randomized weights between 0-0.5
	*/
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

	/*
	Description: initializes the output units bias randomly between 0-0.5
	PARAMS: None
	RETURN: double[] - array containing randomized biases between 0-0.5
	*/
	private double[] initializeWeightBias(){
		double[] weightBias = new double[outputDimension];
		if(weight == 1){
			for(int i = 0; i < outputDimension; i++){
				Random rand = new Random();
				weightBias[i] = 0.5 * rand.nextDouble(); //Random number between 0-0.5
				if(rand.nextInt(2) == 0){
					weightBias[i] *= -1;
				}
			}
		}

		return weightBias;
	}

	/*
	Description: retrieves the data in testing file line by line
	PARAMS: None
	RETURN: int[] - array containing testing data
	*/
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

	/*
	Description: passes the correct output unit values and letter representation from data file
	PARAMS: None
	RETURN: int[] - array containing correct output units
	*/
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
