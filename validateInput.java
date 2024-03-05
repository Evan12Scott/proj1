import java.util.InputMismatchException;
import java.io.*;

public class validateInput {
	private int userAction;
	private String file;
	private double doubleValue;
	private int intValue;

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

	public boolean validateWriteTestResultFile(String file){
		String writeFile = "./testResults/" + file;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
			return false;
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the test file: " + e.getMessage());
			return true;
        	}
	}

	public boolean validateWriteWeightsFile(String file){
		String writeFile = "./trainedWeights/" + file;
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile))) {
			return false;
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the test file: " + e.getMessage());
			return true;
        	}
	}

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

	public boolean validateTheta(String value){
		 try {
                        doubleValue = Double.parseDouble(value);
			return false;
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }
	}

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
