func toLowerCase(str string) string {
	byteArray := []byte(str)
	newByte := make([]byte, len(byteArray))
	for i, v := range byteArray {
		if v <= 'Z' && v >= 'A' {
			v += 'a' - 'A'
		}
		newByte[i] = v
	}
	return string(newByte[:])
}
