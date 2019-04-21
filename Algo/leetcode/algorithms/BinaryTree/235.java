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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || p == null || q == null)
        	return null;
        TreeNode cu = root;
        while(cu != null)
        {
        	if(Math.max(p.val, q.val) < cu.val )
        		cu = cu.left;
        	else if( Math.min(p.val, q.val) > cu.val)
        		cu = cu.right;
        	else
        		return cu;
        }
        return cu;        
    }
}