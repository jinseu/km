/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {

	private Stack<TreeNode> st;
	TreeNode cu;
    public BSTIterator(TreeNode root) 
    {
    	st = new Stack<TreeNode>();
    	cu = root;
        while(cu != null)
        {
        	st.push(cu);
        	cu = cu.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() 
    {
        if(!st.isEmpty() || cu != null)
        	return true;
        else
        	return false;
    }

    /** @return the next smallest number */
    public int next() 
    {
        while(cu != null)
        {   
            st.push(cu);  
            cu = cu.left;  
        } 
        cu = st.peek();  
        int res = cu.val;
        if(cu.right != null )
        {  
        	st.pop();
            cu = cu.right;
        }  
        else
        {  
        	st.pop();
        	cu = null;
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */