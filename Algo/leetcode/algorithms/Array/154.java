public class Solution {
    public int findMin(int[] num) 
    {
        return find(num,0,num.length - 1);
    }
    public int find(int[] num,int l,int r)
    {
    	if(l == r)
    		return num[l];
    	if(num[l] < num[r])
    	{
    		return num[l];
    	}
    	else
    	{
    		int mid = (l + r)/2;
    		int lm = find(num,l,mid);
    		int rm = find(num,mid+1,r);
    		return Math.min(lm, rm);
    	}
    }
}