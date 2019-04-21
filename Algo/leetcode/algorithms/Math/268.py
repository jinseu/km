class Solution(object):
    def missingNumber(self, nums):
        """
		concept: x ^ x = 0, x ^ 0 = x, x ^ x ^ y = y
        :type nums: List[int]
        :rtype: int
        """
        res = 0
        for x in range(len(nums)+1):
            res = res ^ x
        for x in nums:
            res = res ^ x
        return res