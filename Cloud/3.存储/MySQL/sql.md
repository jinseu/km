### 经典例子

#### 1. 获取第二大的某个值

```
# 第一种方法,比较通用，可以获取第n大，但是如果只有一行，那么就会得到empty set
SELECT DISTINCT(Salary) as SecondHighestSalary FROM Employee ORDER BY Salary DESC LIMIT n,1
# 第二种方法，可以如果只有一行，会返回NULL，此处需要注意，max函数对结果的影响。
SELECT MAX(Salary) AS SecondHighestSalary
  FROM Employee
 WHERE Salary < (SELECT MAX(Salary)
                 FROM Employee); 
```

#### 2. MySQL 查看配置

```
SHOW VARIABLES LIKE '%innodb_autoinc_lock_mode%';
```


#### 3. MySQL 查看索引与key

```
show index from tbl_name;
show key from tbl_name
```


#### 时间按一定区间聚集聚集

可以使用`HOUR`, `MINUTE`函数

```
GROUP BY HOUR(date), MINUTE(date)
```