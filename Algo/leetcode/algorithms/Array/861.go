
func pow(x int) int {
    return 1 << uint(x)
}

func toggleRow(A [][]int, row int){
    for i := 0; i < len(A[row]); i ++ {
        A[row][i] = (A[row][i] + 1) % 2
    }
}

func matrixScore(A [][]int) int {
    res := 0
    for i := 0; i < len(A); i++ {
        if A[i][0] == 0 {
            toggleRow(A, i)
        }
        res += pow(len(A[0]) - 1)
    }
    for j := 1; j < len(A[0]); j++ {
        oneCnt := 0
        for i := 0; i < len(A); i++ {
            if A[i][j] == 1 {
                oneCnt ++
            }
        }
        if oneCnt <= len(A)/2{
            oneCnt = len(A) - oneCnt
        }
        res += oneCnt * pow(len(A[0]) - j - 1)
    }
    return res
}
