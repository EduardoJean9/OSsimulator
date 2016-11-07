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
			String instructionBin = format.substring(0, Math.min(format.length(), 2));
			
			if (instructionBin.equals("00")) //Arithmetic instruction format
			{
				
			}
			
			else if (instructionBin.equals("01")) //Conditional Branch and Immediate format
			{
				
			}
			
			else if (instructionBin.equals("10")) //Unconditional Jump format
			{
				
			}
			
			else if (instructionBin.equals("11")) //Input and Output instruction format
			{
				String opcodeStr = instructionBin.substring(2, 8);
				int opcode = Integer.parseInt(opcodeStr, 2);
				opcodeStr = Integer.toString(opcode, 16);
				switch (opcodeStr)
				{
					case "0":
						break;
					case "1":
						break;
				}
				//opcode for 11 can only rd or wr
				//convert next four bits to decimal
				//convert next for bits to decimal
				//convert address into decimal
			}
			
			else
				System.out.println("Error in decoding. First two bits of instruction are: " +  instructionBin);
			
			programCounter++;
			if (programCounter >= jobSize)
			{
				stateArray[5] = true;
			}
		}
	}
}