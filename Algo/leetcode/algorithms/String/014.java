public class Solution {
    public String longestCommonPrefix(String[] strs) 
    {
        int strl = strs.length;
        if(strl == 0)
        	return new String();
        if(strl == 1)
        	return strs[0];
        
        int w = strs[0].length() > strs[1].length() ? strs[1].length():strs[0].length();
        for(int i = 1;i < strl;i++)
        {
        	if(w == 0)
        	{
        		break ;
        	}
        	if(strs[i].length() < w)
        	{
        		w = strs[i].length();
        	}
            w = compare(strs[0],strs[i],w);
        }
        return strs[0].substring(0,w);
    }
	private int compare(String str, String str2,int w) 
	{
		int k = 0;
		while( k < w && str.charAt(k) == str2.charAt(k) )
			k++;
		return k;
	}
}