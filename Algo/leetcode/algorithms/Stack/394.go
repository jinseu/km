import "strconv"

func repeatString(s []byte, k int) []byte {
    res := make([]byte, len(s) * k)
    for i := 0; i < len(res); i ++ {
        res[i] = s[i%len(s)]
    }
    return res
}

type level struct {
    k int
    content []byte   
}

func decodeString(s string) string {
    if len(s) == 0 {
        return ""
    }
    resStack := make([]*level, len(s))
    resStack[0] = &level{
        k: 1,
        content: make([]byte, 0),
    }
    top := 0
    number := 0
    
    
    for i := 0; i < len(s);  {
        if s[i] <= '9' && s[i] >= '0' {
            number = i
            for ;; {
                if s[i] <= '9' && s[i] >= '0' {
                    i ++
                } else {
                    break 
                }
            }
            continue
        } else if s[i] == ']' {         
            current := repeatString(resStack[top].content, resStack[top].k)
            top --
            resStack[top].content = append(resStack[top].content, current...)
            number = i + 1
        } else if s[i] == '[' {
            k, err := strconv.Atoi(string(s[number:i]))
            
            if err != nil {
                fmt.Printf("%s\n", err.Error())
            }
            top ++
            resStack[top] = &level{
                k: k,
                content: make([]byte, 0),
            }
            
            fmt.Printf("k:%d \n", k)
        } else {
            resStack[top].content = append(resStack[top].content, s[i])
        }
        i ++
    }
    return string(resStack[0].content)
}
