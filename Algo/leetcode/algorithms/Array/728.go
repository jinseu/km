func selfDividingNumbers(left int, right int) []int {
	res := make([]int, 0, right-left+1)
	for ; left <= right; left++ {
		curr := left
		flag := true
		for curr > 0 {
			divideNumber := curr % 10
			if divideNumber == 0 || left%divideNumber != 0 {
				flag = false
				break
			}
			curr = curr / 10
		}
		if flag {
			res = append(res, left)
		}
	}

	return res
}
