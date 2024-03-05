import java.util.Scanner;

public class trainingInput {
        validateInput userInput = new validateInput();
	String readFile, weight, epoch, writeFile, learningRate, theta, threshold;
	
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

		//if weight = 1 get set weight to random value between -0.5 and 0.5 in helper method below

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

                // valid inputs so now convert and do perceptron learning
                int w = Integer.parseInt(weight), ep = Integer.parseInt(epoch);
                double alpha = Double.parseDouble(learningRate), th = Double.parseDouble(theta), thr = Double.parseDouble(threshold);
                String readDataFile = "./trainingSets/" + readFile;
                String writeWeightFile = "./trainedWeights/" + writeFile;

                //call the neural net to do its training in other file with the specified user inputs above
		PerceptronTraining perceptronTrain = new PerceptronTraining(readDataFile, w, ep, writeWeightFile, alpha, th,  thr);
		perceptronTrain.Train();
	}
}

