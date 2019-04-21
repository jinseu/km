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
    List<Integer> res;
    public List<Integer> preorderTraversal(TreeNode root) {
        res = new ArrayList<Integer>();
        preorder(root);
        return res;
    }
    public void preorder(TreeNode root)
    {
        if(root == null)
           return ;
        else
        {
            res.add(root.val);
            preorder(root.left);
            preorder(root.right);
        }
    }
}
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
   public List<Integer> preorderTraversal(TreeNode root) 
    {
    	List<Integer> ls = new ArrayList<Integer>();
    	if(root == null)
    		return ls;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	//TreeNode output = null;
    	//ts.push(current);
    	
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
            	ls.add(current.val);  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();  
  
            if(current.right != null )//|| (output == current.right && output != null))
            {  
            	ts.pop();
                current = current.right;
            	//ls.add(current.val);
                //ts.push(current);  
                //current = null;  
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