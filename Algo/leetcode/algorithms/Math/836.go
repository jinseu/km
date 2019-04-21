
func max(a int, b int) int {
    if a > b {
       return a
    } else {
        return b
    }
}

func min(a int, b int) int {
    if a < b {
       return a
    } else {
        return b
    }
}
func isRectangleOverlap(rec1 []int, rec2 []int) bool {
    maxY := max(rec1[1], rec2[1])
    minY := min(rec1[3], rec2[3])
    maxX := max(rec1[0], rec2[0])
    minX := min(rec1[2], rec2[2])
    flag := false
    if maxY < minY && maxX < minX {
        flag = true      
    }
    return flag
}
