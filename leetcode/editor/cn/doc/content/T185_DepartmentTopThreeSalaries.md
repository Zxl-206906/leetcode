<p>表:&nbsp;<code>Employee</code></p>

<pre>
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id 是该表的主键列(具有唯一值的列)。
departmentId 是 Department 表中 ID 的外键（reference 列）。
该表的每一行都表示员工的ID、姓名和工资。它还包含了他们部门的ID。
</pre>

<p>&nbsp;</p>

<p>表:&nbsp;<code>Department</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id 是该表的主键列(具有唯一值的列)。
该表的每一行表示部门ID和部门名。
</pre>

<p>&nbsp;</p>

<p>公司的主管们感兴趣的是公司每个部门中谁赚的钱最多。一个部门的 <strong>高收入者</strong> 是指一个员工的工资在该部门的 <strong>不同</strong> 工资中 <strong>排名前三</strong> 。</p>

<p>编写解决方案，找出每个部门中 <strong>收入高的员工</strong> 。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>返回结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Employee 表:
+----+-------+--------+--------------+
| id | name  | salary | departmentId |
+----+-------+--------+--------------+
| 1  | Joe   | 85000  | 1            |
| 2  | Henry | 80000  | 2            |
| 3  | Sam   | 60000  | 2            |
| 4  | Max   | 90000  | 1            |
| 5  | Janet | 69000  | 1            |
| 6  | Randy | 85000  | 1            |
| 7  | Will  | 70000  | 1            |
+----+-------+--------+--------------+
Department  表:
+----+-------+
| id | name  |
+----+-------+
| 1  | IT    |
| 2  | Sales |
+----+-------+
<strong>输出:</strong> 
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------+
<strong>解释:
</strong>在IT部门:
- Max的工资最高
- 兰迪和乔都赚取第二高的独特的薪水
- 威尔的薪水是第三高的

在销售部:
- 亨利的工资最高
- 山姆的薪水第二高
- 没有第三高的工资，因为只有两名员工</pre>

<div><li>👍 879</li><li>👎 0</li></div>

要解决这个问题，我们的目标是找出每个部门中收入高的员工，也就是说，每个部门中工资排前 3 名的员工。具体来说，我们需要按部门对员工进行排序，然后返回每个部门的前 3 个薪资。

### 思路：
1. 我们需要对每个部门进行排序，按工资从高到低排序。
2. 对于每个部门，选取薪资排名前 3 的员工。
3. 使用窗口函数 `RANK()` 或 `ROW_NUMBER()` 来给每个员工按工资排名。
4. 然后筛选出每个部门排名前三的员工。

### 解法：使用窗口函数

我们可以使用 **`RANK()`** 或 **`ROW_NUMBER()`** 窗口函数，这两个函数可以给每个部门的员工按薪资进行排序并给出排名。之后我们筛选出排名前 3 的员工。

```sql
WITH RankedEmployees AS (
    SELECT e.id, e.name AS Employee, e.salary, d.name AS Department,
           RANK() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC) AS rank
    FROM Employee e
    JOIN Department d ON e.departmentId = d.id
)
SELECT Department, Employee, salary
FROM RankedEmployees
WHERE rank <= 3;
```

### 解释：
1. **`RANK() OVER (PARTITION BY e.departmentId ORDER BY e.salary DESC)`**:
    - `PARTITION BY e.departmentId`：按照部门 `departmentId` 对员工进行分组。
    - `ORDER BY e.salary DESC`：在每个部门内，按薪资从高到低排序。
    - `RANK()` 给每个员工在各自部门内按照薪资排序并生成排名。如果有多个员工的薪资相同，他们会获得相同的排名。

2. **`WHERE rank <= 3`**:
    - 过滤出每个部门中排名前 3 的员工。

### 为什么使用 `RANK()` 而不是 `ROW_NUMBER()`：
- **`RANK()`**：如果多个员工的薪资相同，他们会获得相同的排名，接下来的排名会跳过。例如，两个员工的薪资相同且排名为 1，那么下一个员工的排名将是 3。
- **`ROW_NUMBER()`**：如果多个员工的薪资相同，每个员工会获得不同的排名，接下来的排名是连续的。

在这个问题中，由于薪资相同的员工也需要被列出，所以使用 **`RANK()`** 会更合适。

### 示例：

#### 输入：

**Employee 表：**
| id | name   | salary | departmentId |
|----|--------|--------|--------------|
| 1  | Joe    | 85000  | 1            |
| 2  | Henry  | 80000  | 2            |
| 3  | Sam    | 60000  | 2            |
| 4  | Max    | 90000  | 1            |
| 5  | Janet  | 69000  | 1            |
| 6  | Randy  | 85000  | 1            |
| 7  | Will   | 70000  | 1            |

**Department 表：**
| id | name  |
|----|-------|
| 1  | IT    |
| 2  | Sales |

#### 输出：

| Department | Employee | Salary |
|------------|----------|--------|
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |

### 解释：
- **IT 部门**:
    - **Max** 的薪资最高。
    - **Joe** 和 **Randy** 的薪资是第二高且相同，所以他们都排在第二位。
    - **Will** 的薪资是第三高，因此他排在第三位。

- **Sales 部门**:
    - **Henry** 的薪资最高。
    - **Sam** 的薪资第二高。
    - Sales 部门没有第三高的薪资，因为该部门只有两名员工。

### 小结：
- 使用 **`RANK()`** 可以很好地处理薪资相同的员工，保证他们拥有相同的排名，并跳过后续排名。
- 使用窗口函数能够简洁高效地解决分组、排序和筛选问题。