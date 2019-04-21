import math

class Solution(object):
    def poorPigs(self, buckets, minutesToDie, minutesToTest):
        """
        :type buckets: int
        :type minutesToDie: int
        :type minutesToTest: int
        :rtype: int
        这个问题的背后是一个很有意思的编码问题，例如有三头猪时，可以在一轮测试8种情况。所以可以得到buckets = pow(r + 1, n)
        """
        return int(math.ceil(math.log(buckets, 1 + minutesToTest / minutesToDie)))