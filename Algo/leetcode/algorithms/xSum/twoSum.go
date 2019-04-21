func twoSum(nums []int, target int) []int {
    var numDict = make(map[int] int)
    var res = []int{-1, -1}
    for i := 0 ; i<len(nums); i++  {
        index, ok :=  numDict[target-nums[i]]
        if ok {
            res[0] = index
            res[1] = i
            return res
        }
        numDict[nums[i]] = i
    }
    return res
}
