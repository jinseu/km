public class Solution {
    public int sqrt(int x) {
    	if(x == 0)
    		return 0;
    	if(x == 1)
    		return 1;
    	long left  = 0;
    	long right = x;
    	long res   = 0;
    	long squre = 0;
    	while(left <= right)
    	{
    		res = (left + right)/2;
    		squre = res * res;
    		
    		if(squre > x)
    		{
    			right = res - 1;
    			//res = (res + left)/2; 
    		}
    		else if(squre < x)
    		{
    			left = res + 1;
    			//res = (res + right)/2; 			
    		}
    		else
    			return (int)res;	
    	}
    	if(squre > x)
    		return (int)res -1;
    	return (int)res;
    }
}