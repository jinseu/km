class Solution(object):
    def prisonAfterNDays(self, cells, N):
        """
        :type cells: List[int]
        :type N: int
        :rtype: List[int]
        """
        source, tmp = None, [0]*8
        cycle, k = 0, N
        
        while k:
            for i in range(1, 7):
                tmp[i] = int(cells[i-1] == cells[i+1])
            
            if cycle == 0:
                source = tmp[::] 
            elif source == tmp:
                k %= cycle

            cells = tmp[::]
            cycle += 1
            k -= 1
        
        return cells
