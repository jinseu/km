public class Solution {
    public List<String> generateParenthesis(int n) {
    	List<String> res = new ArrayList<String>();
    	if( n > 0)
    	{
    		char[] cache = new char[n*2];
    		dfs(res,cache,0,n,n);
    	}
    	    
    	
    	return res;
    }
	private void dfs(List<String> res, char[] cache, int i, int m, int n) {
	    if (m == 0 && n == 0)
	    {
	        res.add(new String(cache,0,i));
	    }
	    else
	    {
	        if (m != 0)
	        {
	        	cache[i] = '(';
	            dfs(res, cache,i+1, m - 1, n);
	        }
	        
	        if (m < n && n != 0)
	        {
	        	cache[i] = ')';
	            dfs(res, cache,i+1, m, n - 1);
	        }
	    }
		
	}
}