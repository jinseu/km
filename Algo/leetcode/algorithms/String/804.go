func uniqueMorseRepresentations(words []string) int {
	morseCode := [26]string{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."}
	resultMap := make(map[string]int)
	result := 0
	for _, v := range words {
		current := ""
		for _, c := range v {
			current += morseCode[(int)(c-'a')]
		}
		if v, ok := resultMap[current]; ok {
			resultMap[current] = v + 1
		} else {
			resultMap[current] = 1
			result++
		}
	}
	return result
}
