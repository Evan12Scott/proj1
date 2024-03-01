import java.util.Scanner;

public class testingInput {
	validateInput userInput = new validateInput();
	String readFile, writeFile, action;
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
					System.out.println("Enter the testing/deploying data file name and ensure it is located in the TESTINGSETS subdirectory [INCLUDE .txt]:");
					readFile = input.nextLine();
				} while(userInput.checkReadFile(readFile, typeAction));

				do {
					System.out.println("Enter a file name to save the testing/deploying results [INCLUDE .txt]:");
					writeFile = input.nextLine();
				} while(userInput.validateWriteFile(writeFile));

				// call the testing class file
				PerceptronTesting perceptronTesting = new PerceptronTesting(readFile, writeFile);
				perceptronTesting.Test();
                        }	
	}
}
