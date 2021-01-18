class Solution(object):
    def longestWord(self, words):
        """
        :type words: List[str]
        :rtype: str
        """
        words.sort()
        record, res = set(), ""
        for word in words:
            if word[:-1] in record or word[:-1] == "":
                if len(word) > len(res): 
                    res = word
                record.add(word)
        return res