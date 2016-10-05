import java.util.*;
import java.io.*;
//HDD contains an array of strings and a list of PCBs
public class HardDisk {
	
	String filename = "Program-File.txt";
	String line = null;
	StringBuffer stringbuf = new StringBuffer();
	try{
		FileReader filereader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(filereader);
		while((line = bufferedReader.readLine()) != null){
			stringbuf.append(line).append("\n");
		}
		bufferedReader.close();
	}
	catch(FileNotFoundException ex1){
		System.out.println("Error: File was not found.");
	}
	catch(IOException ex){
		System.out.println("Error: IO");
	}
//Read the text file
	//String X;
	//x = fin.readLine();
	/*while (x != null){
	 * parse current line;
	 * JOB 1 17 2
	 * string [] = 
	 * initialize job header
	 * Job length = 17;
	 * for(Job length)
	 * {
	 * 		read line and add to HDD list;
	 * }
	 * read next job header;
	 * }
	 */
}}
