**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * Runtime: 333 ms
 */
public class Solution {
    public boolean isSymmetric(TreeNode root) 
    {
        if(root == null)
        	return true;
        if(root.left != null && root.right != null)
           return Symm(root.left,root.right);
        if(root.left == null && root.right == null)
        	return true;
        return false;
        
    }
	private boolean Symm(TreeNode left, TreeNode right) 
	{
		if(left.val != right.val)
			return false;
		if(left.left == null ^ right.right == null)
			return false;
		if(left.right == null ^ right.left == null)
			return false;
		boolean flags = true;
		if(left.left != null && right.right != null)
		    flags = Symm(left.left,right.right);
		if(flags == false)
			return false;
		if(left.right != null && right.left != null)
			return Symm(left.right,right.left);
		return true;
	}
}