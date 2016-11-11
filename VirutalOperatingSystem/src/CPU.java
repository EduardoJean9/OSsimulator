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
				//add, subtract, multiply, divide (based on opcode)
				//first two bits are not used
				//next 6 bits are opcode for (+,-,*,/)
				//next four bits are the first part of solution (think x)
				//next four bits are the second part (think y)
				//next four bits are the answer to the question
				//example (x+y=z)
				//last 12 bits are always 0
			}
			
			else if (instructionBin.equals("01")) //Conditional Branch and Immediate format
			{
				
			}
			
			else if (instructionBin.equals("10")) //Unconditional Jump format
			{
				//jump to address (last 16 bits)
			}
			
			else if (instructionBin.equals("11")) //Input and Output instruction format
			{
				String opcode = instructionBin.substring(2, 8); //convert opcode to hex
				int opcodeConv = Integer.parseInt(opcode, 2);
				opcode = Integer.toString(opcodeConv, 16);
				
				String reg1 = instructionBin.substring(8, 12); //convert reg 1 and reg 2 to decimal
				String reg2 = instructionBin.substring(12, 16);
				int reg1Conv = Integer.parseInt(reg1, 2);
				int reg2Conv = Integer.parseInt(reg1, 2);
				reg1 = Integer.toString(reg1Conv, 10);
				reg2 = Integer.toString(reg2Conv, 10);
				reg1Conv = Integer.parseInt(reg1);
				reg2Conv = Integer.parseInt(reg2);
				
				String address = instructionBin.substring(16, 32); //convert address to decimal
				int addressConv = Integer.parseInt(address, 2);
				address = Integer.toString(addressConv, 10);
				addressConv = Integer.parseInt(address);
				switch (opcode)
				//opcode for 11 can only rd or wr
				{
					case "0": //read
						if (reg2.equals("0"))
						{
							register[reg1Conv-1] = addressConv; 
						}
						else if (!reg2.equals("0"))
						{
							register[reg1Conv-1] = reg2Conv;
						}
						else
						{
							//error in reading reg2
							System.out.println("Error in reading I/O instruction: register2 not found.");
						}
						break;
					case "1": //write
						if (reg2.equals("0"))
						{
							 
						}
						else if (!reg2.equals("0"))
						{
							
						}
						else
						{
							//error in reading reg2
							System.out.println("Error in reading I/O instruction: register2 not found.");
						}
						break;
				}
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