/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 * Runtime: 12 ms
 */
class Solution {
public:
	int res;
	bool flag;
    bool hasPathSum(TreeNode* root, int sum) {
		if(root == NULL)
			return false;
		flag = false;
        res = sum;
		currSum(root,0);
		return flag;
    }
	void currSum(TreeNode* root, int curr)
	{
		if(flag)
			return;
		if(root->left==NULL && root->right ==NULL)
		{
			curr += root->val;
			if(curr == res)
				flag = true;
			return ;
		}
		if(root->left != NULL)
		{
			currSum(root->left,curr+root->val);
		}
		if(root->right != NULL)
		{
			currSum(root->right,curr+root->val);
		}
	}
};