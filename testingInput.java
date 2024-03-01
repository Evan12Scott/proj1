import java.util.Scanner;

public class testingInput {
	validateInput userInput = new validateInput();
	String readFile, writeFile, action;
	public void promptUser(Scanner input){

		do {                                                                                                                                         System.out.println("Enter 1 to test/deploy using a testing/deploying data file, enter 2 to quit");                                 action = input.nextLine();
		} while(userInput.checkAction(action));

                        if(Integer.parseInt(action) == 2){
                                System.exit(0);
                        }
                        else {
				do {
					System.out.println("Enter the trained weight settings input data file name:");
					readFile = input.nextLine();
				} while(userInput.checkReadFile(readFile));

				do {
					System.out.println("Enter the trained weight settings input data file name:");
					writeFile = input.nextLine();
				} while(userInput.validateWriteFile(writeFile));

				// call the testing class file
                        }	
	}
}
