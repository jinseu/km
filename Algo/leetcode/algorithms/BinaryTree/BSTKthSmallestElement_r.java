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
    int i = 0;
    int kk = 0;
    int res = 0;
    public int kthSmallest(TreeNode root, int k) {
          i = 0;
          kk = k;
          search(root);
          return res;
    }
    public void search(TreeNode root)
    {
        if(root.left != null)
            search(root.left);
        i++;
        if(i == kk)
        {
          res = root.val;
          return ;
        }
        if(root.right != null)
            search(root.right);
    }
}