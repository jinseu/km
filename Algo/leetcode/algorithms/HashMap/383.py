class Solution(object):
    def canConstruct(self, ransomNote, magazine):
        """
        :type ransomNote: str
        :type magazine: str
        :rtype: bool
        """
        magaDict = {}
        for m in magazine:
            if m in magaDict:
                magaDict[m] = magaDict[m] + 1
            else:
                magaDict[m] = 1
        
        for r in ransomNote:
            if r not in magaDict:
                return False
            else:
                t = magaDict[r]
                if t - 1 < 0:
                    return False
                magaDict[r] = t -1
        return True