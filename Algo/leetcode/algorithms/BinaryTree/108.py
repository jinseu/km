# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def sortedArrayToBST(self, nums):
        """
        :type nums: List[int]
        :rtype: TreeNode
        """
        if len(nums) == 0:
            return None
        mid = len(nums)/2
        r = TreeNode()
        r.val = nums[mid]
        r.left = self.sortedArrayToBST(nums[0:mid])
        r.right = self.sortedArrayToBST(nums[mid+1:])
        return r
