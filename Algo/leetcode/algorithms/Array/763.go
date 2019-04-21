func partitionLabels(S string) []int {
    indexMap := make(map[byte]*[2]int)
    indexSorted := make([]*[2]int, 0)
    for i, v := range []byte(S) {
        item, ok := indexMap[v]
        if ok {
            item[1] = i
        } else {
            item = &[2]int{i, i}
            indexMap[v] = item
            indexSorted = append(indexSorted, item)
        }
    }
    res := make([]int, 0)

    currentStart := indexSorted[0][0]
    currentEnd := indexSorted[0][1]

    for i:=1; i < len(indexSorted); i++ {
        vs := indexSorted[i]
        if vs[0] > currentEnd {
            res = append(res, currentEnd - currentStart + 1)
            currentStart = vs[0]
            currentEnd = vs[1]
        } else if vs[1] > currentEnd {
            currentEnd = vs[1]
        }
    }
    res = append(res, currentEnd - currentStart + 1)
    return res
}
