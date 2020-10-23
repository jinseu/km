
## 基本命令

### 创建数据库

mongodb创建数据库的语法格式如下：

``` javascript
use DATABASE_NAME
```

如果数据库不存在，则创建并切换到数据库，否则切换到指定数据库。

可以用show dbs或show databases命令查看所有数据库，但是要说明的是，如果数据库中没有数据，那么便不会显示在show dbs的列表中。在插入数据后才会显示。

``` javascript
show dbs 显示所有数据库
```

### Collection

``` javascript 
//显示当前数据库的所有表
show collections
//删除collection全部数据包括collection本身
db.collection.drop()
//删除collection全部数据
db.collection.deleteMany({})
```

### CRUD

**插入数据**

``` javascript
//插入单行
db.collection.insertOne({item: "canvas", qty: 100, tags: ["cotton"], size: { h: 28, w: 35.5, uom: "cm" }}) 
```

### 聚合

**Distinct**

``` javascript
//获取不同arg字段的值，arg字段可以是一个对象，可以是单个值
db.test.distinct("arg")
//获取不同arg对象round字段的值，round字段可以是一个对象，可以是单个值
db.test.distinct("arg.round")
```
