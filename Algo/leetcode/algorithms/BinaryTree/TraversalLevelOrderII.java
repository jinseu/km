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
	Stack<List<Integer>> res;
	Queue<TreeNode> qu;
    public List<List<Integer>> levelOrder(TreeNode root) 
    {
    	res = new Stack<List<Integer>>();
    	qu = new LinkedList<TreeNode>();
    	if(root != null)
    	   qu.add(root);
    	LFS();
    	return res;
    }
	private void LFS() {
		TreeNode curr = null;
		int size = qu.size();
		if(size == 0)
			return ;
		ArrayList<Integer> level = new ArrayList<Integer>();
		while(size > 0)
		{
			curr = qu.poll();
			level.add(curr.val);
			if(curr.left != null)
				qu.add(curr.left);
			if(curr.right != null)
				qu.add(curr.right);
			size--;			
		}
		res.add(level);		
		LFS();
	}
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	levelOrder(root);
    	List<List<Integer>> newres = new ArrayList<List<Integer>>();
    	while(!res.isEmpty())
    	{
    		newres.add(res.pop());
    	}
    	return newres;
    }
}