func repeatedNTimes(A []int) int {
    for i := 0; i < len(A); i += 2 {
        if A[i] == A[i+1] {
            return A[i]
        }
    }
    if len(A) >= 4 {
        if A[0] == A[2] || A[1]==A[2]{
            return A[2]
        }
        if A[1] == A[3] || A[0] == A[3]{
            return A[3]
        }
    }
    return 0
}
