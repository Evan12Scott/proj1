// Abstraction of the neural net with necessary user prompts and calls

import java.util.Scanner;

public class main {
	public static void main(String[] args) {
		validateInput userInput = new validateInput();
		trainingInput train = new trainingInput();
		testingInput test = new testingInput();
		Scanner input = new Scanner(System.in);
		String action;

		do {
			System.out.println("Enter 1 to train using a training data file, enter 2 to use a trained weight settings data file:");
			action = input.nextLine();
		} while (userInput.checkAction(action));
		
		if(Integer.parseInt(action) == 1) {
			train.promptUser(input);
			test.promptUser(input);
			do {                                                                                                                                         System.out.println("Enter 1 to test/deploy using a testing/deploying data file, enter 2 to quit");
				action = input.nextLine();
			} while(userInput.checkAction(action));

			if(Integer.parseInt(action) == 2){
				System.exit(0);
			}
			else {
				
			}
		}
		else {
			test.promptUser(input);
		}
	}
}
