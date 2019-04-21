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
	public HashMap<Integer,Integer> hs;
	public int find(int tar)
	{
		return hs.get(tar);
	}
	public TreeNode buildSubTree(int[] inorder,int left,int[] preorder, int pleft, int l)
	{
		   if(l == 0)
		   {
			   return null;
		   }
		   int rootval = preorder[pleft];
		   TreeNode root = new TreeNode(rootval);
	       if(l == 1)
	       {
	    	   return root;
	       }
	       int index = find(rootval);
	       int llength = index - left;
	       root.left  = buildSubTree(inorder,left,preorder,pleft+1,llength);
	       root.right = buildSubTree(inorder,index + 1,preorder,pleft + llength + 1,l - llength - 1);
	       return root;
	       
	}
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
    	  hs = new HashMap<Integer,Integer>();
    	  int length = preorder.length;
    	  for(int i = 0;i< length;i++)
    		  hs.put(inorder[i], i);
    	  if(length == 0){
    		  return null;
    	  }
          int rootval = preorder[0];
          TreeNode root = new TreeNode(rootval);
    	  if(length == 1)
    	  {
    		  return root;
    	  }
          int index = find(rootval);
          root.left  = buildSubTree(inorder,0,preorder,1,index);
          root.right = buildSubTree(inorder,index + 1,preorder,index+1,inorder.length - index -1);
          return root;
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
    public TreeNode buildTree(int[] preorder, int[] inorder) 
    {
    if(preorder==null&&inorder==null||preorder.length==0) {
      return null;
    }

    TreeNode root	 = new TreeNode(preorder[0]);

    int i = 0;
    for(i=0;i<inorder.length;i++) {
      if(inorder[i]==preorder[0]) {
        break;
      }
    }
    
    int[] new_inorder_left, new_preorder_left, new_inorder_right, new_preorder_right;
    
    if(i<inorder.length) {
      //生成左子树时需要用到的inorder
      new_inorder_left = new int[i];
      System.arraycopy(inorder, 0, new_inorder_left, 0, i);
      //生成左子树时需要用到的preorder
      new_preorder_left = new int[i];
      System.arraycopy(preorder, 1, new_preorder_left, 0, i);
      root.left = buildTree(new_preorder_left, new_inorder_left);
      
      //生成右子树时需要用到的inorder
      new_inorder_right = new int[preorder.length - i - 1];
      System.arraycopy(inorder, i + 1, new_inorder_right,  0,  preorder.length - i - 1);
      //生成右子树时需要用到的preorder
      new_preorder_right = new int[preorder.length - i - 1];
      System.arraycopy(preorder, 1 + i, new_preorder_right, 0, preorder.length - i - 1);
      root.right = buildTree(new_preorder_right, new_inorder_right);
    }
    
    return root;
    }
}