public class Solution {
	private char[] rom;
	private int[]  value;
	Solution()
	{
	   rom =new char[7];
	   rom[0] = 'I';
	   rom[1] = 'V';
	   rom[2] = 'X';
	   rom[3] = 'L';
	   rom[4] = 'C';
	   rom[5] = 'D';
	   rom[6] = 'M';
	   value = new int[7];
	   value[0] = 1;
	   value[1] = 5;
	   value[2] = 10;
	   value[3] = 50;
	   value[4] = 100;
	   value[5] = 500;
	   value[6] = 1000;
	}
    public int romanToInt(String s) 
    {
    	//I    	V    	X    	L   	C    	D    	M


        int index = s.length() -1;
        int level = 0; 
        int res = 0;
        while(index >= 0)
        {
        	char x = s.charAt(index);
        	int i =0;
        	for( ;i <rom.length;i++)
        	{
        		if(x == rom[i])
        		{
        			break ;
        		}
        	}
        	if(i==rom.length)
        		return 0;
        	if(i >= level)
        	{
        		res += value[i];
        	}
        	else
        	{
        		res -= value[i];
        	}
        	if(i > level)
        	{
        		level = i;
        	}
        	index--;
        }
        return res;
    }
}