## xpath

XPath 是一门在 XML 文档中查找信息的语言。XPath 用于在 XML 文档中通过元素和属性进行导航。

### 节点

在XPath 中，有七种类型的节点：元素、属性、文本、命名空间、处理指令、注释以及文档（根）节点。XML 文档是被作为节点树来对待的。树的根被称为文档节点或者根节点。

节点之间的关系包括Parent，Children，Sibling（拥有相同的父的节点），Ancestor（某节点的父、父的父，等等），Descendant（某个节点的子，子的子，等等）。

### 基本语法

在xpath中节点是通过沿着路径或者step来选取的。

|nodename | 选取此节点的所有子节点|
|---------|---------------------|
|/|	从根节点选取 |
|//	| 从匹配选择的当前节点选择文档中的节点，而不考虑它们的位置 |
|. | 选取当前节点 |
|.. | 选取当前节点的父节点 |
|@ | 选取属性 |

另外xpath还支持通配符*以及正则表达式。在使用正则表达式时，需要使用特定的函数。

|路径|选取的节点|
|----|--------|
| /bookstore/book[1] | 选取属于 bookstore 子元素的第一个 book 元素 |
| /bookstore/book[last()] | 选取属于 bookstore 子元素的最后一个 book 元素 |
| /bookstore/book[last()-1] | 选取属于 bookstore 子元素的倒数第二个 book 元素 |
| /bookstore/book[position()<3]	| 选取最前面的两个属于 bookstore 元素的子元素的 book 元素 |
| //title[@lang] | 选取所有拥有名为 lang 的属性的 title 元素 |
| //title[@lang='eng'] | 选取所有 title 元素，且这些元素拥有值为 eng 的 lang 属性 |
| /bookstore/book[price>35.00] | 选取 bookstore 元素的所有 book 元素，且其中的 price 元素的值须大于 35.00 |
| /bookstore/book[price>35.00]/title | 选取 bookstore 元素中的 book 元素的所有 title 元素，且其中的 price 元素的值须大于 35.00|

### location steps

location steps有以下三项组成：
1. 轴（axis） 定义所选节点与当前节点之间的树关系
2. 节点测试（node-test）识别某个轴内部的节点
3. 零个或者更多谓语（predicate）更深入地提炼所选的节点集

|路径|选取的节点|
|----|--------|
|child::book | 选取所有属于当前节点的子元素的 book 节点 |
|attribute::lang | 选取当前节点的 lang 属性 |
|child::* | 选取当前节点的所有子元素 |
|attribute::* | 选取当前节点的所有属性 |
|child::text() | 选取当前节点的所有文本子节点 |
|child::node() | 选取当前节点的所有子节点 |
|descendant::book | 选取当前节点的所有 book 后代 |
|ancestor::book | 选择当前节点的所有 book 先辈 |
|ancestor-or-self::book | 选取当前节点的所有 book 先辈以及当前节点（如果此节点是 book 节点）|
|child::*/child::price | 选取当前节点的所有 price 孙节点|

### 运算符

xpath 中支持的运算符包括:
1. ‘|’ 计算两个节点集
2. + ，- ，* ，div  加减乘除
3. =， ！= ，<, <=, > ,>=
4. or and mod（取余）

### 内置函数

http://www.w3school.com.cn/xpath/xpath_functions.asp