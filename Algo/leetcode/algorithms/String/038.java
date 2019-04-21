public class Solution {
    public String countAndSay(int n) 
    {
    	 int i = 1;
    	 if(n <= 0)
    		 return "";
    	 StringBuffer res = new StringBuffer();
    	 while(i <= n)
    	 {
    		 if(i == 1)
    		 {
    			 res.append('1');
    		 }
    		 else
    		 {
    		     res = count(res);

    		 }
    		 i++;
    	 }
    	 return res.toString();
    }   
    	 
	private StringBuffer count(StringBuffer res) 
	{
		int i = 0;
		char curr = res.charAt(0);
		int count =0;
		StringBuffer res2 = new StringBuffer();
		while(i < res.length())
		{
			if(res.charAt(i) == curr)
			{
				count++;
			}
			else
			{
				res2.append(count);
				res2.append(curr);
				
				curr = res.charAt(i);
				count = 1;
			}
			i++;
		}
		res2.append(count);
		res2.append(curr);
		return res2;
	}
}