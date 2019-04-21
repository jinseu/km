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
	public int find(int[] num,int s,int tar,int l)
	{
		for(int i = s;i < s + l;i++)
		{
			if(num[i] == tar)
				return i;
		}
		return -1;
	}
	public TreeNode buildSubTree(int[] inorder,int left,int[] postorder, int pleft, int l)
	{
		   //System.out.printf("%d %d %d\n",left,pleft,l);
		   if(l == 0)
		   {
			   return null;
		   }
		   int rootval = postorder[pleft + l - 1];
		   TreeNode root = new TreeNode(rootval);
	       if(l == 1)
	       {
	    	   return root;
	       }
	       int index = find(inorder,left,rootval,l);
	       int llength = index - left;
	       root.left  = buildSubTree(inorder,left,postorder,pleft,index - left);
	       root.right = buildSubTree(inorder,index + 1,postorder,pleft + llength,l - llength - 1);
	       return root;
	       
	}
    public TreeNode buildTree(int[] inorder, int[] postorder) 
    {
    	  int length = postorder.length;
    	  if(length == 0)
    	  {
    		  return null;
    	  }
          int rootval = postorder[length - 1];
          TreeNode root = new TreeNode(rootval);
    	  if(length == 1)
    	  {
    		  return root;
    	  }
          int index = find(inorder,0,rootval,inorder.length);
          root.left  = buildSubTree(inorder,0,postorder,0,index);
          root.right = buildSubTree(inorder,index + 1,postorder,index,inorder.length - index -1);
          return root;
    }
}