class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        longSubStr = set()
        i = 0
        start = 0
        maxLen = 0
        maxStart = 0
        length = len(s)
        while i < length :
            if s[i] in longSubStr :
                longSubStr.remove(s[start])
                if (i - start) > maxLen:
                   maxStart = start
                   maxLen = i - start 
                start += 1
            else :
                longSubStr.update(s[i])
                i += 1
        if (i - start) > maxLen:
            maxStart = start
            maxLen = i - start
 
        return maxLen
"""
if __name__ == "__main__" :
    sl = Solution()
    res = sl.lengthOfLongestSubstring("a")
    print res
"""
