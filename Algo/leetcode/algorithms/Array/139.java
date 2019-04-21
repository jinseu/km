public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        int size =s.length();
        boolean[] res = new boolean[size+1];
        for(int i = 0;i<=size;i++)
        	res[i] = false;
        for (int i = 0;i <= size;i++)
        {
        	String sub = s.substring(0, i);
        	if(dict.contains(sub) == true)
        	{
        		res[i] = true;
        	}
        	else
        	{
        		for(int j = 0;j < i;j++)
        		{
        			sub = s.substring(j, i);
        			if(res[j] == true && dict.contains(sub))
        			{
        				res[i] = true;
        			}
        					
        		}
        	}
        }
        return res[size];        
    }
}