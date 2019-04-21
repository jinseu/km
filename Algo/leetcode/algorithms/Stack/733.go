import "container/list"

func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
    stack := list.New()
    stack.PushBack([2]int{sr,sc})
    height := len(image[0])
    width := len(image)
    flag := make([][]int, width)
    for i := range flag {
        flag[i] = make([]int, height)
    }
    start := image[sr][sc]
    for ;stack.Len() != 0; {
        currentEle := stack.Front()
        stack.Remove(currentEle)
        current := currentEle.Value.([2]int)
        cx, cy := current[0], current[1]
        image[current[0]][current[1]] = newColor
        flag[current[0]][current[1]] = 1
        if current[1] + 1 < height && image[cx][cy + 1] == start && flag[cx][cy + 1] == 0 {
            stack.PushBack([2]int{cx, cy+1})
        }
        if current[0] + 1 < width && image[cx + 1][cy] == start && flag[cx + 1][cy] == 0 {
            stack.PushBack([2]int{cx + 1, cy})
        }
        if current[1] - 1 >= 0 && image[cx][cy - 1] == start && flag[cx][cy - 1] == 0{
            stack.PushBack([2]int{cx, cy-1})
        }
        if current[0] - 1 >= 0 && image[cx - 1][cy]== start && flag[cx-1][cy] == 0 {
            stack.PushBack([2]int{cx - 1, cy})
        }
    }
    return image
}
