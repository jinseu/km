//本质上是一个数学问题，连续的整数可以看做是一个阶为1的等差数列，然后略加计算即可得到答案
func consecutiveNumbersSum(N int) int {
    res := 1
    maxK := int(math.Sqrt(float64(2 * N)))
    for i := 2; i <= maxK; i++ {
        if (N - i * (i - 1) / 2) % i == 0 {
            res++
        }
    }
    return res
}
