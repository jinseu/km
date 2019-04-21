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
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0)
        	return null;
        int mid = nums.length /2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = DFS(nums,0,mid-1);
        root.right = DFS(nums,mid+1,nums.length-1);
        return root;
    }
	private TreeNode DFS(int[] nums, int i, int j) {
		if(i > j)
		   return null;
		int mid = (i + j)/2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = DFS(nums,i,mid-1);
        root.right = DFS(nums,mid+1,j);
        return root;		
	}
}