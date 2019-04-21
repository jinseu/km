class Solution(object):
    def twoSum(self, nums, target):
        snums = sorted(nums)
        i = 0
        j = len(snums) - 1
        while i < j :
            cur = snums[i] + snums[j]
            if cur == target :
                res = list()
                for index, each in enumerate(nums) :
                    if each == snums[i]:
                       res.append(index+1)
                       continue
                    if each == snums[j]:
                       res.append(index+1)
                       continue
                    res = sorted(res)
                return res
            if cur < target :
                i+=1
            if cur > target :
                j-=1
        return None
"""
在几个月后的今天看来，上面的解答，无论是代码的质量还是方法都显得非常业余。
可以用几行代码解决的问题，由于能力的欠缺，用了整整二十行代码来解决。
在该题目中，首先排序并非是必要的。于是就有了下面的解决方案。
"""

class Solution(object):
    def twoSum(self, nums, target):
        num_dict = dict()
        for index, num in enumerate(nums):
            if target - num in num_dict:
                return [num_dict[target - num], index]
            else:
                num_dict[num] = index
        return None
            