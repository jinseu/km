# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):

    def __init__(self, *args, **kwargs):
        self.res = 0

    def dfs(self, node, current_max):
        if node.val >= current_max:
            self.res += 1
            current_max = node.val
        if node.left:
            self.dfs(node.left, current_max)
        if node.right:
            self.dfs(node.right, current_max)

    def goodNodes(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.dfs(root, -1E5)
        return self.res