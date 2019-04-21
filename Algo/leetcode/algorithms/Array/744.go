func nextGreatestLetter(letters []byte, target byte) byte {
    smallest := byte(254)
    for _, v := range letters {
        if v > target && v < smallest {
            smallest = v
        }
    }
    if smallest == byte(254) {
        smallest = letters[0]
    }
    return smallest
}


