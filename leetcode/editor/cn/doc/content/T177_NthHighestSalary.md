<p>表:&nbsp;<code>Employee</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
在 SQL 中，id 是该表的主键。
该表的每一行都包含有关员工工资的信息。
</pre>

<p>&nbsp;</p>

<p>查询&nbsp;<code>Employee</code> 表中第 <code>n</code> 高的工资。如果没有第 <code>n</code> 个最高工资，查询结果应该为&nbsp;<code>null</code> 。</p>

<p>查询结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee table:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
n = 2
<strong>输出:</strong> 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
n = 2
<strong>输出:</strong> 
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+</pre>

<div><li>👍 795</li><li>👎 0</li></div>

在 SQL 中，查询第 `n` 高的薪水通常可以使用 `LIMIT` 结合 `OFFSET` 来实现。为了确保我们可以获取第 `n` 高的薪水，可以使用以下方法。

### 解决方案：
1. **使用 `DISTINCT`**：首先，我们要使用 `DISTINCT` 来获取所有不同的薪水，防止重复的薪水影响排名。
2. **排序**：然后按薪水进行降序排序。
3. **返回第 `n` 高的薪水**：通过使用 `LIMIT` 和 `OFFSET` 来选择第 `n` 高的薪水。
4. **处理不存在第 `n` 高薪水的情况**：如果 `n` 大于表中不同薪水的数量，则返回 `null`。

### SQL 查询：

```sql
SELECT 
    (
        SELECT DISTINCT salary
        FROM Employee
        ORDER BY salary DESC
        LIMIT 1 OFFSET n-1
    ) AS getNthHighestSalary;
```

### 解释：
- **`DISTINCT salary`**：去重薪水，确保每个薪水只计算一次。
- **`ORDER BY salary DESC`**：按薪水从高到低排序。
- **`LIMIT 1 OFFSET n-1`**：`LIMIT 1` 只取一条记录，`OFFSET n-1` 跳过前 `n-1` 条记录，从而取到第 `n` 高的薪水。如果第 `n` 高薪水不存在，返回 `null`。
- 由于如果没有足够的不同薪水，`LIMIT` 返回空结果，所以可以返回 `null`。

### 示例 1：

输入：

```
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

`n = 2`

查询结果：

```
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+
```

### 示例 2：

输入：

```
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
```

`n = 2`

查询结果：

```
+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| null                   |
+------------------------+
```

### 进一步优化：
- 如果表格非常大并且 `n` 是一个较大的值，使用 `LIMIT` 和 `OFFSET` 可能会影响性能。在这种情况下，您也可以考虑使用窗口函数（`ROW_NUMBER()`）来优化查询。

### 使用窗口函数（如果数据库支持）：

```sql
WITH RankedSalaries AS (
    SELECT DISTINCT salary, ROW_NUMBER() OVER (ORDER BY salary DESC) AS row_num
    FROM Employee
)
SELECT salary AS getNthHighestSalary
FROM RankedSalaries
WHERE row_num = n;
```

### 解释：
- **`ROW_NUMBER()`**：为每个不同的薪水分配一个唯一的行号，从 1 开始，按薪水从高到低排序。
- **`WHERE row_num = n`**：通过行号 `row_num` 来选择第 `n` 高的薪水。

这种方法在大表中通常性能更好，因为它避免了使用 `LIMIT` 和 `OFFSET` 的扫描方式。