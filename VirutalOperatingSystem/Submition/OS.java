import java.util.ArrayList;

public class OS {
		HardDisk hd;
		RAM r;
		ArrayList<PCB> ready;
		ArrayList<PCB> terminated;
		CPU[] cpu;
		int sortingMethod;
		
		public OS (int numpages, int pagesize, int numCPU, int sortingMethod)
		{
			hd = new HardDisk();
			hd.input();
			r = new RAM(numpages, pagesize, hd);
			ready = new ArrayList<PCB>();
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
					job.setCreatedTime(System.nanoTime());
					r.Load(job);
					ready.add(job);
				}
				else
					break;
			}
			
		}
		
		public boolean isDone(){
			if(hd.pcbList.size() == 0)
				return true;
			else
				return false;
		}
		
		public void shortTermSchedular()
		{
			//1- FCFS
			//2- Priority
			//3- SJF
			
			if(sortingMethod == 1) //FCFS
			{
				return;
			}
			else if (sortingMethod == 2) //priority
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
			
			else //SJF
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
			
			for(int i = 0; i < cpu.length; i++)
			{				
				if (cpu[i] == null) //if not declared
				{
					if(ready.size() > 0)
					{
						cpu[i] = new CPU(r,i);
						cpu[i].contextSwitchIn(ready.get(0)); //push in a new process
						ready.remove(0);
						cpu[i].start();
					}
				}
				else if(cpu[i].stateArray[0]) //if idle
				{
					if(cpu[i].stateArray[2]) //if terminate
					{
						PCB temp = cpu[i].contextSwitchOut();
						r.Deallocate(temp);
						terminated.add(temp);
						if(ready.size() > 0)
						{
							cpu[i] = new CPU(r,i);
							cpu[i].contextSwitchIn(ready.get(0)); //push in a new process
							ready.remove(0);
							cpu[i].start();
						}
					}
					else
					{
						if(ready.size() > 0)
						{
							cpu[i] = new CPU(r,i);
							cpu[i].contextSwitchIn(ready.get(0)); //push in a new process
							ready.remove(0);
							cpu[i].start();
						}
					}
				}
			}//for(int i...
		}//Dispatcher

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
		
