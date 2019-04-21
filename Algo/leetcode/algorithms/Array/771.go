func numJewelsInStones(J string, S string) int {
    jMap := make(map[byte] int)
    for i:= 0; i < len(J); i++ {
        jMap[J[i]] = 1
    }
    count := 0
    for i := 0; i < len(S); i++ {
        _, ok := jMap[S[i]]
        if ok {
            count += 1
        }
    }
    return count
}
