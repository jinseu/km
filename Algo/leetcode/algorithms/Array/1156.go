type Pair struct {
    Key rune
    Cnt int
}

func IntMax(a, b int) int {
    if a < b {
        return b
    }
    return a
}

func IntMin(a, b int) int {
    if a > b {
        return b
    }
    return a
}

func maxRepOpt1(text string) int {
    if len(text) == 0 {
        return 0
    }
    pairList := make([]Pair, 0)
    charCntMap := make(map[rune]int)
    var curKey rune
    curCnt := 0
    for i, v := range text {
        if v == curKey {
            curCnt ++
        } else {
            pairList = append(pairList, Pair{
                Key: curKey,
                Cnt: curCnt,
            } )
            if cnt, exist := charCntMap[curKey]; exist {
                charCntMap[curKey] = cnt + curCnt
            } else {
                charCntMap[curKey] = curCnt
            }
            curKey = v
            curCnt = 1
        }
        if i == len(text) - 1 {
            pairList = append(pairList, Pair{
                Key: curKey,
                Cnt: curCnt,
            } )
            if cnt, exist := charCntMap[curKey]; exist {
                charCntMap[curKey] = cnt + curCnt
            } else {
                charCntMap[curKey] = curCnt
            }
        }
    }
    res := 0
    for i := 0; i < len(pairList); i ++ {
        cur := pairList[i]
        res = IntMax(res, IntMin(cur.Cnt + 1, charCntMap[cur.Key]))
        if i < len(pairList) - 2 {
            if pairList[i+1].Cnt == 1 && pairList[i+2].Key == cur.Key {
                res = IntMax(res, IntMin(cur.Cnt + pairList[i+2].Cnt + 1, charCntMap[cur.Key]))
            }
        }
    }
    return res
}
