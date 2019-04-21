class Solution(object):
    def maxProduct(self, words):
        """
        :type words: List[str]
        :rtype: int
        """
        pre_process = list()
        for word in words:
            pre_word = 0
            for ch in word:
                pre_word |= (1 << (ord(ch) - ord('a')))
            pre_process.append(pre_word)
        max_product = 0
        for idx, val in enumerate(words):
            idx2 = idx + 1
            while idx2 < len(words):
                if pre_process[idx] & pre_process[idx2] == 0 and len(words[idx]) * len(words[idx2]) > max_product:
                    max_product = len(words[idx]) * len(words[idx2])
                idx2 += 1
        return max_product