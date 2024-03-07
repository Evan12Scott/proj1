/*
Authors: Evan Scott, Kieran Kennedy, Sean Pala
Last Date Modified: 3/7/24
Description: trainingInput handles the retrieval of all necessary input from user that is required for training the perceptron net. Makes use of functions located in validateInput.java to ensure the validity of the input. 
*/

import java.util.Scanner;

public class trainingInput {
        validateInput userInput = new validateInput();
	String readFile, weight, epoch, writeFile, learningRate, theta, threshold;
	
        /*
	Description: prompts user for all necessary information to train the perceptron net
	PARAMS: input: Scanner object
	 	action: String convert to int
	RETURN: None
	*/
	public void promptUser(Scanner input, String action) {
		int typeAction = Integer.parseInt(action);

		do {
			System.out.println("Enter the training data file name and ensure it is located in the TRAININGSETS subdirectory:");
                        readFile = input.nextLine();
		} while(userInput.checkReadFile(readFile, typeAction));

                do {
                        System.out.println("Enter 0 to initialize weights to 0, enter 1 to initialize weights to random values between -0.5 and 0.5:");
                        weight = input.nextLine();
                } while(userInput.validateWeight(weight));

                do {
                        System.out.println("Enter the maximum number of training epochs [1:]:");
                        epoch = input.nextLine();
                } while(userInput.validateEpoch(epoch));

                do {
                        System.out.println("Enter a file name to save the trained weight settings:");
                        writeFile = input.nextLine();
                        } while(userInput.validateWriteWeightsFile(writeFile));

                do {
                        System.out.println("Enter the learning rate alpha from 0 to 1 but not including 0:");
                        learningRate = input.nextLine();
                } while(userInput.validateLearningRate(learningRate));

		do {
                        System.out.println("Enter the threshold theta:");
                        theta = input.nextLine();
                } while(userInput.validateTheta(theta));

                do {
                        System.out.println("Enter the threshold to be used for measuring weight changes:");
                        threshold = input.nextLine();
                } while(userInput.validateThreshold(threshold));

                // valid inputs so converting from Strings to appropriate int/double
                int w = Integer.parseInt(weight), ep = Integer.parseInt(epoch);
                double alpha = Double.parseDouble(learningRate), th = Double.parseDouble(theta), thr = Double.parseDouble(threshold);
                
                // add appropriate paths to the files provided
                String readDataFile = "./trainingSets/" + readFile;
                String writeWeightFile = "./trainedWeights/" + writeFile;

                // all necessary inputs have been acquired so begin training of perceptron net
		PerceptronTraining perceptronTrain = new PerceptronTraining(readDataFile, w, ep, writeWeightFile, alpha, th,  thr);
		perceptronTrain.Train();
	}
}

