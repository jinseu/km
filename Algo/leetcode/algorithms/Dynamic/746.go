func minCostClimbingStairs(cost []int) int {
    minCost := make([]int, len(cost)+1)
    for i := 0; i < len(minCost); i++ {
        if i == 0 || i == 1 {
            minCost[0] = 0
        } else if minCost[i-1] + cost[i-1] > minCost[i-2] + cost[i-2] {
            minCost[i] = minCost[i-2] + cost[i-2]
        } else{
            minCost[i] = minCost[i-1] + cost[i-1]
        }
    }
    return minCost[len(cost)]
}
