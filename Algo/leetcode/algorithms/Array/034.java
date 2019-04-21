public class Solution {
    public int[] searchRange(int[] nums, int target) 
    {
		int[] res = new int[2];
		int   mid = searchBin(nums, target);
		int right = searchBin(nums, target + 1);
		int left  = searchBin(nums, target - 1);

		if (mid < 0 || nums[mid] != target) {
			res[0] = -1;
			res[1] = -1;
			return res;
		}
		if(right < 0)
			right = nums.length-1;
		if(left < 0)
			left = 0;
		while (left < mid) {
			if (nums[left] == target)
				break;
			else
				left++;
		}
		while (right > mid) {
			if (nums[right] == target)
				break;
			else
				right--;
		}

		res[0] = left;
		res[1] = right;

		return res;
    }
	public int searchBin(int[] nums, int target) {
		return Arrays.binarySearch(nums, target);
	}
}