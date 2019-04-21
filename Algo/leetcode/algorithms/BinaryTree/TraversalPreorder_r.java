/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
    Runtime: 300 ms
 */
public class Solution {
    List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<Integer>();
        preorder(root);
        return res;
    }
    public void preorder(TreeNode root)
    {
        if(root == null)
           return ;
        else
        {
            res.add(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}