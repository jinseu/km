public class Solution {
    public int[] plusOne(int[] digits) {
        LinkedList<Integer> res = new LinkedList<Integer>();
        int carry = 1;
        int tem = 0;
        for(int i = digits.length-1; i >= 0; i--)
        {
        	tem = digits[i] + carry;
        	if(tem >= 10)
        	{
        		res.addFirst(tem%10);
        		carry = 1;
        	}
        	else
        	{
        		res.addFirst(tem);
        		carry = 0;
        	}
        }
        int[] resa;
        if(carry == 1)
        {
        	res.addFirst(carry);
        	resa = new int[digits.length+1];
        }
        else
        {
        	resa = new int[digits.length];
        }
        Iterator<Integer> it = res.iterator();
        for(int i =0;i< resa.length;i++)
        {
        	resa[i] = it.next();
        }
        return resa;        
    }
}