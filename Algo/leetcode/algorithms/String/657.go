func judgeCircle(moves string) bool {
    x := 0
    y := 0
    for _, c := range moves {
        if c == 'U' {
            y ++
        }
        if c == 'D' {
            y --
        }
        if c == 'L' {
            x ++
        }
        if c == 'R' {
            x --
        }
    }
    return x == 0 && y == 0
}
