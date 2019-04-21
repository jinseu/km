func peakIndexInMountainArray(A []int) int {
    index := 0
    for i, j := 0, 1; j < len(A); i, j = i+1, j+1 {
        if A[i] > A[j] {
            index = i
            break
        }
    }
    return index
}
