/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution1 {
    public void flatten(TreeNode root) 
    {
        List<TreeNode> res = preorderTrav(root);
        int l = res.size();
        TreeNode curr = null;
        for(int i = 0; i < l;i++)
        {
        	curr = res.get(i);
        	if(i < l-1)
        	{
        	    curr.left  = null;       	
        	    curr.right = res.get(i+1);
        	}
        	else
        	{
        		curr.left = null;
        		curr.right = null;
        	}
        }
    }
    public List<TreeNode> preorderTrav(TreeNode root) 
    {
    	List<TreeNode> ls = new ArrayList<TreeNode>();
    	if(root == null)
    		return ls;
    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;
    	
    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
            	ls.add(current);  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();  
  
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
    	
        return ls;         
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution2 {
    public void flatten(TreeNode root) 
    {
        if(root == null) 
           return;
        if(root.left != null){
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null;
            root.right = left;
            TreeNode rightMost = root.right;
            while(rightMost.right != null){
                rightMost = rightMost.right;
            }
            rightMost.right = right;
        }
        flatten(root.right);
    }
}