func numberOfLines(widths []int, S string) []int {
    current := 0
    line := 1
    for _, v := range S {
        index := int(v-'a')
        
        if current + widths[index] > 100 {
            line += 1
            current = widths[index]
        } else {
            current += widths[index]
        }
    }
    result := make([]int, 2)
    result[0] = line
    result[1] = current
    return result
}
