public class Solution {
    public List<String> anagrams(String[] strs) {
       HashSet<String> hs = new HashSet<String>();
       HashMap<String,String> hs2 = new HashMap<String,String>();
       List<String> res = new ArrayList<String>();
       for(int i = 0;i < strs.length;i++)
       {
    	   char[] temp = strs[i].toCharArray();
    	   Arrays.sort(temp);
    	   String strt = new String(temp);
    	   if(hs.contains(strt))
    	   {
    		   res.add(strs[i]);
    	   }
    	   else if(hs2.containsKey(strt))
    	   {
    		   res.add(hs2.get(strt));
    		   res.add(strs[i]);
    		   hs2.remove(strt);
    		   hs.add(strt);
    	   }
    	   else
    	   {
    		   hs2.put(strt, strs[i]);
    	   }
       }
       return res;        
    }
}