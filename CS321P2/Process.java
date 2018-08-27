
public class Process {
	private int priorityLevel;
	private int timeToFinish;
	private int timeNotProcessed;
	private int arrivalTime;
	private int maxPriorityLevel;
	
	public Process(int currTime, int maxTime, int maxLevel) {
		arrivalTime = currTime;
		timeToFinish = maxTime;
		priorityLevel = maxLevel;
		timeNotProcessed = 0;
	}
	
	public void reduceTimeRemaining() {

		timeToFinish--;
	}


	public void incrementTimeNotProcessed() {

		timeNotProcessed++;
	}


	public int getTimeNotProcessed() {

		return timeNotProcessed;
	}


	public int getTimeRemaining() {
		//check if getTime = timeToFinish
		return timeToFinish - timeNotProcessed;
	}


	public boolean done() {
		return timeToFinish == 0;
	}
	
	public int getArrivalTime() {

		return arrivalTime;
	}


	public int getPriority() {

		return priorityLevel;
	}


	public void incrementPriority() {
		if (priorityLevel < maxPriorityLevel) {
		priorityLevel++;
		}
	}


	public void resetTimeNotProcessed() {

		timeNotProcessed = 0;
	}
	
	private void setPriorityLevel(int priorityLevel) {

		this.priorityLevel = priorityLevel;
	}
	
	public int getTimeToFinish() {

		return timeToFinish;
	}

	
	private void setTimeToFinsh(int finish) {

		timeToFinish = finish;
	}


	private void setTimeNotProcessed(int process) {

		timeNotProcessed = process;
	}


	private void setArrialTime(int arrive) {

		arrivalTime = arrive;
	}
	public int getMaxPriorityLevel() {

		return maxPriorityLevel;
	}


	public void setMaxPriorityLevel(int maxPrio) {

		maxPriorityLevel = maxPrio;
	}

}
