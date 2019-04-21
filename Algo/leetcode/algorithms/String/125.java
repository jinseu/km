public class Solution {
    public boolean isPalindrome(String s) 
    {
    	int i = 0;
    	int j = s.length()-1;
    	while(j >= i )
    	{
        	while(!isAlpha(s.charAt(i)))
        	{
        		i++;
        		if(i>=s.length())
        			return true;
        	}
        	while(!isAlpha(s.charAt(j)))
        	{
        		j--;
        		if(j<0)
        			return true;
        	}
        	if(equal(s.charAt(i),s.charAt(j)))
        	{
        		i++;
        		j--;
        	}
        	else
        		return false;
    	}
        return true;
    }
    public boolean isAlpha(char a)
    {
    	if(a >= 'A' && a <= 'Z') 
    		return true;
    	else if(a >= 'a' && a <= 'z')
    		return true;
    	else if(a >= '0' && a <= '9')
    		return true;
    	else
    		return false;
    }
    public boolean equal(char a,char b)
    {
    	int c = 0;
    	int d = 0;
    	if(a >= 'A' && a <= 'Z')
    	{
    		c = a + 'a' - 'A';
    	}
    	else
    		c = a;
    	if(b >= 'A' && b <= 'Z')
    	{
    		d = b + 'a' - 'A';
    	}
    	else
    	{
    		d = b;
    	}
    	if(c == d)
    		return true;
    	else
    		return false;
    }
}