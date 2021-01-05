func makeGood(s string) string {
	arr := make([]byte, len(s))
	index := 0
	if len(s) == 0 {
		return s
	}
	arr[0] = s[0]
	tem := []byte("aA")
	d := tem[0] - tem[1]
	for i := 1; i < len(s); i++ {
		if index >= 0 && (arr[index]-d == s[i] || s[i]-d == arr[index]) {
			index--
		} else {
			index++
			arr[index] = s[i]
		}
	}
	return string(arr[0 : index+1])
}