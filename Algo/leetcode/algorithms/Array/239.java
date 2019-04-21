public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
		if (nums.length < k)
			return null;
		if(k == 0)
			return new int[]{};
		int[] res = new int[nums.length - k + 1];
		LinkedList<Integer> Q = new LinkedList<Integer>();
		for (int i = 0; i < k; i++) {
			while (!Q.isEmpty() && nums[i] >= nums[Q.getLast()])
				Q.pollLast();
			Q.addLast(i);
		}
		for (int i = k; i < nums.length; i++) {
			res[i - k] = nums[Q.getFirst()];
			while (!Q.isEmpty() && nums[i] >= nums[Q.getLast()])
				Q.pollLast();
			while (!Q.isEmpty() && Q.getFirst() <= i - k)
				Q.pollFirst();
			Q.addLast(i);
		}
		res[nums.length - k] = nums[Q.getFirst()];
		return res;        
    }
}