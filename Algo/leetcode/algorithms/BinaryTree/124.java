/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    int max = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) 
    {
        if(root == null)
        	return 0;
        calcNodeMax(root);        
        return max;
    }
	private int calcNodeMax(TreeNode root) {
		if (root == null)
			return 0;
		int i_left  = calcNodeMax(root.left);
		int i_right = calcNodeMax(root.right);
		int sum = root.val;
		if (i_left > 0)
			sum += i_left;
		if (i_right > 0)
			sum += i_right;
		max = Math.max(sum,max);
		return (Math.max(i_left, i_right) > 0) ? 
				(Math.max(i_left, i_right) + root.val) : root.val;	
	}
}