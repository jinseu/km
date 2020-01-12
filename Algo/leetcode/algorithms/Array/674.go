func findLengthOfLCIS(nums []int) int {
    if len(nums) == 0 {
        return 0
    }
    current := nums[0]
    maxLongestInc := 0
    longestInc := 1
    for i := 1; i < len(nums); i++ {
        if nums[i] > current {
            longestInc += 1
        } else {
            if longestInc > maxLongestInc {
                maxLongestInc = longestInc
            }
            longestInc = 1
        }
        current = nums[i]
    }
    if longestInc > maxLongestInc {
        maxLongestInc = longestInc
    }
    return maxLongestInc
}
