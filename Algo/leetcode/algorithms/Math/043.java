public class Solution {
    public String multiply(String num1, String num2) {
    	boolean flag = true;
        if(num1.charAt(0) == '-' && num2.charAt(0) != '-')
        {
        	flag = false;
        	num1 = num1.substring(1);
        }
        if(num2.charAt(0) == '-' && num1.charAt(0) != '-')
        {
        	flag = false;
        	num2 = num2.substring(1);
        }
        if(num2.charAt(0) == '-' && num1.charAt(0) == '-')
        {
        	flag = true;
        	num1 = num1.substring(1);
        	num2 = num2.substring(1);
        }        
        //StringBuilder sb = new StringBuilder();
        char[] arr = new char[num1.length() + num2.length() +1];
        for(int w = 0;w < arr.length;w++)
        {
        	arr[w] = '0';
        }
        int k1 = 0;
        int k2 = 0;
        int k3 = 0;
        int carry = 0;
        int res = 0;
        for(int i = num1.length()-1;i>=0;i--)
        {
        	k1 = num1.charAt(i) - '0';
        	for(int j = num2.length()-1;j>=0;j--)
        	{
        		k2 = num2.charAt(j) - '0';
        		k3 = arr[i + j + 2] -'0';
        		res = k1*k2 + carry + k3;
        		carry = res/10;
        		arr[i + j + 2] = (char) (res % 10 + '0');       		
        	}
        	while(carry != 0)
        	{
        		arr[i+1] = (char) ((carry%10)+'0');
        		carry = carry/10;
        	}
        }
        int pos = 0;
        while(pos < arr.length-1 && arr[pos] == '0')
        {
        	pos++;
        }
        if(flag == false)
        {
        	arr[pos-1] = '-';
        	pos--;
        }
        
        return new String(arr,pos,arr.length-pos);       
    }
}