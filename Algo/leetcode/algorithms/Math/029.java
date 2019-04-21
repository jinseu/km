public class Solution {
    public int divide(int dividend, int divisor) {
    	if (dividend == 0 || divisor == 0)
            return 0;
        int res = 0;
        int count = 1;
        long div = Math.abs((long)dividend);
        long divis = Math.abs((long)divisor);
        long divis2 = divis;
        if(divis > div)
        	return 0;
        while(div >= divis2)
        {
        	count = 1;
        	divis = divis2;
           while(div >= divis)
           {
        	    div -= divis;
        	    res += count;
        	    count += count;
        	    divis += divis;
           }
        }

        if ((dividend>0&&divisor<0) || (dividend<0&&divisor>0))
        	return 0 - res;
        else
        	return res;     
    }
}