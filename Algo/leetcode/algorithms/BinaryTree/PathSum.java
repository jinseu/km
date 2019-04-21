/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 *      Runtime: 286 ms
 * }
 */
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        Stack<TreeNode> st = new Stack<TreeNode>();
        Stack<Boolean> left = new Stack<Boolean>();
        Stack<Boolean> right = new Stack<Boolean>();
        TreeNode curr = root;
        int res = 0;
        while(!st.empty() || curr != null)
        {
        	while(curr != null)
        	{
        	    res += curr.val;
        	    st.push(curr);
        	    left.push(true);
        	    right.push(false);
        	    
        	    curr = curr.left;
        	}
        	TreeNode leaf = st.peek();
        	if(res == sum && leaf.left == null && leaf.right == null)
        	{
        		    return true;
        	}
        	else
        	{
                curr = st.peek();  
                
                if(curr.right != null && right.peek() == false)
                {  
                	right.pop();
                	right.push(true);
                    curr = curr.right;
                    //st.push(curr);
                    //res += curr.val;
                    //left.push(true);
                    //right.push(false);
                }  
                else
                {  
                	res -= st.pop().val;
                	left.pop();
                	right.pop();
                	curr = null;
                }
        	}
        }
        return false;
    }
}