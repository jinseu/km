class Solution(object):
    def compare(self, a, b, order):
        len_a = len(a)
        len_b = len(b)
        i = 0
        while i < len_a and i < len_b:
            if order[a[i]] < order[b[i]]:
                return True
            elif order[a[i]] > order[b[i]]:
                return False
            else:
                i += 1
        if len_a < len_b:
            return True
        return False
        
    
    def isAlienSorted(self, words, order):
        """
        :type words: List[str]
        :type order: str
        :rtype: bool
        """
        i = 0
        order_map = {}
        while i < len(order):
            order_map[order[i]] = i
            i += 1
            
        i = 1
        while i < len(words):
            if not self.compare(words[i-1], words[i], order_map):
                return False
            i += 1
        return True
        
