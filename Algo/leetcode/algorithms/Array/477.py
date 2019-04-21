class Solution(object):
    def totalHammingDistance(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        flag_num = 0x01
        i = 0
        res = 0
        while i < 31:
            m = 0
            n = 0
            for num in nums:
                if num & flag_num > 0:
                    m += 1
                else:
                    n += 1
            res += m * n
            flag_num <<= 1
            i += 1
        return res
