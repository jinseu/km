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
    public TreeNode invertTree(TreeNode root) {
        if(root == null)
        	return root;
        invert(root);
        return root;
    }
	private void invert(TreeNode root) {
		if(root == null)
			return ;
		TreeNode tem = root.left;
        root.left    = root.right;
        root.right   = tem;        
        invert(root.left);
        invert(root.right);		
	}
}