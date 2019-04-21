class Solution(object):
    def isUgly(self, num):
        ugly = [2,3,5]
        if num == 0:
            return False
        for ug in ugly:
            while num % ug == 0:
                num /= ug
        if num == 1:
            return True
        else:
            return False