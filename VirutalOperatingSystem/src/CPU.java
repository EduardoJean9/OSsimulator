//package operatingSystem;

public class CPU {
	
	public int jobNumber = -1;
	public int programCounter = -1;
	public int jobPriority = -1;
	public int jobSize = -1;
	public int dataCounter = 1;
	
	/*
	 * Context Switch In
	 * Array
	 *  0. Idle
	 *  1. Send to IO queue
	 *  2. send to wait queue
	 *  3. send to terminate
	 *  4. send to ready queue
	 *  5. interrupt
	 */
	
	boolean stateArray[] = {true, false, false, false, false, false};
	public void contextSwitchIn(PCB in)
	{
		this.jobNumber = in.getJobNumber();;
		this.programCounter = in.getProgramCounter();;
		this.jobPriority = in.getJobPriority();
		this.jobSize = in.getJobSize();
		this.dataCounter = in.getDataCounter();
	}
	
	public PCB contextSwitchOut()
	{
		PCB out =  new PCB(jobNumber, jobSize, jobPriority, programCounter, dataCounter);
		return out;
	}
	
	public void run()
	{
		while (!stateArray[5])
		{
			
			
		}
	}
}