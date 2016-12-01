import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class Driver {
		
	public static int IOCount = 0;
	public static void main(String[] args) throws InterruptedException, Exception 
	{
		FileWriter fw = new FileWriter("Graph Data.csv");
		BufferedWriter fout = new BufferedWriter(fw);	
		fout.write("# of Pages,Page Size,# of CPU,Sorting Method,Total Execution Time(Mili),Average wait time(Mili)");
		fout.newLine();
		for(int i = 4; i<=16; i*=2){
			for(int j = 16; j<=32; j*=2){
				for(int k = 1; k<=4; k*=2){
					for(int l = 1; l<=3; l++){
						Stopwatch.start();
						int numPages, pageSize, numCPU, sortingMethod;
						
						sortingMethod = l;
						numCPU = k;
						pageSize = j;
						numPages = i;
						
						OS os = new OS(numPages, pageSize, numCPU, sortingMethod);
						
						
						while(!os.isDone() || os.ready.size() != 0){
							os.longTermSchedular();
							os.shortTermSchedular();		
							os.Dispatcher();
							TimeUnit.MILLISECONDS.sleep(100);
						}
						
						os.longTermSchedular();
						os.shortTermSchedular();		
						os.Dispatcher();
						
						Stopwatch.stop();
						
						long avgWait = 0;
						for(PCB t : os.terminated){
							avgWait += t.dispatchTime - t.createdTime;
						}
						avgWait = avgWait/30;
						
						fout.write(numPages +"-" + pageSize + "-" + numCPU + "-" +sortingMethod + "," + (Stopwatch.getElapsedTimeSecs()/100000) + "," + (avgWait/100000));
						fout.newLine();

						System.out.println();
						System.out.println("-------------------------------------------------------------------------------");
						System.out.println("Terminate Queue Size: " + os.terminated.size());
						System.out.println("Total IO count: " + IOCount);
						System.out.println("Total time elapsed: " + Stopwatch.getElapsedTimeSecs() + " nanoseconds");
						System.out.println("Average job time: " + ((Stopwatch.getElapsedTimeSecs())/30) + " nanoseconds" );
						
						System.out.println("Number of CPUs: " + numCPU);
						System.out.println("-------------------------------------------------------------------------------");
						

					}
				}
			}
		}
		fout.flush();
		fout.close();		
		
		
//		Scanner scan = new Scanner(System.in);
//		int numPages, pageSize, numCPU, sortingMethod;
//		String temp;
//
//		System.out.print("Number of RAM pages [8]: ");
//		temp = scan.nextLine();
//		if(temp.equals("")
//		{
//			numPages = 8;
//		}
//		else
//		{
//			numPages = Integer.parseInt(temp);
//		}
//		System.out.print("Page size [16]: ");
//		temp = scan.nextLine();
//		if(temp.equals(""))
//		{
//			pageSize = 16;
//		}
//		else
//		{
//			pageSize = Integer.parseInt(temp);
//		}
//		System.out.print("# of CPUs [1]: ");
//		temp = scan.nextLine();
//		if(temp.equals(""))
//		{
//			numCPU = 1;
//		}
//		else
//		{
//			numCPU = Integer.parseInt(temp);
//		}
//		System.out.println("Sorting Method");
//		System.out.println("(1) FCFS");
//		System.out.println("(2) Priority");
//		System.out.println("(3) Shortest Job First");
//		System.out.print("Method [1]: ");
//		temp = scan.nextLine();
//		Stopwatch.start();
//		System.out.println("Stopwatch started");
//		if(temp.equals(""))
//		{
//			sortingMethod = 1;
//		}
//		else
//		{
//			sortingMethod = Integer.parseInt(temp);
//		}
//		
//		
//		
//		OS os = new OS(numPages, pageSize, numCPU, sortingMethod);
//		
//		
//		while(!os.isDone() || os.ready.size() != 0){
//			os.longTermSchedular();
//			os.shortTermSchedular();		
//			os.Dispatcher();
//			TimeUnit.MILLISECONDS.sleep(100);
//		}
//		
//		os.longTermSchedular();
//		os.shortTermSchedular();		
//		os.Dispatcher();
//		
//		FileWriter fw = new FileWriter("Results.csv");
//		BufferedWriter fout = new BufferedWriter(fw);
//		fout.write("System Paramiters");
//		fout.newLine();
//		fout.write("# of Pages,Page Size,# of CPU,Sorting Method");
//		fout.newLine();
//		fout.write(numPages +"," + pageSize + "," + numCPU + "," +sortingMethod);
//		fout.newLine();
//		fout.write("Terminate Queue Size: " + os.terminated.size());
//		fout.newLine();
//		fout.write("Total IO count: " + IOCount);
//		fout.newLine();
//		fout.write("Total time elapsed: " + Stopwatch.getElapsedTimeSecs() + " nanoseconds");
//		fout.newLine();
//		fout.write("Average job time: " + ((Stopwatch.getElapsedTimeSecs())/30) + " nanoseconds" );
//		fout.newLine();
//	
//		fout.write("Job #,Job Time");
//		fout.newLine();
//		for(PCB t : os.terminated){
//			fout.write(t.jobNumber + "," + t.jobTime);
//			fout.newLine();
//		}
//		
//		fout.write("Job #,Wait Time");
//		fout.newLine();
//		for(PCB t : os.terminated){
//			long time = t.dispatchTime - t.createdTime;
//			fout.write(t.jobNumber + "," + time);
//			fout.newLine();
//		}
//		
//		System.out.println();
//		System.out.println("-------------------------------------------------------------------------------");
//		System.out.println("Terminate Queue Size: " + os.terminated.size());
//		System.out.println("Total IO count: " + IOCount);
//		System.out.println("Total time elapsed: " + Stopwatch.getElapsedTimeSecs() + " nanoseconds");
//		System.out.println("Average job time: " + ((Stopwatch.getElapsedTimeSecs())/30) + " nanoseconds" );
//		
//		System.out.println("Number of CPUs: " + numCPU);
//		System.out.println("-------------------------------------------------------------------------------");
//		
//		fout.flush();
//		fout.close();
	}
}
