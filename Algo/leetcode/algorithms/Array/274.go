import (
	"sort"
)

// 这个题目不难，但是在边界值的处理上，比较有难度，需要仔细考虑，后续可以多看看
func hIndex(citations []int) int {
	sort.Sort(sort.Reverse(sort.IntSlice(citations)))
	res := 0
	for i, v := range citations {
		if i+1 > v {
			res = i
			break
		}
		res = i + 1
	}
	return res
}