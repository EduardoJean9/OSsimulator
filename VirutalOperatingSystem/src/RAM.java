import java.util.ArrayList;

public class RAM {
	
	public int[][] pageTable; //Column 0 Job Num, Column 1 Page Num
	public String[] ramData;
	public int numpages;
	public static int pagesize;
	HardDisk hd;
	
	public RAM(int numpages, int pagesize, HardDisk hd)
	{
		this.hd = hd;
		pageTable = new int[numpages][2];
		ramData = new String[numpages * pagesize];
		this.numpages = numpages;
		this.pagesize = pagesize;
		
		for(int i = 0; i < numpages; i++)
		{
			pageTable[i][0] = -1;
			pageTable[i][1] = i;
		}
	}
	
	//go to memory location and return data
	public String getRAM(int jobnum, int virtualAddress)
	{
		int virtualPageNum = (virtualAddress / pagesize) + 1;
		int physicalPageNum = -1;
		int counter = 0;
		for(int i = 0; i < numpages; i++)
		{
			if(pageTable[i][0] == jobnum)
			{
				counter++;
				if(virtualPageNum == counter) //set physical page number
				{
					physicalPageNum = pageTable[i][1];
					break;
				}
			}
		}
		
		int offSet = virtualAddress % pagesize;
		
		return ramData[(physicalPageNum * pagesize) + offSet];
	}
	
	public boolean canFit(PCB in)
	{
		int jobSize = in.getJobSize();
		int pagesNeeded = jobSize / pagesize;
		int freePages = 0;
		if(jobSize % pagesize == 0) {/*Do nothing*/}
		else 
			pagesNeeded++;
		for(int i = 0; i < numpages; i++)
		{
			if(pageTable[i][0] == -1)
			{
				freePages++;
			} 
		}
		if(pagesNeeded <= freePages)
			return true;
		else
			return false;
	}
	
	public void Load(PCB n)
	{
		int jobSize = n.getJobSize();
		int pagesNeeded = jobSize / pagesize;
		ArrayList<Integer> allocatedPages = new ArrayList<Integer>();
		if(jobSize % pagesize == 0) {/*Do nothing*/}
		else 
			pagesNeeded++;
		for(int i = 0; i < numpages; i++)
		{
			if(pageTable[i][0] == -1)
			{
				pageTable[i][0] = n.getJobNumber();
				allocatedPages.add(i);
				pagesNeeded--;
			}
			if(pagesNeeded == 0)
				break;
		}
		for(int i = 0; i < jobSize; i++)
		{
			int pageNum = i / pagesize;
			int offset = i % pagesize;
			int hdIndex = n.getProgramCounter() + i;
			ramData[(allocatedPages.get(pageNum)* pagesize) + offset] = hd.getInstruction(hdIndex);
		}
	}
	
	public void Deallocate (PCB a)
	{
		for (int i = 0; i < numpages; i++)
		{
			if(pageTable[i][0] == a.getJobNumber())
			{
				pageTable[i][0] = -1;
			}
		}
	}
	
	public void CoreDump()
	{
		if(ramData[0] == null)
		{
			System.out.println("[]");
		}
		else
		{
			for(String l : ramData)
			{
				System.out.println(l);
			}			
		}
	}
}