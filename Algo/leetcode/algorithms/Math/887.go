func projectionArea(grid [][]int) int {
    if len(grid) == 0 {
        return 0
    }
    countZ := 0
    maxX := make([]int, len(grid))
    maxY := make([]int, len(grid[0]))
    for i := 0; i < len(grid); i++ {
        for j := 0; j < len(grid[i]); j++ {
            if grid[i][j] > maxX[i] {
                maxX[i] = grid[i][j]
            }
            if grid[i][j] > maxY[j] {
                maxY[j] = grid[i][j]
            }
            if grid[i][j] > 0 {
                countZ ++
            }
        }
    }
    for _, v := range maxX {
        countZ += v
    }
    for _, v := range maxY {
        countZ += v
    }
    return countZ
    
}
