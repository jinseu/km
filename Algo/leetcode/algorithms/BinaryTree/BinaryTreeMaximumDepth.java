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
	int maxdepth;
    public int maxDepth(TreeNode root) {
    	maxdepth = 0;
        depth(root,0);
        return maxdepth;
    }
	private void depth(TreeNode root, int i) {
		if(root != null)
		{
			depth(root.left,i+1);
			depth(root.right,i+1);
		}
		else
		{
			if(i > maxdepth)
				maxdepth = i;
		}
		
	}
}