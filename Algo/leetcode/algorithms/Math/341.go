func integerBreak(n int) int {
    if n < 4 {
        return n - 1
    }else if n % 3 == 0 {
        return int(math.Pow(3, float64(n/3)))
    } else if n % 3 == 1 {
        return int(math.Pow(3, float64((n-3)/3)) * 4)
    } else {
        return int(math.Pow(3, float64((n-2)/3))*2)
    }
}
