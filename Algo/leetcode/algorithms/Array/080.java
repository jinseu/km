public class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        int j = 0;
        int k = 0;
        if(nums.length == 0)
        	return 0;
        while(i < nums.length - 1)
        {
        	if(nums[i] == nums[i+1])
        	{
        		if(k < 1)
        		{
            		nums[j] = nums[i];
            		i++;
            		j++;
        		    k++;
        		}
        		else
        		{
        			i++;
        		}
        	}
        	else
        	{
        		nums[j] = nums[i];
        		i++;
        		j++;
        		k = 0;
        		
        	}
        }
        nums[j] = nums[i];
        j++;
        return j;         
    }
}