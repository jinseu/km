class Solution(object):
    def groupThePeople(self, groupSizes):
        """
        :type groupSizes: List[int]
        :rtype: List[List[int]]
        """
        sizeMap = {}
        for i, name in enumerate(groupSizes):
            sameSize = sizeMap.setdefault(name, [])
            sameSize.append(i)
        res = []
        for size, sameSizeIndexs in sizeMap.items():
            j = 0
            while j <len(sameSizeIndexs):
                res.append(sameSizeIndexs[j:j+size])
                j += size
        return res
