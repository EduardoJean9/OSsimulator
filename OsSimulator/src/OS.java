import java.util.ArrayList;

public class OS {
		HardDisk hd;
		RAM r;
		ArrayList<PCB> ready;
		ArrayList<PCB> waiting;
		ArrayList<PCB> terminated;
		CPU[] cpu;
		int sortingMethod;
		
		public OS (int numpages, int pagesize, int numCPU, int sortingMethod)
		{
			hd = new HardDisk();
			hd.input();
			r = new RAM(numpages, pagesize, hd);
			ready = new ArrayList<PCB>();
			waiting = new ArrayList<PCB>();
			terminated = new ArrayList<PCB>();
			cpu = new CPU[numCPU];
			this.sortingMethod = sortingMethod;
		}
		
		public void longTermSchedular()
		{
			//get the size of the next job from HD
			//ask the RAM if it has room
			while (true)
			{
				if (hd.pcbList.size() == 0)
					break;
				else if (r.canFit(hd.pcbList.get(0)))
				{
					PCB job = hd.pcbList.remove(0);
					r.Load(job);
					ready.add(job);
				}
				else
					break;
			}
			
		}
		
		public void shortTermSchedular()
		{
			//1- FCFS
			//2- Priority
			//3- SJF
			
			if(sortingMethod == 1)
			{
				return;
			}
			else if (sortingMethod == 2)
			{
				PCB temp;
		        for(int i=0; i < ready.size()-1; i++){
		 
		            for(int j=1; j < ready.size()-i; j++){
		                if(ready.get(j-1).getJobPriority() < ready.get(j).getJobPriority()){
		                    temp=ready.get(j-1);
		                    ready.set((j-1), ready.get(j));
		                    ready.set(j, temp);
		                }
		            }
		        }
		    }
			
			else if (sortingMethod == 3)
			{
				PCB temp;
		        for(int i=0; i < ready.size()-1; i++){
		 
		            for(int j=1; j < ready.size()-i; j++){
		                if(ready.get(j-1).getJobSize() > ready.get(j).getJobSize()){
		                    temp=ready.get(j-1);
		                    ready.set((j-1), ready.get(j));
		                    ready.set(j, temp);
		                }
		            }
		        }
			}
		}
		
		public void Dispatcher()
		{
			
		}

		public void printPCB()
		{
			for(PCB p : ready)
			{
				System.out.println(p.toString());
			}
		}
		
		public String toString()
		{
			return "# Pages: " + r.numpages + " Page Size: " + r.pagesize + "\n"
					+ "# of CPUs: " + cpu.length + " Sorting Method: " + sortingMethod;
		}
}
