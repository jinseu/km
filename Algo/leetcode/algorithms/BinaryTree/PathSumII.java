/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 *      Runtime: 366 ms
 * }
 */
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
    	List<List<Integer>> resll = new ArrayList<List<Integer>>();
        Stack<TreeNode> st = new Stack<TreeNode>();
        Stack<Boolean> left = new Stack<Boolean>();
        Stack<Boolean> right = new Stack<Boolean>();
        TreeNode curr = root;
        boolean cont = false;
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
        	if(res == sum && leaf.left == null && leaf.right == null && cont == false)
        	{
        		    List<Integer> li = new ArrayList<Integer>();
        		    Iterator<TreeNode> ii = st.iterator();
        		    while(ii.hasNext())
        		    	li.add(ii.next().val);
        		    resll.add(li);
        		    cont = true;       		    
        	}
        	else
        	{
                curr = st.peek();  
                
                if(curr.right != null && right.peek() == false)
                {  
                	right.pop();
                	right.push(true);
                    curr = curr.right;
                    cont = false;
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
        return resll;          
    }
}