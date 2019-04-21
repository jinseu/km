func countPrimeSetBits(L int, R int) int {
    count := 0
    for i := L; i <= R; i++ {
        temp := 1
        bits := 0
        for ; temp <= i; {
            if temp & i > 0 {
                bits ++
            }
            temp <<= 1
        }
        switch (bits){
            case 2, 3, 5, 7, 11, 13, 17, 19:
                count ++
        }
    }
    return count
}
