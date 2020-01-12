//也可以使用BFS


func checkIfNearest(grid [][]int, i, j, currentDis int) bool {
    if grid[i][j] == 0 || grid[i][j] > currentDis + 1 {
        grid[i][j] = currentDis + 1
        return true
    }
    return false
}

func caldistence(grid [][]int, i, j, currentDis int) int {
    res := 0
    if i-1 >= 0 {
        if checkIfNearest(grid, i-1, j, currentDis) {
            res ++
        }
    }
    if i + 1 < len(grid) {
        if checkIfNearest(grid, i+1, j, currentDis) {
            res ++
        }
    }
    if j - 1 >= 0 {
        if checkIfNearest(grid, i, j-1, currentDis) {
            res ++
        }
    }
    if j + 1 < len(grid) {
        if checkIfNearest(grid, i, j+1, currentDis) {
            res ++
        }
    }
    return res
}

func maxDistance(grid [][]int) int {
    round := 0
    for {
        changeCnt := 0
        for i := 0; i < len(grid); i++ {
            for j := 0; j < len(grid); j++ {
                if grid[i][j] == round+1 {
                    changeCnt += caldistence(grid, i, j, round+1) 
                }
            }
        }
        if changeCnt == 0 {
            if round == 0 {
                return -1
            }
            return round 
        }
        round ++
    }
    return 0
}
