func findShortestSubArray(nums []int) int {
    var eleMap = make(map[int] [3]int)
    maxF := -1
    shortLen := len(nums)
    for i := 0; i < len(nums); i++ {
        eleIndex, ok := eleMap[nums[i]]
        if ok {
            eleIndex[1] = i
            eleIndex[2] += 1
        } else {
            eleIndex = [3]int{i, i, 1}
        }
        eleMap[nums[i]] = eleIndex
        if eleIndex[2] > maxF{
            maxF = eleIndex[2]
            shortLen = eleIndex[1] - eleIndex[0] + 1
        } else if eleIndex[2] == maxF{
            temLen := eleIndex[1] - eleIndex[0] + 1
            if temLen < shortLen {
               shortLen = temLen
            }
       }
    }
    return shortLen
}
