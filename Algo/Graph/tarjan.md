### Tarjan 算法解析

#### 背景

一个有向图是强连通的（strongly connected）当且仅当每一对不相同结点 u 和 v 间既存在从 u 到 v 的路径也存在从 v 到 u 的路径。有向图的极大强连通子图（这里指Node数极大）被称为强连通分量。Tarjan 算法就是用来寻找有向图中的强连通分量。

在Terrafrom中，通过tf文件描述的资源会被抽象为一张图，这张图中是不能有环的，有环即意味着有循环依赖，这是Terrafrom不允许的。所以，Terrafrom 在将资源描述转化为图之后，首先会检查是否有环，即是否有强连通分量，此处就用到了Tarjan算法。

下面以Terrafrom中的实现为例，对Tarjan算法做一定的解析。

#### 图的表示

Terra

```
type BasicNode struct {
        Name      string
        NodeEdges []Edge
}

func (b *BasicNode) Edges() []Edge {
        return b.NodeEdges
}

func (b *BasicNode) AddEdge(edge Edge) {
        b.NodeEdges = append(b.NodeEdges, edge)
}

func (b *BasicNode) String() string {
        if b.Name == "" {
                return "Node"
        }
        return fmt.Sprintf("%v", b.Name)
}

// BasicEdge is a digraph Edge that has a name, head and tail
type BasicEdge struct {
        Name     string
        EdgeHead *BasicNode
        EdgeTail *BasicNode
}

func (b *BasicEdge) Head() Node {
        return b.EdgeHead
}

// Tail returns the end point of the Edge
func (b *BasicEdge) Tail() Node {
        return b.EdgeTail
}

func (b *BasicEdge) String() string {
        if b.Name == "" {
                return "Edge"
        }
        return fmt.Sprintf("%v", b.Name)
}
```

#### Tarjan 算法
