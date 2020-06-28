# Write your MySQL query statement below
# 这个题目主要就是涉及了一个问题，不能在在同一个sql语句中，先select同一个表的某些值，然后再update这个表。所以需要先引入一个中间表
DELETE FROM Person WHERE Id NOT IN (SELECT min_id FROM (SELECT MIN(id) as min_id FROM Person GROUP BY Email) as a)
