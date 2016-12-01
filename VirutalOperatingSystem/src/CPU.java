import java.util.ArrayList;

//package operatingSystem;

public class CPU extends Thread{
	
	public int jobNumber = -1;
	public int programCounter = -1;
	public int jobPriority = -1;
	public int jobSize = -1;
	public int dataCounter = 1;
	public long jobTime = 0;
	public long startTime = 0;
	public long endTime = 0;
	public long createdTime = 0;
	public long dispatchTime = 0;
	public RAM ram;
	public ArrayList<String> inputBuffer;
	public ArrayList<String> outputBuffer;
	public CPU(RAM i)
	{
		ram = i;
	}
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
		this.jobNumber = in.getJobNumber();
		this.programCounter = in.getProgramCounter();
		this.jobPriority = in.getJobPriority();
		this.jobSize = in.getJobSize();
		this.dataCounter = in.getDataCounter();
		this.inputBuffer = in.getInputBuffer();
		this.outputBuffer = in.getOutputBuffer();
		this.startTime = in.getStartTime();
		this.createdTime = in.getCreatedTime();
		if (startTime == 0)
			this.startTime = System.nanoTime();
		if (dispatchTime != 0)
			this.dispatchTime = System.nanoTime();
	}
	
	public PCB contextSwitchOut()
	{
		PCB out =  new PCB(jobNumber, jobSize, jobPriority, programCounter, dataCounter, outputBuffer, inputBuffer, jobTime, startTime, createdTime, dispatchTime);
		stateArray[0] = true;
		stateArray[1] = false;
		stateArray[2] = false;
		stateArray[3] = false;
		stateArray[4] = false;
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
//			if(jobNumber == 2 && programCounter == 20){
//				int i = 0;
//			}
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("Job #: " + jobNumber + " PC: " + programCounter);
			String instruction = ram.getRAM(jobNumber, programCounter);
			String instructionBin = hexToBinary(instruction);
			String format = instructionBin.substring(0, Math.min(instructionBin.length(), 2));
			
			if (format.equals("00")) //Arithmetic instruction format
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
					case "9": //AND
						register[reg3Conv] = register[reg1Conv] & register[reg2Conv];
						break;
					case "10": //OR
						register[reg3Conv] = register[reg1Conv] | register[reg2Conv];
						break;
				}
				//and or
			}
			
			else if (format.equals("01")) //Conditional Branch and Immediate format
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
						if (reg2Conv == 0) //use data
						{
							register[reg1Conv] = addressConv;
						}
						else if (reg2Conv != 0)
						{
							register[reg1Conv] = reg2Conv;
						}
						break;
					case "12": //ADDI
						if (reg2Conv == 0) //use data
						{
							register[reg1Conv] += addressConv;
						}
						else if (reg2Conv != 0)
						{
							register[reg1Conv] += reg2Conv;
						}
						break;
					case "13": //MULI
						if (reg2Conv == 0) //use data
						{
							register[reg1Conv] *= addressConv;
						}
						else if (reg2Conv != 0)
						{
							register[reg1Conv] *= reg2Conv;
						}
						break;
					case "14": //DIVI
						if (reg2Conv == 0) //use data
						{
							register[reg1Conv] /= addressConv;
						}
						else if (reg2Conv != 0)
						{
							register[reg1Conv] /= reg2Conv;
						}
						break;
					case "15": //LDI
						if (reg2Conv == 0) //use data
						{
							register[reg1Conv] = addressConv;
						}
						else if (reg2Conv != 0)
						{
							register[reg1Conv] = reg2Conv;
						}
						break;
					//effective vs indirect address
				}
			}
			
			else if (format.equals("10")) //Unconditional Jump format
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
					stateArray[2] = true;
				}
				else
					System.out.println("Error in reading opcode for unonditional jump.");
			}
			
			else if (format.equals("11")) //Input and Output instruction format
			{
				Driver.IOCount++;
//				System.out.println("Total IO count: " + (Driver.IOCount));
				//System.out.println(instructionBin);
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
				int index = addressConv;
				
				switch (opcode)
				//opcode for 11 can only rd or wr
				{
					case "0": //read
						if (reg2.equals("0"))
						{
							String buff = inputBuffer.remove(0);
//							System.out.println("DEBUG- Line 276: " + buff);
							int result = Integer.parseInt(buff, 16);
							register[reg1Conv] = result;
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
							outputBuffer.add(register[reg1Conv] + "");
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
				}
				//temp buffer
			}
			
			else
				System.out.println("Error in decoding. First two bits of instruction are: " +  instructionBin);
			
			programCounter++;
			if (programCounter >= jobSize-1)
			{
//				endTime = System.nanoTime();
//				this.jobTime = ((endTime - this.startTime) / 1000000);
//				System.out.println("Job " + this.jobNumber + " Finished in " + this.jobTime + " milliseconds");
				stateArray[2] = true;
				stateArray[0] = true;
				stateArray[4] = true;
			}
		}
		endTime = System.nanoTime();
		this.jobTime = ((endTime - this.startTime));
		System.out.println("Job " + this.jobNumber + " Finished in " + this.jobTime + " microseconds");
	}
}