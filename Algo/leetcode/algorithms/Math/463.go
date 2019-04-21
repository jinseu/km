func islandPerimeter(grid [][]int) int {
    perim := 0
    for i := 0; i < len(grid); i++ {
        for j:= 0; j < len(grid[0]); j++ {
            if grid[i][j] == 1 {
                perim += 4
            } else {
                continue
            }
            
            if i >= 1 && grid[i-1][j] == 1 {
                perim -= 1
            } 
            if i < len(grid) - 1 && grid[i+1][j] == 1{
                perim -= 1
            }
            if j >= 1 && grid[i][j-1] == 1 {
                perim -= 1
            }
            if j < len(grid[0]) - 1 && grid[i][j+1] == 1{
                perim -= 1
            } 
        }
    }
    return perim
    
}
