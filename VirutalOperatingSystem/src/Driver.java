import java.util.*;

public class Driver {
		
	public static int IOCount = 0;
	public static void main(String[] args) 
	{
		
		Scanner scan = new Scanner(System.in);
		int numPages, pageSize, numCPU, sortingMethod;
		String temp;

		System.out.print("Number of RAM pages [8]: ");
		temp = scan.nextLine();
		if(temp.equals(""))
		{
			numPages = 8;
		}
		else
		{
			numPages = Integer.parseInt(temp);
		}
		System.out.print("Page size [16]: ");
		temp = scan.nextLine();
		if(temp.equals(""))
		{
			pageSize = 16;
		}
		else
		{
			pageSize = Integer.parseInt(temp);
		}
		System.out.print("# of CPUs [1]: ");
		temp = scan.nextLine();
		if(temp.equals(""))
		{
			numCPU = 1;
		}
		else
		{
			numCPU = Integer.parseInt(temp);
		}
		System.out.println("Sorting Method");
		System.out.println("(1) FCFS");
		System.out.println("(2) Priority");
		System.out.println("(3) Shortest Job First");
		System.out.print("Method [1]: ");
		temp = scan.nextLine();
		Stopwatch.start();
		System.out.println("Stopwatch started");
		if(temp.equals(""))
		{
			sortingMethod = 1;
		}
		else
		{
			sortingMethod = Integer.parseInt(temp);
		}
		
		OS os = new OS(numPages, pageSize, numCPU, sortingMethod);
		System.out.println(os.toString());
//		System.out.println("Core Dump");
//		os.r.CoreDump();
//		System.out.println("Ready Queue size: " + os.ready.size());
		os.longTermSchedular();
//		System.out.println("Core Dump");
//		os.r.CoreDump();
		System.out.println("Ready Queue size: " + os.ready.size());
		os.printPCB();
		os.shortTermSchedular();
		System.out.println("Sorted Ready");
		os.printPCB();
		System.out.println("Total IO count: " + IOCount);
		System.out.println("Total time elapsed:" + Stopwatch.getElapsedTime());
	}
}
