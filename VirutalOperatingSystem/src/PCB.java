import java.util.ArrayList;

public class PCB {
	public int jobNumber;
	public int programCounter;
	public int jobPriority;
	public int jobSize;
	public int dataCounter;
	public long jobTime;
	public long startTime;
	public long createdTime;
	public long dispatchTime;
	public long waitTime;
	public int cpuNum;
	public ArrayList<String> inputBuffer;
	public ArrayList<String> outputBuffer;
	
	PCB()
	{
		cpuNum = -1;
		jobNumber = -1;
		programCounter = -1;
		jobPriority = -1;
		jobSize = -1;
		dataCounter = -1;
		jobTime = 0;
		startTime = 0;
		createdTime = 0;
		dispatchTime = 0;
		waitTime = 0;
		ArrayList<String> inputBuffer = new ArrayList<String>();
		ArrayList<String> outputBuffer = new ArrayList<String>();
	}
	
	PCB(int jobNumber, int jobSize, int jobPriority, int programCounter, int dataCounter, 
			ArrayList<String> inputBuffer, ArrayList<String> outputBuffer, long jobTime, long startTime, long createdTime, long dispatchTime, long waitTime)
	{
		this.jobNumber = jobNumber;
		this.jobSize = jobSize;
		this.jobPriority = jobPriority;
		this.programCounter = programCounter;
		this.dataCounter = dataCounter;
		this.inputBuffer = inputBuffer;
		this.outputBuffer = outputBuffer;
		this.jobTime = jobTime;
		this.startTime = startTime;
		this.createdTime = createdTime;
		this.dispatchTime = dispatchTime;
		this.waitTime = waitTime;
	}
	
	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}

	public long getDispatchTime() {
		return dispatchTime;
	}

	public void setDispatchTime(long dispatchTime) {
		this.dispatchTime = dispatchTime;
	}

	PCB(int jobNumber, int jobSize, int jobPriority, int programCounter, int dataCounter, long jobTime, long startTime)
	{
		this.jobNumber = jobNumber;
		this.jobSize = jobSize;
		this.jobPriority = jobPriority;
		this.programCounter = programCounter;
		this.dataCounter = dataCounter;
		this.jobTime = jobTime;
		this.startTime = startTime;
		this.outputBuffer = new ArrayList<String>();
	}
	
	
	
	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public void setJobTime(long jobTime) {
		this.jobTime = jobTime;
	}

	public int getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(int jobNumber) {
		this.jobNumber = jobNumber;
	}

	public int getProgramCounter() {
		return programCounter;
	}

	public void setProgramCounter(int programCounter) {
		this.programCounter = programCounter;
	}

	public int getJobPriority() {
		return jobPriority;
	}

	public void setJobPriority(int jobPriority) {
		this.jobPriority = jobPriority;
	}

	public int getJobSize() {
		return jobSize;
	}

	public void setJobSize(int jobSize) {
		this.jobSize = jobSize;
	}

	public int getDataCounter() {
		return dataCounter;
	}

	public void setDataCounter(int dataCounter) {
		this.dataCounter = dataCounter;
	}
	
	public ArrayList<String> getOutputBuffer() {
		return outputBuffer;
	}
	
	public void setOutputBuffer(ArrayList<String> outputBuffer) {
		this.outputBuffer = outputBuffer;
	}
	
	public ArrayList<String> getInputBuffer() {
		return inputBuffer;
	}
	
	public void setInputBuffer(ArrayList<String> inputBuffer) {
		this.inputBuffer = inputBuffer;
	}

	public long getJobTime() {
		return jobTime;
	}

	public void setJobTime(int jobTime) {
		this.jobTime = jobTime;
	}

	public String toString()
	{
		return ("job number: " + jobNumber + " Program Counter: " + programCounter + " priority: " + jobPriority + " job size: " + jobSize);
	}
	
}
	
	

