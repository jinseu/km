
**查看创建库语句**

```
SHOW CREATE TABLE "TABLE_NAME"
```

**更改列**

```
alter table "TABLE_NAME" Change "COLUMN_1" "COLUMN_2" ["DATA_TYPE"];
```

**删除列**

```
ALTER TABLE "TABLE_NAME" DROP COLUMN "COLUMN_1";
```

**新增列**

```

```

**查看索引**

```
show index from "TABLE_NAME"
```

**创建索引**

```
CREATE INDEX "INDEX_NAME" ON "TABLE_NAME" ("COLUMN_1", "COLUMN_2", ...);
```

**创建唯一索引**

```
CREATE UNIQUE INDEX "INDEX_NAME" ON "TABLE_NAME" ("COLUMN_1", "COLUMN_2", ...);
```