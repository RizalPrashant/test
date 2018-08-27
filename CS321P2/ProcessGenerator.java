import java.util.Random;

public class ProcessGenerator {
	double probability;
	Random random;
	
	

	public ProcessGenerator(double prob) {
		setProbability(prob);
	}
	
	public boolean query() {
		random = new Random();
		double randVal = random.nextDouble();
		if (randVal < probability) {
			return true;
		}
		else {
		return false;
		}
	}
	
	public Process getNewProcess(int currTime, int maxTime, int maxLevel) {
		int timeToFinish = random.nextInt(maxTime) + 1;
		int priorityLevel = random.nextInt(maxLevel) + 1;
		Process temp = new Process(currTime,timeToFinish,priorityLevel);
		temp.setMaxPriorityLevel(maxLevel);
		return temp;
	}
	public void setProbability(double prob) {
		probability = prob;
	}
	
}