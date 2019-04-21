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
	LinkedList<TreeNode> qu;
	List<List<Integer>> res;
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    	res = new ArrayList<List<Integer>>();
    	qu = new LinkedList<TreeNode>();
    	if(root != null)
     	   qu.add(root);
     	LFS(true);
     	return res;
    }
	private void LFS(boolean flags) {
		TreeNode curr = null;
		int size = qu.size();
		if(size == 0)
			return ;
		LinkedList<Integer> level = new LinkedList<Integer>();
		LinkedList<TreeNode> tem = new LinkedList<TreeNode>();
		while(size > 0)
		{
			curr = qu.poll();
			if(flags)
				level.add(curr.val);
			else
			   level.addFirst(curr.val);
			
			if(curr.left != null)
				tem.add(curr.left);
			if(curr.right != null)
				tem.add(curr.right);
			size--;			
		}
		res.add(level);	
		qu = tem;
		LFS(!flags);
	}
}