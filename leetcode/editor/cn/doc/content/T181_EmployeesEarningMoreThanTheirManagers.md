<p>表：<code>Employee</code>&nbsp;</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
| salary      | int     |
| managerId   | int     |
+-------------+---------+
id 是该表的主键（具有唯一值的列）。
该表的每一行都表示雇员的ID、姓名、工资和经理的ID。
</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出收入比经理高的员工。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
<strong>输出:</strong> 
+----------+
| Employee |
+----------+
| Joe      |
+----------+
<strong>解释:</strong> Joe 是唯一挣得比经理多的雇员。</pre>

<div><li>👍 729</li><li>👎 0</li></div>


要找出收入比经理高的员工，我们可以通过自连接（Self Join）来解决问题。具体思路如下：

### 思路：
1. **自连接**：我们将表 `Employee` 自连接一次，假设连接的员工为“经理”（`managerId`），被连接的员工为“员工”。
2. **比较工资**：通过比较员工的工资和其经理的工资，筛选出工资比经理高的员工。
3. **筛选条件**：确保员工的工资比其经理的工资高。

### SQL 查询：

```sql
SELECT E.name AS Employee
FROM Employee E
JOIN Employee M
  ON E.managerId = M.id
WHERE E.salary > M.salary;
```

### 解释：
1. **`E` 和 `M`**：我们将表 `Employee` 分别作为两个不同的实例，`E` 代表员工，`M` 代表经理。
2. **`JOIN`**：通过 `E.managerId = M.id` 将员工与他们的经理连接起来。
3. **`WHERE E.salary > M.salary`**：筛选出工资高于经理工资的员工。
4. **`SELECT E.name`**：返回员工的姓名。

### 示例分析：
对于给定的输入：
```
+----+-------+--------+-----------+
| id | name  | salary | managerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | Null      |
| 4  | Max   | 90000  | Null      |
+----+-------+--------+-----------+
```

- **Joe** 的工资是 70000，而他的经理是 **Sam**，**Sam** 的工资是 60000。因为 Joe 的工资比 Sam 高，所以 Joe 符合条件。
- 其他员工（Henry 和 Max）并没有工资比他们的经理高的情况。

**输出结果：**
```
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```

该查询能正确找出所有收入比经理高的员工。