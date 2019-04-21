class Solution(object):
    
    def brokenCalc(self, X, Y):
        """
        :type X: int
        :type Y: int
        :rtype: int
        """
        if X > Y:
            return X - Y
        res = 0
        while X < Y:
            if Y % 2 == 0:
                res += 1
                Y = Y/2
            else:
                res += 1
                Y = Y+1
        return res + X - Y
            
                
