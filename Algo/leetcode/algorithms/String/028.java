public class Solution {
    public int[] getNext(String str)
    {
    	int[] next = new int[str.length()];
    	next[0] = -1;
    	int i = -1;
    	for(int j = 1;j < str.length();j++)
    	{
    		i = next[j-1];
    		while(str.charAt(i+1) != str.charAt(j) && i >= 0)
    			i = next[i];
    		if(str.charAt(i+1) == str.charAt(j))
    			i++;
    		next[j] = i;
    	}
    	return next;
    }
    public int strStr(String haystack, String needle) {
    	int m = haystack.length();
    	int n = needle.length();
    	if(n==0)
    		return 0;
    	if(m == 0 && n != 0 )
    	{
    		return -1;
    	}
    	int j = 0;
    	int i = -1;
    	int[] next = getNext(needle);
    	for(;j < m;j++)
    	{
    		while(needle.charAt(i+1) != haystack.charAt(j) && i >= 0)
    			i = next[i];
    		if(needle.charAt(i+1) == haystack.charAt(j))
    			i++;
    		if(i == n-1)
    			return j-i;    		  
    	}
    	return -1;   
    }
}