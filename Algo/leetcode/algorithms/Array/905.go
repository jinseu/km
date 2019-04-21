func sortArrayByParity(A []int) []int {
    i := 0
    j := len(A) - 1
    for i < j {
        if A[i] % 2 == 1 {
            A[i], A[j] = A[j], A[i]
            j --
        } else {
            i ++
        }
    }
    return A
}
