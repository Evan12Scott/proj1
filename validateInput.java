/*
Authors: Evan Scott, Kieran Kennedy, Sean Pala
Last Date Modified: 3/7/24
Description: validateInput ensures the inputs provided by user (passed from testingInput/trainingInput) are appropriate otherwise it will continue to prompt user(function will return true) until a valid entry is given 
*/

import java.util.InputMismatchException;
import java.io.*;

public class validateInput {
	private int userAction;
	private String file;
	private double doubleValue;
	private int intValue;

	/*
	Description: checks whether the action inputted by user is one of the valid options
	PARAMS: action: String
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean checkAction(String action) {
		try {
			userAction = Integer.parseInt(action);
			if(userAction == 1 || userAction == 2){
				return false;
			}
			else {
				return true;
			}
		} catch(NumberFormatException e) {
			System.out.println("Invalid input. Please enter an integer.");
			return true;
		}	
	}

	/*
	Description: validates the ability to open data file for reading from training/testing subdir provided by user
	PARAMS: file: String (filename)
		    typeAct: int (determines whether the path is through training or testing subdirectory)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean checkReadFile(String file, int typeAct){
		try {
			String readFile = "";
                	if(typeAct == 1) {
                        	readFile = "./trainingSets/" + file;
                }
                	else {
                        	readFile = "./testingSets/" + file;
			}
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			return false;
		} catch (IOException e) {
            		System.err.println("An error occurred while reading the data file: " + e.getMessage());
			return true;
        	}
	}

	/*
	Description: validates the ability to open file for reading by user from the trainedWeights subdir 
	PARAMS: file: String (filename)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean checkReadWeightFile(String file){
		try {
			String readFile = "./trainedWeights/" + file;
			BufferedReader reader = new BufferedReader(new FileReader(readFile));
			return false;
		} catch (IOException e) {
            System.err.println("An error occurred while reading the trained weights file: " + e.getMessage());
			return true;
        	}
		}

	/*
	Description: validates the ability to write results to file specified by user in testResults subdir 
	PARAMS: file: String (filename)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateWriteTestResultFile(String file){
		String writeFile = "./testResults/" + file;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
			return false;
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the test file: " + e.getMessage());
			return true;
        	}
	}

	/*
	Description: validates the ability to write converged weights to file specified by user in trainedWeights subdir 
	PARAMS: file: String (filename)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateWriteWeightsFile(String file){
		String writeFile = "./trainedWeights/" + file;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
			return false;
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the test file: " + e.getMessage());
			return true;
        	}
	}

	/*
	Description: validates the integer inputted by user is one of the valid options for assigning initial weights 
	PARAMS: value: String convert to int (user input)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateWeight(String value){
		 try {
                        intValue = Integer.parseInt(value);
                        if(intValue == 0 || intValue == 1){
                                return false;
			}
                        else {
                                return true;
                        }
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }
	}
	
	/*
	Description: validates the integer inputted by user for max number of epochs is within the specified range 
	PARAMS: value: String convert to int (user input)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateEpoch(String value){
		try {
                        intValue = Integer.parseInt(value);
                        if(intValue <= 0){
                                return true;
                        }
                        else {
                                return false;
                        }
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                        return true;
                } 
	}

	/*
	Description: validates the double inputted by user for learning rate is within the specified range 
	PARAMS: value: String convert to double (user input)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateLearningRate(String value){
		 try {
                        doubleValue = Double.parseDouble(value);
                        if(doubleValue > 0.0 && doubleValue <= 1.0){
                                return false;
                        }
                        else {
                                return true;
                        }
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }	
	}

	/*
	Description: validates the double inputted by user for theta is within the specified range 
	PARAMS: value: String convert to double (user input)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateTheta(String value){
		 try {
                        doubleValue = Double.parseDouble(value);
			return false;
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }
	}

	/*
	Description: validates the double inputted by user for the threshold
	PARAMS: value: String convert to double (user input)
	RETURN: boolean - true: invalid so prompt user again
					  false: valid value entry
	*/
	public boolean validateThreshold(String value){
		try {
                        doubleValue = Double.parseDouble(value);
                        return false;
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }	
	}
}
