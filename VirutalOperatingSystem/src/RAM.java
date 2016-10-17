
public class RAM {
	
	public int[][] pageTable; //Column 0 Job Num, Column 1 Page Num
	public String[] ramData;
	public int numpages;
	public int pagesize;
	
	public RAM(int numpages, int pagesize)
	{
		pageTable = new int[numpages][2];
		ramData = new String[numpages * pagesize];
		this.numpages = numpages;
		this.pagesize = pagesize;
		for(int i = 0; i < numpages, i++)
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
		
	}
	
	//finds empty memory location and stores 'in'.  Returns memory location.
	public static int storeRAM(String in)
	{
		for(int i=0;i<31;i++)
		{
			if(ramData.get(i) == null)
			{
				ramData.set(i, in);
				return i;
			}
		}
		return -1;
	}
	
}