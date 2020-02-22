func findDuplicates(nums []int) []int {
    res := make([]int, 0)
    for i := 0; i < len(nums);  {
        if nums[i] == -1 {
            i++
        } else if nums[i] == nums[nums[i] - 1]{
            if i != nums[i]-1 {
                res = append(res, nums[i])
                nums[i] = -1
            }
            i++
        } else {
            nums[i], nums[nums[i] - 1] = nums[nums[i] - 1], nums[i]
        }
    }
    return res
}
