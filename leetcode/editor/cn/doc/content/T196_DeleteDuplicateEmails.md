<p>表:&nbsp;<code>Person</code></p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id 是该表的主键列(具有唯一值的列)。
该表的每一行包含一封电子邮件。电子邮件将不包含大写字母。
</pre>

<p>&nbsp;</p>

<p>编写解决方案<strong> 删除</strong> 所有重复的电子邮件，只保留一个具有最小 <code>id</code> 的唯一电子邮件。</p>

<p>（对于 SQL 用户，请注意你应该编写一个 <code>DELETE</code> 语句而不是 <code>SELECT</code> 语句。）</p>

<p>（对于 Pandas 用户，请注意你应该直接修改 <code>Person</code> 表。）</p>

<p>运行脚本后，显示的答案是 <code>Person</code> 表。驱动程序将首先编译并运行您的代码片段，然后再显示 <code>Person</code> 表。<code>Person</code> 表的最终顺序 <strong>无关紧要</strong> 。</p>

<p>返回结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> 
Person 表:
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
<strong>输出:</strong> 
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
<strong>解释:</strong> john@example.com重复两次。我们保留最小的Id = 1。</pre>

<div><li>👍 839</li><li>👎 0</li></div>

要删除重复的电子邮件并仅保留具有最小 `id` 的唯一电子邮件，可以使用 SQL 的 `DELETE` 语句结合子查询来实现。

这里的基本思路是：首先找出重复的电子邮件，然后删除除了最小 `id` 的其余行。

### SQL 解法

```sql
DELETE p1
FROM Person p1
JOIN Person p2
ON p1.email = p2.email
WHERE p1.id > p2.id;
```

### 解释：
1. **`JOIN Person p2 ON p1.email = p2.email`**：我们将 `Person` 表自联接，连接条件是电子邮件相同。
2. **`WHERE p1.id > p2.id`**：删除条件是 `p1` 的 `id` 比 `p2` 的 `id` 大，即只删除具有较大 `id` 的行。
3. `DELETE p1`：表示删除 `p1` 中满足上述条件的记录。

通过这种方式，我们保留了每个电子邮件地址中 `id` 最小的记录，其余重复的记录将被删除。

### 示例：
对于输入数据：

```
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
| 3  | john@example.com |
+----+------------------+
```

执行上述 SQL 后，输出会是：

```
+----+------------------+
| id | email            |
+----+------------------+
| 1  | john@example.com |
| 2  | bob@example.com  |
+----+------------------+
```

这样就完成了删除重复电子邮件并保留最小 `id` 的操作。