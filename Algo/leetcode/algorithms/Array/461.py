class Solution(object):
    def hammingDistance(self, x, y):
        """
        :type x: int
        :type y: int
        :rtype: int
        """
        i = 0
        ch = 1
        res = 0
        while i< 32:
            if x & ch != y & ch:
                res += 1
            ch = ch << 1
            i += 1
        return res
        
