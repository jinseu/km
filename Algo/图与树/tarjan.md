# Tarjan 算法简介

## 背景

一个有向图是强连通的（strongly connected）当且仅当每一对不相同结点 u 和 v 间既存在从 u 到 v 的路径也存在从 v 到 u 的路径。有向图的极大强连通子图（这里指Node数极大）被称为强连通分量。Tarjan 算法就是用来寻找有向图中的强连通分量。

### 算法简介

下面以Go语言为例，介绍tarjan算法

```
func StronglyConnected(g *Graph) [][]Vertex {
        vs := g.Vertices()
        acct := sccAcct{
                NextIndex:   1,
                VertexIndex: make(map[Vertex]int, len(vs)),
        }
        for _, v := range vs {
                // Recurse on any non-visited nodes
                if acct.VertexIndex[v] == 0 {
                        stronglyConnected(&acct, g, v)
                }
        }
        return acct.SCC
}

func stronglyConnected(acct *sccAcct, g *Graph, v Vertex) int {
        // Initial vertex visit
        index := acct.visit(v)
        minIdx := index

        for _, raw := range g.DownEdges(v).List() { //DownEdges returns the outward edges from the source Vertex v.
                target := raw.(Vertex)
                targetIdx := acct.VertexIndex[target]

                // Recurse on successor if not yet visited
                if targetIdx == 0 {
                        minIdx = min(minIdx, stronglyConnected(acct, g, target))
                } else if acct.inStack(target) {
                        // Check if the vertex is in the stack
                        minIdx = min(minIdx, targetIdx)
                }
        }

        // Pop the strongly connected components off the stack if
        // this is a root vertex
        if index == minIdx {
                var scc []Vertex
                for {
                        v2 := acct.pop()
                        scc = append(scc, v2)
                        if v2 == v {
                                break
                        }
                }

                acct.SCC = append(acct.SCC, scc)
        }

        return minIdx
}

func min(a, b int) int {
        if a <= b {
                return a
        }
        return b
}
// sccAcct is used ot pass around accounting information for
// the StronglyConnectedComponents algorithm
type sccAcct struct {
        NextIndex   int
        VertexIndex map[Vertex]int
        Stack       []Vertex
        SCC         [][]Vertex
}

// visit assigns an index and pushes a vertex onto the stack
func (s *sccAcct) visit(v Vertex) int {
        idx := s.NextIndex
        s.VertexIndex[v] = idx
        s.NextIndex++
        s.push(v)
        return idx
}

// push adds a vertex to the stack
func (s *sccAcct) push(n Vertex) {
        s.Stack = append(s.Stack, n)
}

// pop removes a vertex from the stack
func (s *sccAcct) pop() Vertex {
        n := len(s.Stack)
        if n == 0 {
                return nil
        }
        vertex := s.Stack[n-1]
        s.Stack = s.Stack[:n-1]
        return vertex
}

// inStack checks if a vertex is in the stack
func (s *sccAcct) inStack(needle Vertex) bool {
        for _, n := range s.Stack {
                if n == needle {
                        return true
                }
        }
        return false
}
```



### 备注说明

1. 复杂度:对每个节点，过程strongconnect只被调用一次；整个程序中每条边最多被考虑一次。因此算法的运行时间关于图的边数是线性的，即`O(|V|+|E|)`
2. 判断节点v'是否在堆栈中应在常量时间内完成，例如可以对每个节点保存一个是否在堆栈中的标记，本文的实现中，判断是否在堆中，用了遍历的方法，会导致时间复杂度的提升
3. 同一个强连通分量内的节点是无序的，但此算法具有如下性质：每个强连通分量都是在它的所有后继强连通分量被求出之后求得的。因此，如果将同一强连通分量收缩为一个节点而构成一个有向无环图，这些强连通分量被求出的顺序是这一新图的拓扑序的逆序