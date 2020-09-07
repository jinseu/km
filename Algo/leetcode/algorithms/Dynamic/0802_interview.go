func DFS(res [][]int, top int, obstacleGrid [][]int, r int, c int, visit [][]int) int {
        topItem := res[top]
        if topItem[0] == r - 1 && topItem[1] == c - 1 {
            return top
        }
        if visit[topItem[0]][topItem[1]] == 1 {
            return -1
        }
        visit[topItem[0]][topItem[1]] = 1
        if topItem[0] + 1 < r && obstacleGrid[topItem[0]+1][topItem[1]] != 1 {
            res[top+1] = []int{topItem[0] + 1, topItem[1]}
            if res := DFS(res, top + 1, obstacleGrid, r, c, visit); res >= 0  {
                return res
            }
        } 
        if topItem[1] + 1 < c && obstacleGrid[topItem[0]][topItem[1]+1] != 1 {
            res[top+1] = []int{topItem[0], topItem[1] + 1}
            if res := DFS(res, top + 1, obstacleGrid, r, c, visit); res >= 0  {
                return res
            }
        }
        return -1
}

func pathWithObstacles(obstacleGrid [][]int) [][]int {
    r := len(obstacleGrid)
    c := len(obstacleGrid[0])
    res := make([][]int, c + r)
    visit := make([][]int, r)
    for i := 0; i < r; i++{
        visit[i] = make([]int, c)
    }

    top := 0
    res[top] = []int{0,0}
    if obstacleGrid[0][0] == 1 || obstacleGrid[r - 1][c - 1] == 1{
        return res[0:0]
    }
    top = DFS(res, top, obstacleGrid, r, c, visit)
    
    return res[0:top + 1]
}
