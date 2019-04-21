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
    public int kthSmallest(TreeNode root, int k) {
    	if(root == null)
    		return 0;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	int i = 0;
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();
            i++;
            if(i == k)
                return current.val;
  
            if(current.right != null )//|| (output == current.right && output != null))
            {  
            	ts.pop();
                current = current.right;
            }  
            else
            {  
            	ts.pop();
            	current = null;
            }  
    	}
    	
        return 0;           
    }
}