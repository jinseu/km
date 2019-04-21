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
    public String intToRoman(int num) 
    {
    	StringBuilder res = new StringBuilder();
        for(int i = value.length-1;i >= 0;i-=2)
        {
        	int x = num / value[i];
        	res.append(toRoman(x,i));
        	num = num % value[i];
        }
        return res.toString();
    }
    public StringBuffer toRoman(int x,int j)
    {
    	StringBuffer sb = new StringBuffer();
    	if(0 < x && x <=3)
    	{
    		for(int i = 0;i<x;i++)
    		{
    			sb.append(rom[j]);
    		}
    	}
    	else if(x == 4)
    	{
    		 sb.append(rom[j]);
    		 sb.append(rom[j + 1]);
    	}    	
    	else if(x >= 5 && x <=8)
    	{
    		sb.append(rom[j+1]);
    		for(int i = x;i > 5;i--)
    		{
    			sb.append(rom[j]);
    		}
    	} 
    	else if( x == 9)
    	{
    		sb.append(rom[j]);
    		sb.append(rom[j+2]);
    	}
    	return sb;
    }
}