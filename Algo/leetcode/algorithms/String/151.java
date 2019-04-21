public class Solution {
    public String reverseWords(String s) {
		StringBuilder res = new StringBuilder();  
        if(s.length()==0)//s="";  
        {  
            return new String("");  
        }  
        String[] arr = s.split(" ");
        if(arr.length == 0)
        {
        	return new String(""); 
        }
        int i = arr.length - 1;
        while(i > 0)
        {
        	if(arr[i].compareTo("") != 0)
        	{
        	   res.append(arr[i]);
        	   res.append(" ");
        	}
        	i--;
        }
        if(arr[i].compareTo("") != 0)
        {
        	res.append(arr[i]);
        }
        else
        {
        	res.deleteCharAt(res.length()-1);
        }
        return res.toString();
    }
}