public class Solution {
    public String addBinary(String a, String b) 
    {
    	StringBuffer sb = new StringBuffer();
    	int carry = 0; 
    	int i = a.length() - 1;
    	int j = b.length() - 1;   	
        for(; i >= 0 && j >= 0;i--,j--)
        {
        	int tem = a.charAt(i) - '0' + b.charAt(j) - '0' + carry;
        	if(tem >= 2)
        	{
        		carry = 1;
        		tem = tem % 2;
        	}
        	else
        	{
        		carry = 0;
        	}
        	sb.insert(0, tem);
        }
        String str;
        int k = 0;
        if(i >= 0)
        {
        	str = a;
        	k = i;
        }
        else
        {
        	str = b;
        	k = j;
        }
        for(; k >= 0;k--)
        {
        	int tem = str.charAt(k) - '0' + carry;
        	if(tem >= 2)
        	{
        		carry = 1;
        		tem = tem % 2;
        	}
        	else
        	{
        		carry = 0;
        	}
        	sb.insert(0, tem);        	
        }
        if(carry == 1)
        	sb.insert(0, carry);
        return sb.toString();
    }
}