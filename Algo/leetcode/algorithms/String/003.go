func lengthOfLongestSubstring(s string) int {
    cMap := make(map[rune]int)
    maxNRLen := 0
    curStart := 0
    curLen := 0
    for i, c := range s {
        if last, exist := cMap[c]; exist {
            curLen = i - curStart
            
            if last >= curStart {
                curStart = last + 1
            } 
            
            if curLen > maxNRLen {
                maxNRLen = curLen
            }
        }
        cMap[c] = i
    }
    curLen = len(s) - curStart
    if curLen > maxNRLen {
        maxNRLen = curLen
    } 
    return maxNRLen
}
