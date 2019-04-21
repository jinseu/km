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
    public int minDepth(TreeNode root) {
    	if(root == null)
    		return 0;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	TreeNode output = null;
    	int mind = Integer.MAX_VALUE;
    	int d = 0;
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();  
            if(current.left == null && current.right == null)
            {
                d = ts.size();
                if(d < mind)
                	mind = d;
            }
  
            if(current.right == null || (output == current.right && output != null))
            {  
                output = current;  
                ts.pop();  
                current = null;  
            }  
            else
            {  
            	current = current.right;  
            }  
    	}
    	if(root.left == null && root.right == null)
    		return 1;
        return mind;              
    }
}