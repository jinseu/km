public class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() == 0 ^ t.length() == 0)
           return false;
        if(s.length() == 0 && t.length() == 0)
           return true;
        HashMap<Character,Integer> hs = new HashMap<Character,Integer>();
        for(int i = 0; i < s.length();i++)
        {
        	char curchar = s.charAt(i);
        	if(hs.containsKey(curchar))
        	{
        		hs.put(curchar, hs.get(curchar)+1);
        	}
        	else
        	{
        		hs.put(curchar, 1);
        	}
        }
        for(int i = 0; i < t.length();i++)
        {
        	char curchar = t.charAt(i);
        	if(hs.containsKey(curchar))
        	{
        		int tem = hs.get(curchar);
        		if(tem == 0)
        			return false;
        		else if(tem == 1)
        			hs.remove(curchar);
        		else
        		{
        			hs.put(curchar, tem-1);
        		}
        	}
        	else
        		return false;
        }
        if(hs.isEmpty())
        	return true;
        else
        	return false;
    }
}