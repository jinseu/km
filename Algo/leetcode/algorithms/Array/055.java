public class Solution {
    public boolean canJump(int[] nums) 
    {
        int end = nums.length;
        if (end == 0)
            return false;
            
        int step = nums[0];
        
        for(int i = 1; i < end; i++)
            if (step > 0)
            {
                step--;
                step = Math.max(step, nums[i]);
            }
            else
                return false;
                
        return true;
        
    }

}