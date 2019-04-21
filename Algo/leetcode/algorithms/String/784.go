func recursion(result *list.List, s []byte, index int) {

	if index >= len(s) {
		result.PushBack(string(s[:]))
		return
	}
	if s[index] <= 'z' && s[index] >= 'a' {
		recursion(result, s, index+1)
		s[index] = s[index] - ('a' - 'A')
		recursion(result, s, index+1)
	} else if s[index] <= 'Z' && s[index] >= 'A' {
		recursion(result, s, index+1)
		s[index] = s[index] + ('a' - 'A')
		recursion(result, s, index+1)
	} else {
		recursion(result, s, index+1)
	}

}

func letterCasePermutation(S string) []string {
	result := list.New()
	byteArray := []byte(S)
	recursion(result, byteArray, 0)
	resultString := make([]string, result.Len())
	next := result.Front()
	for i := 0; next != nil; i++ {
		resultString[i] = next.Value.(string)
		next = next.Next()
	}
	return resultString
}
