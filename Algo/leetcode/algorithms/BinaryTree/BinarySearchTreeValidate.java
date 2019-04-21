/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * Runtime: 376 ms
 * Key:PreOrder
 */
public class Solution {
   public boolean isValidBST(TreeNode root) {
    	if(root == null)
    		return true;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	int cu = Integer.MIN_VALUE;
    	int prev = Integer.MIN_VALUE; 
    	boolean flags = false;
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();
            prev = cu;
            cu = current.val;  
            if(flags == true && prev >= cu)
            	return false;  
            else if(flags == false)
            	flags = true;
            if(current.right != null )
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
    	
        return true; 
    }
 
}