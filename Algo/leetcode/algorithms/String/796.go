//方法1，KMP匹配

func getNext(A string) []int {
    lenA := len(A)
    k := -1
    j := 0
    next := make([]int, lenA)
    next[0] = -1
    for ;j < lenA-1; {
        if k == -1 || A[k] == A[j] {
            k++
            j++
            next[j] = k
        } else {
            k = next[k]
        }
    }
    return next
}

func rotateString(A string, B string) bool {
    if len(A) != len(B) {
        return false
    }
    lenA := len(A)
    if lenA == 0 {
        return true
    }
    
    
    flag := true
    next := getNext(B)
    i := 0
    j := 0
    for ; ; {
        if i >= 2*lenA {
            break
        }
        if j == -1 || A[i%lenA] == B[j] {
            i++;
            j++;
            if j == lenA{
                flag = true
                break
            }
        } else {
            flag = false
            j = next[j]
        }
    }
    return flag
}
// 方法二，暴力匹配

func rotateString(A string, B string) bool {
    if len(A) != len(B) {
        return false
    }
    lenA := len(A)
    lenB := len(B)
    flag := true
    OUT:
    for current := 0; current < lenA; current++ {
        i := current
        j := 0
        for ; ; {
            if A[i%lenA] == B[j] {
                i++;
                j++;
                if j == lenB{
                    flag = true
                    break OUT
                }
            } else {
                flag = false
                break
            }
        }
    }
    return flag
}
