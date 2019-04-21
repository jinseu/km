func transpose(A [][]int) [][]int {
    res := make([][]int, len(A[0]))
    i := 0
    j := 0
    for i = 0; i < len(res); i++ {
        res[i] = make([]int, len(A))
    }
    for i = 0; i < len(A); i++ {
        for j = 0; j < len(A[0]); j++ {
            res[j][i] = A[i][j]
        }
    }
    return res
}
