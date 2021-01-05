func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}

func hasGroupsSizeX(deck []int) bool {
	cntMap := make(map[int]int)
	for _, d := range deck {
		if c, exist := cntMap[d]; exist {
			cntMap[d] = c + 1
		} else {
			cntMap[d] = 1
		}
	}
	if len(deck) == 0 {
		return false
	}
	s := cntMap[deck[0]]
	for _, v := range cntMap {
		if v == 1 {
			return false
		}
		s = gcd(s, v)
		if s == 1 {
			return false
		}
	}
	return true
}