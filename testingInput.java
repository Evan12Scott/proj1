import java.util.Scanner;

public class testingInput {
	validateInput userInput = new validateInput();
	String readWeightsFile, readDataFile, writeFile, action;
	public void promptUser(Scanner input, String action){
		int typeAction = Integer.parseInt(action);
		do {                                                                                                                                         System.out.println("Enter 1 to test/deploy using a testing/deploying data file, enter 2 to quit:");          
			action = input.nextLine();
		} while(userInput.checkAction(action));

                        if(Integer.parseInt(action) == 2){
                                System.exit(0);
                        }
                        else {
				do {
					System.out.println("Enter the file name with the trained weight settings and ensure it is located in the TRAINEDWEIGHTS subdirectory:");
					readWeightsFile = input.nextLine();
				} while(userInput.checkReadWeightFile(readWeightsFile));

				do {
					System.out.println("Enter the testing/deploying data file name and ensure it is located in the TESTINGSETS subdirectory:");
					readDataFile = input.nextLine();
				} while(userInput.checkReadFile(readDataFile, typeAction));

				do {
					System.out.println("Enter a file name to save the testing/deploying results:");
					writeFile = input.nextLine();
				} while(userInput.validateWriteTestResultFile(writeFile));

				// call the testing class file
				String readWeights = "./trainedWeights/" + readWeightsFile;
				String readData = "./testingSets/" + readDataFile;
				String writeOut = "./testResults/" + writeFile;
				PerceptronTesting perceptronTesting = new PerceptronTesting(readWeights, readData, writeOut);
				perceptronTesting.Test();
                        }	
	}
}
