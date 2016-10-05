import java.util.*;
import java.io.*;
//HDD contains an array of strings and a list of PCBs

public class HardDisk 
{
	
	static void input()
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



