public class Solution {
    public int evalRPN(String[] tokens) {
        int i = 0;
        int a = 0;
        int b = 0;
        Stack<Integer> snum = new Stack<Integer>();
        while(i < tokens.length)
        {
            if(tokens[i].compareTo("+") == 0)
            {
        		b = snum.pop();
        		a = snum.pop();
        		snum.push(a+b);
            }
        	else if(tokens[i].compareTo("-") == 0)
        	{
        		b = snum.pop();
        		a = snum.pop();
        		snum.push(a-b);
        	}
        	else if(tokens[i].compareTo("*") == 0)
        	{
        		b = snum.pop();
        		a = snum.pop();
        		snum.push(a*b);
        	}
        	else if(tokens[i].compareTo("/") == 0)
        	{
        		b = snum.pop();
        		a = snum.pop();
        		snum.push(a/b);
        	}
        	else
        	{
        		snum.push(Integer.valueOf(tokens[i]));
        	}
            i++;
        }
        return snum.pop();        
    }
}