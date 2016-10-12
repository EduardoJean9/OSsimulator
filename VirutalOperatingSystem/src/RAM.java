import java.util.ArrayList;

public class RAM {
	
	public static ArrayList<ArrayList<String>> pageTable = new ArrayList<ArrayList<String>>();
	public static ArrayList<String> ramData = new ArrayList<String>();
	
	//go to memory location and return data
	public static String getRAM(int location)
	{
			return (ramData.get(location));
	}
	
	//finds memory location and sets value to NULL
	public static void eraseRAM(int location)
	{
		ramData.remove(location);
	}
	
	//set every memory location in RAM to NULL
	public static void clearRAM(int location)
	{
		for(int i=0;i<31;i++)
		{
			ramData.remove(location);
		}
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