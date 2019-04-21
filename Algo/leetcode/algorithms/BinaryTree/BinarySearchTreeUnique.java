public class Solution {
    private static int[] arr;
	  public int numTrees(int n) {
        if(n <= 0)
        	return 0;
        arr = new int[n+1];
        calc(n);
        return arr[n];
    }
	  private void calc(int i) {
		   if(i == 1)
		   {
		      arr[0] = 1;
		      arr[i] = 1;
		   }
		   else if(arr[i] == 0)
		   {
			    calc(i-1);
			    int res = 0;
			    for(int k = 0; k < i;k++)
			    {
				     res += arr[k]*arr[i-1-k];
			    }
			    arr[i] = res;
		   }	
	  }
}