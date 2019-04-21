public class Solution {
    public int[] productExceptSelf(int[] nums) {
        int mul = 1;
        int[] res = new int[nums.length];
        int count = 0;
        int index = 0;
        for(int i = 1; i < nums.length;i++)
        {
        	if(nums[i] == 0)
        	{
        		count++;
        		index = i;
        	}
        	else
        	   mul *= nums[i];
        }
        
        if(count > 1)
        	return res;
        if(count == 1)
        {
        	res[index] = mul*nums[0];
        	return res;
        }
        res[0] = mul;
        if(res[0] == 0)
        	return res;
        for(int i = 1; i < nums.length;i++)
        {
        	mul = mul/nums[i] * nums[i-1];
        	res[i] = mul;
        }
        return res;     
    }
}