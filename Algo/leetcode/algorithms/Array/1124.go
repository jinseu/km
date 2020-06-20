//解法1, 时间复杂度O(n2)
func longestWPI(hours []int) int {
    prefix := make([]int, len(hours))
    current := 0
    for i, hour := range hours {
        if hour > 8 {
            current += 1
        } else {
            current -= 1
        }
        prefix[i] = current
    }
    res := 0
    for i := 0; i < len(hours); i ++ {
        for j := i; j < len(hours); j ++ {
            if i-1 >=0 {
                if prefix[j] - prefix[i-1] > 0 && j - i + 1 > res  {
                    res = j - i + 1
                }
            }  else {
                if prefix[j] > 0 && j + 1 > res {
                    res = j + 1
                }
            } 
        }
    }
    return res
}

//解法2，在解法1的基础上进一步优化，每次计划prefix[j] - prefix[i-1] > 0, 即计算 prefix[i-1] < prefix[j] 的最小的i
func longestWPI(hours []int) int {
    prefix := 0
    prefixMap := make(map[int]int)
    res := 0
    for i, h := range hours {
        if h > 8 {
            prefix += 1
        } else {
            prefix -= 1
        }
        if prefix > 0 {
            res = i+1
        } else {
            if v, exist := prefixMap[prefix-1]; exist {
                if i - v > res {
                    res = i -v 
                }
            }
        }
        
        if _, exist := prefixMap[prefix]; !exist {
            prefixMap[prefix] = i
        }
    }
    return res
}
