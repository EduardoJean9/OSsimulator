import java.util.*;
import java.io.*;


public class HardDisk
{
	public int jobNumber;
	public int programCounter;
	public int jobPriority;
	public int jobSize;
	public int dataCounter;
	public long jobTime;
	public long startTime;
	public ArrayList<PCB> pcbList;
	public ArrayList<String> hdData;

	
	public HardDisk()
	{
		jobNumber = 0;
		programCounter = 0;
		jobPriority = 0;
		jobSize = 0;
		dataCounter = 0;
		pcbList = new ArrayList<PCB>();
		hdData = new ArrayList<String>();
	
	}
	
	public void input()
	{
		String filename = "Program-File.txt";
		String line = null;
		
		StringBuffer stringbuf = new StringBuffer();
		
		try
		{
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(filereader);
			boolean data = false;
			ArrayList<String> jobData = null;
			while((line = bufferedReader.readLine()) != null)
			{
				stringbuf.append(line).append("\n");
				
				if (line.contains("JOB")) //is job header
				{
					// JOB 1 17 2
					data = false;
					jobData = new ArrayList<String>();
					String jobHeader[] = line.split(" ");
					jobNumber = Integer.parseInt(jobHeader[2], 16);
					jobSize = Integer.parseInt(jobHeader[3], 16);
					jobPriority = Integer.parseInt(jobHeader[4], 16);
					programCounter = 0;//hdData.size();
					dataCounter = programCounter+jobSize;
					pcbList.add(new PCB(jobNumber, jobSize, jobPriority, programCounter, dataCounter, jobTime, startTime));
				}
				else if(line.contains("0x")) //Job is either data or instruction code
				{
					String[] job = line.split("0x");
					hdData.add(job[1]);
					if(data){
						jobData.add(job[1]);
					}
					else{
						//ignore		
					}
				}
				else if(line.contains("Data")){
					data = true;
				}
				else if(line.contains("END")){
					pcbList.get(pcbList.size()-1).setInputBuffer(jobData);
				}
			}
			//print out each job header
//			for (PCB t : pcbList)
//				System.out.println(t.toString());
			
			bufferedReader.close();
		}
		catch(FileNotFoundException ex1)
		{
			System.out.println("Error: File was not found.");
		}
		catch(IOException ex)
		{
			System.out.println("Error: IO");
		}
		
	}
	
	public PCB firstLine()
	{
		return pcbList.get(0);
	}
	
	public String getInstruction(int index)
	{
		return hdData.get(index);
	}
	
}



