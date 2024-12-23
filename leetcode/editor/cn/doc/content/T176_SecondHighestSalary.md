<code>Employee</code> 表：

<div class="original__bRMd"> 
 <div> 
  <pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| salary      | int  |
+-------------+------+
id 是这个表的主键。
表的每一行包含员工的工资信息。
</pre> 
 </div>
</div>

<p>&nbsp;</p>

<p>查询并返回 <code>Employee</code>&nbsp;表中第二高的 <b>不同</b>&nbsp;薪水 。如果不存在第二高的薪水，查询应该返回 <code>null(Pandas 则返回 None)</code> 。</p>

<p>查询结果如下例所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
<strong>输出：</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>
Employee 表：
+----+--------+
| id | salary |
+----+--------+
| 1  | 100    |
+----+--------+
<strong>输出：</strong>
+---------------------+
| SecondHighestSalary |
+---------------------+
| null                |
+---------------------+
</pre>


<div><li>👍 1503</li><li>👎 0</li></div>

要查询员工表中第二高的薪水，可以使用以下几种SQL方法。这里有一个比较简洁的解决方案，使用 `DISTINCT` 和 `LIMIT` 来处理。

### 方法 1: 使用 `DISTINCT` 和 `LIMIT`（适用于 MySQL）
可以使用 `DISTINCT` 关键字来去除重复的薪水，并根据薪水进行降序排序，选取第二高的薪水。

```sql
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (SELECT MAX(salary) FROM Employee);
```

### 解释：
1. **`MAX(salary)`**：首先，使用 `MAX(salary)` 获取薪水的最大值，也就是最高薪水。
2. **`WHERE salary < (SELECT MAX(salary) FROM Employee)`**：然后，在 `Employee` 表中，选取那些小于最高薪水的薪水（即排除了最高薪水的记录），并使用 `MAX(salary)` 再次获取其中的最大值，也就是第二高的薪水。
3. 如果第二高的薪水不存在（例如只有一个薪水值），则返回 `NULL`。

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
查询结果：
```
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
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
查询结果：
```
+---------------------+
| SecondHighestSalary |
+---------------------+
| NULL                |
+---------------------+
```

### 方法 2: 使用 `DISTINCT` 和排序（适用于 MySQL 或其他支持 `LIMIT` 的数据库）

```sql
SELECT salary AS SecondHighestSalary
FROM (
    SELECT DISTINCT salary
    FROM Employee
    ORDER BY salary DESC
    LIMIT 2
) AS temp
ORDER BY salary
LIMIT 1;
```

### 解释：
1. **`SELECT DISTINCT salary FROM Employee ORDER BY salary DESC LIMIT 2`**：首先从 `Employee` 表中获取所有不重复的薪水值，并按降序排序，选取前两个薪水。
2. **`ORDER BY salary LIMIT 1`**：然后，从上述结果中选取第一个薪水，也就是第二高的薪水。
3. 如果只有一个不同的薪水，查询结果将返回 `NULL`。

### 总结：
- 方法 1：通过子查询获取最高薪水，然后查询小于该薪水的最大值作为第二高薪水。适用于大多数 SQL 数据库。
- 方法 2：先通过 `DISTINCT` 去重，再选取前两个薪水，并从中选择第二高薪水，适用于支持 `LIMIT` 的数据库。

两种方法都可以有效解决问题，第一种方法比较简洁，适用于所有常见的数据库，而第二种方法在某些数据库中可能表现更好。


为了补充完整 `T176_SecondHighestSalary` 类，您需要实现求第二高薪水的解决方案。在这里，我们可以参考 SQL 解决方案中的思路来处理这个问题。对于 LeetCode 这种平台，通常会让你通过编写代码来操作数据库（例如通过 JDBC 或 ORM 框架），但在 Java 中处理这个问题，基本上你会做一些数据处理。

假设题目要求你模拟 SQL 查询的逻辑，下面是实现思路：

### 思路：
1. **获取不同薪水值**：首先，我们需要获取所有不同的薪水（类似于 SQL 中的 `DISTINCT`）。
2. **排序**：然后，将这些薪水按从高到低排序。
3. **返回第二高薪水**：如果存在至少两个不同的薪水值，则返回第二高薪水，否则返回 `null`。

下面是代码实现：

### 完整代码：

```java
import java.util.*;

public class T176_SecondHighestSalary {
    public static void main(String[] args) {
        Solution solution = new T176_SecondHighestSalary().new Solution();
        
        // 测试用例
        int[][] scores1 = {{1, 100}, {2, 200}, {3, 300}};
        System.out.println(solution.secondHighestSalary(scores1)); // 200

        int[][] scores2 = {{1, 100}};
        System.out.println(solution.secondHighestSalary(scores2)); // null
    }

    class Solution {
        public Integer secondHighestSalary(int[][] scores) {
            // 使用一个集合来存储所有不同的薪水值
            Set<Integer> salarySet = new HashSet<>();
            
            // 遍历所有的薪水并加入集合
            for (int[] score : scores) {
                salarySet.add(score[1]);
            }
            
            // 如果集合的大小小于 2，说明没有第二高薪水
            if (salarySet.size() < 2) {
                return null;
            }
            
            // 将集合转换为列表并按降序排序
            List<Integer> sortedSalaries = new ArrayList<>(salarySet);
            Collections.sort(sortedSalaries, Collections.reverseOrder());
            
            // 返回第二高薪水
            return sortedSalaries.get(1);
        }
    }
}
```

### 解释：
1. **数据结构**：我们使用 `HashSet` 来存储所有不同的薪水值，因为 `Set` 会自动去重，保证没有重复的薪水。
2. **收集薪水**：通过遍历 `scores` 数组，将每个学生的薪水加入到 `salarySet` 中。
3. **排序**：将 `Set` 转换成 `List`，并使用 `Collections.sort` 按降序对薪水进行排序。
4. **返回第二高薪水**：如果集合中至少有两个薪水，返回排序后的第二个薪水。否则返回 `null`。

### 测试用例：

1. **示例1**：
   输入：`[[1, 100], [2, 200], [3, 300]]`
   输出：`200`

2. **示例2**：
   输入：`[[1, 100]]`
   输出：`null`

### 复杂度分析：
- **时间复杂度**：
    - 遍历 `scores` 数组的时间复杂度是 `O(m)`，其中 `m` 是学生的数量。
    - 将 `Set` 转换成 `List` 并进行排序的时间复杂度是 `O(m log m)`。
    - 所以总体时间复杂度是 `O(m log m)`。

- **空间复杂度**：由于使用了 `HashSet` 和 `ArrayList` 存储薪水，所以空间复杂度是 `O(m)`，其中 `m` 是不同薪水的数量。

这种实现方法通过模拟 SQL 查询的逻辑来解决问题。