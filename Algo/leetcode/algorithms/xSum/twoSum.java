public class Solution {
    public int[] twoSum(int[] nums, int target) {
    	HashMap<Integer,Integer> hs = new HashMap<Integer,Integer>();
    	for(int i = 0;i < nums.length;i++)
    		hs.put(nums[i], i);
        Arrays.sort(nums);
        int i = 0;
        int j = nums.length-1;
        while(i <= j)
        {
            if(nums[i] + nums[j] > target)
                j--;
            else if(nums[i] + nums[j] < target)
                i++;
            else
            {
                int[] res = new int[2];
                res[0] = hs.get(nums[i])+1;
                res[1] = hs.get(nums[j])+1;
                return res;
            }
        }
        return null;
    }
}