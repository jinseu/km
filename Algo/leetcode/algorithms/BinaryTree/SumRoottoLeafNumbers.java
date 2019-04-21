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
    int sum;
    void dfs(TreeNode root,int num)
    {
        if(root.left==null && root.right==null) 
           sum+=num;
        if(root.left != null) 
           dfs(root.left,num*10+root.left.val);
        if(root.right != null) 
           dfs(root.right,num*10+root.right.val);
    }
    int sumNumbers(TreeNode root) {
        if(root==null) 
            return 0;
        sum=0;
        int num=0;
        dfs(root,root.val);
        return sum;
    }
}