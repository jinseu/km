class Solution(object):
    def containsDuplicate(self, nums):
        """
        :type nums: List[int]
        :rtype: bool
        """
        num_set = set()
        for key in nums:
            if key in num_set:
                return True
            else:
                num_set.add(key)
        return False