<p>
 <meta charset="UTF-8" /></p>

<p>表:&nbsp;<code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是该表的主键（具有唯一值的列）。
此表的每一行都包含一封电子邮件。电子邮件不包含大写字母。
</pre>

<p>&nbsp;</p>

<p>编写解决方案来报告所有重复的电子邮件。 请注意，可以保证电子邮件字段不为 NULL。</p>

<p>以&nbsp;<strong>任意顺序&nbsp;</strong>返回结果表。</p>

<p>结果格式如下例。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>输出:</strong> 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>解释:</strong> a@b.com 出现了两次。</pre>

<div><li>👍 521</li><li>👎 0</li></div>
要找出重复的电子邮件，我们可以通过以下步骤来解决：

### 思路：
1. **分组**：根据电子邮件进行分组，查看哪些电子邮件出现了超过一次。
2. **过滤**：只选择出现次数大于 1 的电子邮件。

### SQL 查询：

```sql
SELECT email
FROM Person
GROUP BY email
HAVING COUNT(*) > 1;
```

### 解释：
1. **`GROUP BY email`**：按照 `email` 对表中的记录进行分组，每个电子邮件只会出现一次在分组结果中。
2. **`HAVING COUNT(*) > 1`**：通过 `HAVING` 子句筛选出出现次数大于 1 的电子邮件，`COUNT(*)` 统计每个分组中出现的记录数。
3. **`SELECT email`**：返回所有重复的电子邮件。

### 示例分析：
对于给定的输入：

```
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
```

- **a@b.com** 出现了两次。
- **c@d.com** 只出现了一次，不是重复的电子邮件。

**输出结果：**
```
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
```

该查询能够正确找出所有重复的电子邮件。
