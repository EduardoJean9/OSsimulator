import java.util.*;
import java.io.*;
//HDD contains an array of strings and a list of PCBs

public class HardDisk extends Driver
{
	public static int jobNumber;
	public static int programCounter;
	public static int jobPriority;
	public static int jobSize;
	public static int dataCounter;
	public static ArrayList<PCB> pcbList;
	public static ArrayList<String> hdData;

	
	public HardDisk(){
		jobNumber = 0;
		programCounter = 0;
		jobPriority = 0;
		jobSize = 0;
		dataCounter = 0;
		pcbList = new ArrayList<PCB>();
		hdData = new ArrayList<String>();
	
	}
	
	public static void input()
	{
		String filename = "Program-File.txt";
		String line = null;
		
		StringBuffer stringbuf = new StringBuffer();
		
		try
		{
			FileReader filereader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(filereader);
			while((line = bufferedReader.readLine()) != null)
			{
				stringbuf.append(line).append("\n");
				//System.out.println(line);
				if (line.contains("JOB")) //is job header
				{
					// JOB 1 17 2
					String jobHeader[] = line.split(" ");
					jobNumber = Integer.parseInt(jobHeader[2], 16);
					jobSize = Integer.parseInt(jobHeader[3], 16);
					jobPriority = Integer.parseInt(jobHeader[4], 16);
					programCounter = hdData.size();
					dataCounter = programCounter+jobSize;
					//dataEnd = dataCounter + 43
					pcbList.add(new PCB(jobNumber, jobSize, jobPriority, programCounter, dataCounter));
					
				}
				else if(line.contains("0x"))
				{
					String[] job = line.split("0x");
					hdData.add(job[1]);
				}
			}
			for (PCB t : pcbList)
				System.out.println(t.toString());
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
	
}



