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
    public List<Integer> postorderTraversal(TreeNode root) 
    {
    	List<Integer> ls = new ArrayList<Integer>();
    	if(root == null)
    		return ls;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	TreeNode output = null;
    	//ts.push(current);
    	
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();  
  
            if(current.right == null || (output == current.right && output != null))
            {  
                ls.add(current.val);  
                output = current;  
                ts.pop();  
                current = null;  
            }  
            else
            {  
            	current = current.right;  
            }  
    	}
    	
        return ls; 
    }
}