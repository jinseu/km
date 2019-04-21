class Solution(object):
    def repeatedSubstringPattern(self, str):
        """
        :type str: str
        :rtype: bool
        """
        size = len(str)
        for x in range(1, size / 2 + 1):
            if size % x:
                continue
            if str[:x] * (size / x) == str:
                return True
        return False