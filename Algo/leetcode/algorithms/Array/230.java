public class Solution {
    static int[] arr;
    static
    {
    	arr = new int[31];
    	int tem = 1;
    	for(int i = 0; i< arr.length;i++)
    	{
    		arr[i] = tem;
    		tem = tem<<1;
    	}
    }
	public boolean isPowerOfTwo(int n) 
    {
		if(n <= 0)
			return false;
        for(int i = 0; i < 31;i++)
        {
        	if((n & arr[i]) == n)
        		return true;
        	if(n < arr[i])
        		return  false;
        }
        return false;
    }
}