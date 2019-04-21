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
    List<TreeNode> generateTrees(int n) {
        return createTree(1,n);
     }
     
     List<TreeNode> createTree(int start, int end)
     {
    	 List<TreeNode> results = new ArrayList<TreeNode>();;
         if(start>end)
         {
             results.add(null);
             return results;
         }
         
         for(int k=start;k<=end;k++)
         {
        	 List<TreeNode> left = createTree(start,k-1);
        	 List<TreeNode> right = createTree(k+1,end);
             for(int i=0;i<left.size();i++)
             {
                 for(int j=0;j<right.size();j++)
                 {
                     TreeNode root = new TreeNode(k);
                     root.left = left.get(i);
                     root.right = right.get(j);
                     results.add(root);
                 }
             }
         }
         return results;
     }
}