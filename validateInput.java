import java.util.InputMismatchException;

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

	public boolean checkReadFile(String file){
		return false;	
	}

	public boolean validateWriteFile(String file){
		return false;
	}

	public boolean validateWeight(String value){
		 try {
                        intValue = Integer.parseInt(value);
                        if(userAction == 0 || userAction == 1){
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
                        if(userAction <= 0){
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
                        doubleValue = Integer.parseInt(value);
                        if(userAction > 0.0 && userAction <= 1.0){
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
                        doubleValue = Integer.parseInt(value);
			return false;
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }
	}

	public boolean validateThreshold(String value){
		try {
                        doubleValue = Integer.parseInt(value);
                        return false;
                } catch(NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        return true;
                }	
	}
}
