class Solution(object):
    def isPowerOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        return n > 0 and 1162261467 % n ==0
        
class Solution(object):
    def isPowerOfThree(self, n):
        """
        :type n: int
        :rtype: bool
        """
        while n >= 3:
            m = n%3
            n /= 3
            if m != 0 :
                return False
        
        if n == 1 :
            return True
        else :
            return False