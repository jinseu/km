public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) 
    {
    	List<String> res = new ArrayList<String>();
    	//if(words.length == 0)
    	//	return res;
    	for(int i = 0; i < words.length;)
    	{
    		int j = 0;
    		int k = 0;
    		while(j + Math.max(k-1,0) <= maxWidth)
    		{
    			if(i + k < words.length)
    			{
    			   j += words[i+k].length();
    			   k++;
    			}
    			else
    			{
    				k = 0;
    				break ;
    			}
    		}
    		if(k != 0)
    		{
    		   String str = generate(words,i,k-1,maxWidth);
    	 	   res.add(str);
    		}
    		else
    		{
    			String str = endString(words,i,maxWidth);
    			res.add(str);
    			break ;
    		}
    		i += k-1;
    	}
    	return res;
    }
	private String endString(String[] words, int i,int maxWidth) {
		StringBuilder res = new StringBuilder();
		int ww = 0;
		while( i+ww < words.length)
		{
			if(ww == 0)
				res.append(words[i+ww]);
			else
				res.append(" "+words[i+ww]);
			ww++;
		}
		while(res.length() < maxWidth)
			res.append(' ');
		return res.toString();
	}
	private String generate(String[] words,int i, int k,int maxWidth) {
		StringBuilder res = new StringBuilder();
		for(int ww = 0; ww < k; ww++) 
		{
			if(ww == 0)
			{
			   res.append(words[i+ww]);
			}
			else
			{
				res.append(" "+words[i+ww]);
			}
		}
		int index = 0;
		while(res.length() < maxWidth)
		{
			while(index < res.length() && res.charAt(index) != ' ')
				index++;
			if(index < res.length())
			{
				res.insert(index, ' ');
			}
			else if(index < maxWidth && k == 1)
				res.append(' ');
			while(index < res.length() && res.charAt(index) == ' ')
				index++;
			if(index >= res.length())
				index = 0;
		}
			
		return res.toString();
	}
}