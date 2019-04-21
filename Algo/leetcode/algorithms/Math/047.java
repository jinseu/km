public class Solution {
	    List<List<Integer>> result;  
	    boolean noswap(int[] num, int i, int step) {
	        int j;
	        for (j = step; j < i; j++) {
	            if (num[i] == num[j])
	                return true;
	        }
	        return false;
	    }
	    void dfs(int[] num, int step ) {
	        if (step == num.length) {
	        	List<Integer> arr = new ArrayList<Integer>();
	        	for(int i : num)
	               arr.add(i);
	            result.add(arr);
	        	return ;
	        }
	        int i;
	        for (i = step; i < num.length; i++) {
	            if (noswap(num, i, step))
	                continue;
	            int tem   = num[step];
	            num[step] = num[i];
	            num[i]    = tem;
	            dfs(num, step + 1);
	            tem       = num[step];
	            num[step] = num[i];
	            num[i]    = tem;
	        }
	    }
		public List<List<Integer>> permuteUnique(int[] num) 
	    {
	         result = new ArrayList<List<Integer>>();  
	         dfs(num,0);  
	         return result;  
	    }
}