/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
	Queue<TreeLinkNode> que;
    public void connect(TreeLinkNode root) 
    {
    	que = new LinkedList<TreeLinkNode>();
    	if(root == null)
    		return ;
    	root.next = null;
    	que.add(root);
    	LFS();
    	return ;
    }
	private void LFS() {
		int count = que.size();
		TreeLinkNode curr = null;
		TreeLinkNode prev = null;
		while(count>0)
		{
			prev = curr;
			curr = que.poll();
			if(prev != null)
			{
				prev.next = curr;
			}
			if(curr.left != null)
			{
				que.add(curr.left);
			}
			if(curr.right != null)
			{
				que.add(curr.right);
			}
			count--;
		}
		curr.next = null;
		if(que.size() > 0)
		{
		   LFS();
		}
		
	}
}