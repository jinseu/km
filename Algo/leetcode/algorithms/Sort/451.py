import heapq

class Solution(object):
    def frequencySort(self, s):
        """
        :type s: str
        :rtype: str
        """
        ch_fre = dict()
        for ch in s:
            if ch in ch_fre:
                ch_fre[ch] += 1
            else:
                ch_fre[ch] = 1
        h = []
        for key, value in ch_fre.iteritems():
            heapq.heappush(h, [value, key])
        ch_list = []
        for i in range(len(h)):
            ch_pair = heapq.heappop(h)
            ch_list.insert(0, ch_pair[1] * ch_pair[0])
        return ''.join(ch_list)
        
        
            
        
