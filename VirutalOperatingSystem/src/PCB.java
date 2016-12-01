import java.util.ArrayList;

public class PCB {
	//cpuid:				//Information the assigned CPU
	//program-counter; 	// information the assigned CPU (for multiprocessor system)
	//struct state;		// the job’s pc holds the address of the instruction to fetch
						// record of environment that is saved on interrupt
						// including the pc, registers, permissions, buffers, caches, active 
						// pages/blocks
	//code-size;			// extracted from the //JOB control line
	//struct registers: 	// accumulators, index, general
	//struct sched: 		// burst-time, priority, queue-type, time-slice, remain-time
	//struct accounts: 	// cpu-time, time-limit, time-delays, start/end times, io-times
	//struct memories:	// page-table-base, pages, page-size
						// base-registers – logical/physical map, limit-reg
	//struct progeny: 	// child-procid, child-code-pointers
	//parent: ptr; 		// pointer to parent (if this process is spawned, else ‘null’)
	//struct resources: 	// file-pointers, io-devices – unitclass, unit#, open-file-tables
	//status; 			// {running, ready, blocked, new}
	//status_info:		// pointer to ‘ready-list of active processes’ or
						// ‘resource-list on blocked processes’
	//priority: integer;	// of the process, extracted from the //JOB control line
	public int jobNumber;
	public int programCounter;
	public int jobPriority;
	public int jobSize;
	public int dataCounter;
	public long jobTime;
	public long startTime;
	public long createdTime;
	public long dispatchTime;
	public ArrayList<String> inputBuffer;
	public ArrayList<String> outputBuffer;
	
	PCB()
	{
		jobNumber = -1;
		programCounter = -1;
		jobPriority = -1;
		jobSize = -1;
		dataCounter = -1;
		jobTime = 0;
		startTime = 0;
		createdTime = 0;
		dispatchTime = 0;
		ArrayList<String> inputBuffer = new ArrayList<String>();
		ArrayList<String> outputBuffer = new ArrayList<String>();
	}
	
	PCB(int jobNumber, int jobSize, int jobPriority, int programCounter, int dataCounter, 
			ArrayList<String> inputBuffer, ArrayList<String> outputBuffer, long jobTime, long startTime, long createdTime, long dispatchTime)
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
	
	

