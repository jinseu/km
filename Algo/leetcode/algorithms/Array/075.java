public class Solution {
    public void sortColors(int[] nums) {
        int i = 0;
        int j = nums.length-1;
        int k = 0;
        int tem = 0;
        while(k <= j)
        {
        	if(nums[k] == 2)
        	{
        		tem = nums[j];
        		nums[j] = nums[k];
        		nums[k] = tem;
        		j--;
        	}
        	else if(nums[k] == 0)
        	{
        		tem = nums[i];
        		nums[i] = nums[k];
        		nums[k] = tem;
        		i++;
        		k++;
        	}
        	else
        	    k++;
        }        
    }
}