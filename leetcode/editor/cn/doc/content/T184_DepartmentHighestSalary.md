<p>表：&nbsp;<code>Employee</code></p>

<pre>
+--------------+---------+
| 列名          | 类型    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
在 SQL 中，id是此表的主键。
departmentId 是 Department 表中 id 的外键（在 Pandas 中称为 join key）。
此表的每一行都表示员工的 id、姓名和工资。它还包含他们所在部门的 id。
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Department</code></p>

<pre>
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
在 SQL 中，id 是此表的主键列。
此表的每一行都表示一个部门的 id 及其名称。
</pre>

<p>&nbsp;</p>

<p>查找出每个部门中薪资最高的员工。<br /> 按 <strong>任意顺序</strong> 返回结果表。<br /> 查询结果格式如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<b>输入：</b>
Employee 表:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |
+----+-------+--------+--------------+
Department 表:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
<b>输出：</b>
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+
<strong>解释：</strong>Max 和 Jim 在 IT 部门的工资都是最高的，Henry 在销售部的工资最高。</pre>

<div><li>👍 724</li><li>👎 0</li></div>


这道题的目标是查询每个部门中薪资最高的员工。在 SQL 中，我们可以通过以下几种方法来解决这个问题。

### 思路：

1. 我们需要为每个部门找出薪资最高的员工。如果多个员工薪资相同且最高，那么他们都应该被列出。
2. 可以使用 **`JOIN`** 来连接 `Employee` 和 `Department` 表，确保我们可以得到部门名称。
3. 然后通过 **`GROUP BY`** 和 **`HAVING`** 来找出每个部门的最高薪资，或者使用窗口函数来直接筛选出每个部门的最高薪资。

### 解法：

#### 方法 1：使用子查询

我们可以先通过子查询计算出每个部门的最高薪资，然后再通过外部查询将这些薪资对应的员工找出来。

```sql
SELECT d.name AS Department,
       e.name AS Employee,
       e.salary AS Salary
FROM Employee e
JOIN Department d ON e.departmentId = d.id
WHERE e.salary = (
    SELECT MAX(salary)
    FROM Employee
    WHERE departmentId = e.departmentId
);
```

### 解释：
1. **`JOIN`**: 我们将 `Employee` 表和 `Department` 表通过 `departmentId` 连接起来，确保能够获得每个员工的部门名称。
2. **子查询**: 在 `WHERE` 子句中，我们使用子查询来计算每个部门的最大薪资。子查询中的 `MAX(salary)` 计算的是当前部门 (`departmentId`) 的最高薪资。
3. **`WHERE e.salary = (...)`**: 在主查询中，只选择薪资等于部门最大薪资的员工。这确保了每个部门中薪资最高的员工被选出。

#### 方法 2：使用窗口函数 `ROW_NUMBER()` (适用于大部分数据库)

如果你的数据库支持窗口函数（如 `ROW_NUMBER()`），可以通过以下方式更加简洁地实现：

```sql
WITH RankedEmployees AS (
    SELECT e.name AS Employee,
           e.salary AS Salary,
           d.name AS Department,
           ROW_NUMBER() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC) AS rank
    FROM Employee e
    JOIN Department d ON e.departmentId = d.id
)
SELECT Department, Employee, Salary
FROM RankedEmployees
WHERE rank = 1;
```

### 解释：
1. **`ROW_NUMBER() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC)`**: 为每个部门的员工按薪资降序排序，并为每个员工分配一个 `rank`，薪资最高的员工将获得 `rank = 1`。
2. **`PARTITION BY e.departmentId`**: 按部门进行分组，每个部门单独排序。
3. **主查询**: 从 `RankedEmployees` CTE（公用表表达式）中筛选出 `rank = 1` 的员工，即每个部门中薪资最高的员工。

#### 方法 3：使用 `GROUP BY` 和 `JOIN`

另一种方法是使用 `GROUP BY` 来计算每个部门的最大薪资，并与 `Employee` 表进行连接。

```sql
SELECT d.name AS Department,
       e.name AS Employee,
       e.salary AS Salary
FROM Department d
JOIN Employee e ON e.departmentId = d.id
JOIN (
    SELECT departmentId, MAX(salary) AS max_salary
    FROM Employee
    GROUP BY departmentId
) AS max_salaries
ON e.departmentId = max_salaries.departmentId
AND e.salary = max_salaries.max_salary;
```

### 解释：
1. **`GROUP BY` 和 `MAX(salary)`**: 我们首先计算每个部门的最大薪资，并将其与部门 ID 一起返回。
2. **`JOIN`**: 然后将这个结果与 `Employee` 和 `Department` 表连接，确保只选择薪资等于该部门最大薪资的员工。

### 总结：
- **方法 1**（子查询）较为直观，适用于大多数数据库。
- **方法 2**（窗口函数）是现代 SQL 的一种优化方式，能简洁高效地解决问题，但需要数据库支持窗口函数。
- **方法 3**（`GROUP BY` 和 `JOIN`）也是一种常见的方法，适合于需要计算最大值并与主表连接的情况。

### 示例输入输出：

#### 输入：

**Employee 表：**
| id | name  | salary | departmentId |
|----|-------|--------|--------------|
| 1  | Joe   | 70000  | 1            |
| 2  | Jim   | 90000  | 1            |
| 3  | Henry | 80000  | 2            |
| 4  | Sam   | 60000  | 2            |
| 5  | Max   | 90000  | 1            |

**Department 表：**
| id | name  |
|----|-------|
| 1  | IT    |
| 2  | Sales |

#### 输出：

| Department | Employee | Salary |
|------------|----------|--------|
| IT         | Jim      | 90000  |
| IT         | Max      | 90000  |
| Sales      | Henry    | 80000  |

在这个例子中，`IT` 部门的薪资最高的员工是 `Jim` 和 `Max`（因为他们的薪资相同），`Sales` 部门的薪资最高的员工是 `Henry`。