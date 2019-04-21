/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 * Runtime: 532 ms
 */
public class Solution {
    public void recoverTree(TreeNode root) {
		
    	TreeNode[] buffer = new TreeNode[2];
    	TreeNode pre = null;
    	TreeNode cu;
    	boolean find = false;
    	boolean ans = false;
    	
    	if(root == null)
    		return ;    	
    	Stack<TreeNode> ts = new Stack<TreeNode>();
    	TreeNode current = root;

    	while(!ts.isEmpty() || current != null)
    	{
            while(current != null)
            {  
                ts.push(current);  
                current = current.left;  
            }  
            current = ts.peek();
              
            if(find == true && pre.val >= current.val)//有可能是两个相邻数字互换，只会出现一次逆序
            {
            	if(ans == false)
            	{
                	buffer[0] = pre;
            		buffer[1] = current;
            		ans = true;
            	}
            	else
            	{
            		buffer[1] = current;
            	}
            } 
            else if(find == false)//处理只有一个根节点时候出现的异常，事实上，应该在前文做判断，这里，每次循环都做判断，影响了性能
            	find = true;
            pre = current;
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
        if(buffer[0] != null)
        {
        	int tem = buffer[0].val;
        	buffer[0].val = buffer[1].val;
        	buffer[1].val = tem;
        }     
    }
}