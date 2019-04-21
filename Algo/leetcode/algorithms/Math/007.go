import "math"

var INT_MAX = int(math.Pow(2, 31)) - 1
var INT_MIN = int(math.Pow(2, 31)) * -1

func reverse(x int) int {
    flag := 1
    if x < 0 {
        flag = -1
        x = -1 * x
    }
    stack := make([]int, 0)
    for ; x> 0; {
        stack = append(stack, x%10)
        x = x / 10  
    }
    var res int
    for i := 0; i < len(stack); i++ {
        res *= 10
        res += stack[i]
        
    }
    res *= flag
    if res > INT_MAX || res < INT_MIN {
        return 0
    }
    return int(res)
}
