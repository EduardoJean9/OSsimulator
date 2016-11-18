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
	//ask about and/or
	//ask about direct/indirect
	//ask about time printing (when job is completed)
	/*
	 *  Flag Array
	 *  0. Idle
	 *  1. send to wait queue
	 *  2. send to terminate
	 *  3. send to ready queue
	 *  4. Interrupt
	 */
	boolean stateArray[] = {true, false, false, false, false};
	
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
		stateArray[0] = false;
		while (!stateArray[4])
		{
			String instruction = ram.getRAM(jobNumber, programCounter);
			String format = hexToBinary(instruction);
			String instructionBin = format.substring(0, Math.min(format.length(), 2));
			
			if (instructionBin.equals("00")) //Arithmetic instruction format
			{
				String opcode = instructionBin.substring(2, 8); //convert opcode to hex
				int opcodeConv = Integer.parseInt(opcode, 2);
				opcode = Integer.toString(opcodeConv, 16);
				String reg1 = instructionBin.substring(8, 12); //convert reg 1 and reg 2 and reg 3 to decimal
				String reg2 = instructionBin.substring(12, 16);
				String reg3 = instructionBin.substring(16, 20);
				int reg1Conv = Integer.parseInt(reg1, 2);
				int reg2Conv = Integer.parseInt(reg2, 2);
				int reg3Conv = Integer.parseInt(reg3, 2);
				reg1 = Integer.toString(reg1Conv, 10);
				reg2 = Integer.toString(reg2Conv, 10);
				reg3 = Integer.toString(reg3Conv, 10);
				reg1Conv = Integer.parseInt(reg1);
				reg2Conv = Integer.parseInt(reg2);
				reg3Conv = Integer.parseInt(reg3);
				
				switch(opcode)
				{
					case "5": //add
						register[reg1Conv] = register[reg2Conv] + register[reg3Conv];
						break;
					case "6": //sub
						register[reg3Conv] = register[reg1Conv] - register[reg2Conv];
						break;
					case "7": //mult
						register[reg3Conv] = register[reg1Conv] * register[reg2Conv];
						break;
					case "8": //div
						register[reg3Conv] = register[reg1Conv] / register[reg2Conv];
						break;
				}
				//and or
			}
			
			else if (instructionBin.equals("01")) //Conditional Branch and Immediate format
			{
				String opcode = instructionBin.substring(2, 8); //convert opcode to hex
				int opcodeConv = Integer.parseInt(opcode, 2);
				opcode = Integer.toString(opcodeConv, 16);

				
				String reg1 = instructionBin.substring(8, 12); //convert reg 1 and reg 2 to decimal
				String reg2 = instructionBin.substring(12, 16);
				int reg1Conv = Integer.parseInt(reg1, 2);
				int reg2Conv = Integer.parseInt(reg2, 2);
				reg1 = Integer.toString(reg1Conv, 10);
				reg2 = Integer.toString(reg2Conv, 10);
				reg1Conv = Integer.parseInt(reg1);
				reg2Conv = Integer.parseInt(reg2);
				
				
				boolean effectiveAddress = false;
				String address = instructionBin.substring(16, 32); //convert address to decimal
				int addressConv = Integer.parseInt(address, 2);
				address = Integer.toString(addressConv, 10);
				addressConv = Integer.parseInt(address);
				
				if  (effectiveAddress) //if effective address
				{
					addressConv += reg1Conv;
				}
				
					
				switch (opcode)
				{
					case "21": //reg1 == reg2
						this.jobNumber = addressConv;
						break;
					case "22": //reg1!=reg2
						this.jobNumber = addressConv;
						break;
					case "23": //reg1 == 0
						this.jobNumber = addressConv;
						break;
					case "24": //reg1 != 0
						this.jobNumber = addressConv;
						break;
					case "25": //reg1 > 0
						this.jobNumber = addressConv;
						break;
					case "26": //reg1 < 0
						this.jobNumber = addressConv;
						break;
					case "11": //MOVI
						break;
					case "12": //ADDI
						break;
					case "13": //MULI
						break;
					case "14": //DIVI
						break;
					case "15": //LDI
						break;
					//effective vs indirect address
				}
			}
			
			else if (instructionBin.equals("10")) //Unconditional Jump format
			{
				String opcode = instructionBin.substring(2, 8); //convert opcode to hex
				int opcodeConv = Integer.parseInt(opcode, 2);
				opcode = Integer.toString(opcodeConv, 16);
				
				String address = instructionBin.substring(9, 32); //convert address to decimal
				int addressConv = Integer.parseInt(address, 2);
				address = Integer.toString(addressConv, 10);
				addressConv = Integer.parseInt(address);
				if (opcodeConv == 20)
					this.jobNumber = addressConv;
				else if (opcodeConv == 18)
				{
					stateArray[4] = true;
					stateArray[0] = true;
				}
				else
					System.out.println("Error in reading opcode for unonditional jump.");
			}
			
			else if (instructionBin.equals("11")) //Input and Output instruction format
			{
				Driver.IOCount++;
				System.out.println("Total IO count: " + Driver.IOCount);
				String opcode = instructionBin.substring(2, 8); //convert opcode to hex
				int opcodeConv = Integer.parseInt(opcode, 2);
				opcode = Integer.toString(opcodeConv, 16);
				
				String reg1 = instructionBin.substring(8, 12); //convert reg 1 and reg 2 to decimal
				String reg2 = instructionBin.substring(12, 16);
				int reg1Conv = Integer.parseInt(reg1, 2);
				int reg2Conv = Integer.parseInt(reg2, 2);
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
							register[reg1Conv] = 0;//temp buffer at address 
						}
						else if (!reg2.equals("0"))
						{
							register[reg1Conv] = register[reg2Conv];
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
							 register[addressConv] = 0;//temp buffer at address
						}
						else if (!reg2.equals("0"))
						{
							register[reg2Conv] = register[reg1Conv];
						}
						else
						{
							//error in reading reg2
							System.out.println("Error in reading I/O instruction: register2 not found.");
						}
						break;
				}
				//temp buffer
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