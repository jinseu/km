public class Solution {
    public List<String> summaryRanges(int[] nums) {
		int start = 0;
		int end = 0;
		List<String> li = new ArrayList<String>();
		if (nums.length == 0)
			return li;
		boolean flags = true;
		for (int k = 0; k < nums.length; k++) {
			if (flags) {
				start = nums[k];
				end = nums[k];
				flags = false;
			} else {
				if ((nums[k] - end) == 1)
					end = nums[k];
				else {
					if (start < end) {
						li.add(new String(start + "->" + end));
						flags = true;
						k--;
					} else {
						li.add(String.valueOf(start));
						flags = true;
						k--;
					}
				}
			}
		}
		if (start < end) {
			li.add(new String(start + "->" + end));
			flags = true;
		} else {
			li.add(String.valueOf(start));
			flags = true;
		}
		return li;        
    }
}