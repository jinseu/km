/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
	int depth = -1;
	ArrayList<Integer> al = new ArrayList<Integer>();
    public List<Integer> rightSideView(TreeNode root) 
    {
        depth = 0;
        DFS(root,1);
        return al;
    }
    public void DFS(TreeNode curr,int d)
    {
    	if(curr == null)
    		return ;
    	if(d > depth)
    	{
    		depth = d;
    		al.add(curr.val);
    	}
    	DFS(curr.right,d+1);
    	DFS(curr.left,d+1);
    }
}