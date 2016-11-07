//package operatingSystem;

public class CPU {
	
	public int jobNumber = -1;
	public int programCounter = -1;
	public int jobPriority = -1;
	public int jobSize = -1;
	public int dataCounter = 1;
	public RAM ram;
	
	public CPU(RAM i)
	{
		ram = i;
	}
	
	/*
	 *  Flag Array
	 *  0. Idle
	 *  1. Send to IO queue
	 *  2. send to wait queue
	 *  3. send to terminate
	 *  4. send to ready queue
	 *  5. interrupt
	 */
	boolean stateArray[] = {true, false, false, false, false, false};
	/*
	 * register array
	 * register 0 is the accumulator
	 * register 1 is always set to 0 
	 */
	
	int register[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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
	
	private static String hexToBinary(String hex) {		
		char[] num = hex.toCharArray();
		String bin_num = "";
		for(char c : num){
			bin_num += (String.format("%4s", Integer.toBinaryString(Integer.parseInt(c+"", 16))).replace(" ", "0"));
		}
		return bin_num;
	}
	
	public void run()
	{
		while (!stateArray[5])
		{
			String instruction = ram.getRAM(jobNumber, programCounter);
			String format = hexToBinary(instruction);
			String upToNCharacters = format.substring(0, Math.min(format.length(), 2));
			
			if (upToNCharacters.equals("00")) //Arithmetic instruction format
			{
				
			}
			
			else if (upToNCharacters.equals("01")) //Conditional Branch and Immediate format
			{
				
			}
			
			else if (upToNCharacters.equals("10")) //Unconditional Jump format
			{
				
			}
			
			else if (upToNCharacters.equals("11")) //Input and Output instruction format
			{
				
			}
			
			else
				System.out.println("Error in decoding. First two bits of instruction are: " +  upToNCharacters);
			
			programCounter++;
			if (programCounter >= jobSize)
			{
				stateArray[5] = true;
			}
		}
	}
}