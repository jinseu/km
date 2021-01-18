class Solution(object):
    def removeDuplicates(self, S):
        """
        :type S: str
        :rtype: str
        """
        if len(S) == 0:
            return S
        stack = ['']*len(S)
        index = -1
        for s in S:
            if index == -1 or stack[index] != s:
                index += 1
                stack[index] = s
            else:
                index -= 1
        return ''.join(stack[0:index+1])

            