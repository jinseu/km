func findComplement(num int) int {
	res := 0
	i := -1
	prefix := 0
	arr := [32]int{}
	for num > 0 {
		i++
		if num&1 > 0 {
			arr[i] = 0
		} else {
			arr[i] = 1
			prefix = i
		}
		num = num >> 1
	}
	for j := prefix; j >= 0; j-- {
		if arr[j] >= 1 {
			res = (res << 1) + 1
		} else {
			res = res << 1
		}
	}
	return res
}
