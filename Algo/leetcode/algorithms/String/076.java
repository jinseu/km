public class Solution {
    public String minWindow(String s, String t) {
    	if(s.length() == 0 || t.length() == 0)
    		return "";
    	int[] count1 = new int[256];
    	int[] count2 = new int[256];
    	for(int j = 0;j<t.length();j++)
    	{
    		count1[t.charAt(j)]++;
    		count2[t.charAt(j)]++;
    	}
    	int start = 0;
        int minstart = 0;
        int minlen = Integer.MAX_VALUE;
        int count = t.length();
        int i = 0;
        char curr;
    	while(i < s.length())
    	{
    		  curr = s.charAt(i);
    		  if (count2[curr] > 0)
              {
                  count1[curr]--;
                  if (count1[curr] >= 0)
                      count--;
              }
              if (count == 0)
              {
                  while(true)
                  {
                	  curr = s.charAt(start);
                      if (count2[curr] > 0)
                      {
                          if (count1[curr] < 0)
                              count1[curr]++;
                          else
                              break;
                      }
                      start++;
                  }
              
                  if (minlen > i - start + 1)
                  {
                	  minlen = i - start + 1;
                	  minstart = start;
                  }
              }
              i++;
    	}
    	if(count == 0)
    	   return s.substring(minstart, minstart + minlen);
    	else
    		return "";      
    }
}