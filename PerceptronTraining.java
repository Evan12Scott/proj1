

public class PerceptronTraining {
	int weight, epoch;
	double learningRate, theta, threshold;
	String readFile, writeFile;
		
	public PerceptronTraining(String readFile, int weight, int epoch, String writeFile, double learningRate, double theta, double threshold) {
	this.readFile = readFile;
	this.weight = weight;
	this.epoch = epoch;
	this.writeFile = writeFile;
	this. learningRate = learningRate;
	this.theta = theta;
	this.threshold = threshold;
	}

	// implement main training algorithm and call any necessary helper methods (do 1 thing per method)
	public void Train() {
	}
}
