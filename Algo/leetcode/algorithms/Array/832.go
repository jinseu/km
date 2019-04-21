func flipAndInvertImage(A [][]int) [][]int {
	if len(A) == 0 {
		return A
	}
	for i := 0; i < len(A); i++ {
		for j := 0; j < (len(A[i])+1)/2; j++ {
			swapIndex := len(A[i]) - j - 1
			A[i][swapIndex], A[i][j] = A[i][j], A[i][swapIndex]
			if swapIndex == j {
				A[i][swapIndex] = (A[i][swapIndex] + 1) % 2
			} else {
				A[i][swapIndex] = (A[i][swapIndex] + 1) % 2
				A[i][j] = (A[i][j] + 1) % 2
			}
		}
	}
	return A
}
