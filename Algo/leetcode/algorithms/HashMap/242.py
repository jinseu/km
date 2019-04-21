class Solution(object):
    def isAnagram(self, s, t):
        """
        :type s: str
        :type t: str
        :rtype: bool
        """
        if len(s) == 0 and len(t) != 0 :
           return False
        if len(s) != 0 and len(t) == 0 :
           return False
        if len(s) == 0 and len(t) == 0 :
           return True
        ls = list();
        for ch in s:
            ls.append(ch)
        for ch in t :
            try :
               ls.remove(ch)
            except :
               return False
        if len(ls) == 0 :
            return True;
        else :
            return False;