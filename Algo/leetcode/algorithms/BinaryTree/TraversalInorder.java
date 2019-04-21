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
    public List<Integer> inorderTraversal(TreeNode root) {
    	List<Integer> ls = new ArrayList<Integer>();
    	if(root == null)
    		return ls;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
            	//ls.add(current.val);  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();
            ls.add(current.val);
  
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
    	
        return ls;         
    }
}