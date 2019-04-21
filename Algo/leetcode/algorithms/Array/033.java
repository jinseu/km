public class Solution {
    public int search(int[] nums, int target) {
    	if(0 == nums.length) 
    		return -1;
        int left  = 0; 
        int right = nums.length - 1;
        while(left <= right)
        {
            int midle = (left + right) >> 1;
            if(nums[midle] == target) return midle;
            if(nums[left] <= nums[midle])
            {
                if(nums[left] <= target && target  < nums[midle])
                    right = midle - 1;
                else
                    left = midle + 1;
            }
            else
            {
                if(nums[midle] < target && target <= nums[right])
                    left = midle + 1;
                else 
                    right = midle - 1;
            }
        }
        return -1;        
    }
}