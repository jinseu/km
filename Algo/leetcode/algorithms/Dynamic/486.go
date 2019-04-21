func Max(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func PredictTheWinner(nums []int) bool {
	if len(nums) == 0 {
		return false
	}
	n := len(nums)
	dp := make([][]int, n)

	for i := 0; i < n; i++ {
		dp[i] = make([]int, n)
		dp[i][i] = nums[i]
	}

	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			dp[i][j] = Max(nums[i]-dp[i+1][j], nums[j]-dp[i][j-1])
		}
	}

	return dp[0][n-1] >= 0
}
