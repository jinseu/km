package main

import (
    "fmt"
    "sync"
)

type Count struct {
    cc int
}

func Worker(name string, cond *sync.Cond, c *Count, wg *sync.WaitGroup){
    cond.L.Lock()
    defer cond.L.Unlock()
    for ; true; {
        if c.cc >= 10 {
            break
        }
        cond.Signal()
        c.cc += 1
        fmt.Printf("%s %d\n", name, c.cc)
        cond.Wait()
    }
    cond.Signal()
    wg.Done()
}

func main() {
    var wg sync.WaitGroup
    wg.Add(2)
    l := sync.Mutex{}
    cond := sync.NewCond(&l)
    c := &Count{
      cc: 0,
    }
    go Worker("worker-1", cond, c, &wg)
    go Worker("worker-2", cond, c, &wg)
    wg.Wait()
}
