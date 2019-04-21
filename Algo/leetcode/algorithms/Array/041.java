public class Solution {
    public int firstMissingPositive(int[] nums) {
    	int curr = 0;
    	int tem = 0;
        for(int i = 0; i < nums.length;)
        {
        	curr = nums[i];
        	if(curr != i+1 && curr > 0 && curr-1<nums.length && curr != nums[curr-1])
        	{
        		tem = nums[curr - 1];;
        		nums[curr-1] = curr;
        		nums[i] = tem;
        	}
        	else
        		i++;
        }
        for(int i = 0; i< nums.length;i++)
        {
        	if(nums[i] != i+1)
        		return i+1;
        }
        return nums.length+1;     
    }
}