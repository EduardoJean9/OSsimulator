import java.util.*;
import java.io.*;
//HDD contains an array of strings and a list of PCBs

public class HardDisk extends Driver
{
	static int jobNumber = 0;
	static int lineNumber;
	static int jobPriority;

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
				System.out.println(line);
				if (line.contains("//")) //is job header
				{
					String jobHeader[] = line.split(" ");
					jobHeader[2] = jobNumber;
					jobHeader[3] = lineNumber;
					jobHeader[4] = jobPriority;
					
				}
				else
				{
					
				}
			}
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



