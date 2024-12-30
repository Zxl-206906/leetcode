<p><code>Customers</code> 表：</p>

<pre>
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
在 SQL 中，id 是该表的主键。
该表的每一行都表示客户的 ID 和名称。</pre>

<p><code>Orders</code> 表：</p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| id          | int  |
| customerId  | int  |
+-------------+------+
在 SQL 中，id 是该表的主键。
customerId 是 Customers 表中 ID 的外键( Pandas 中的连接键)。
该表的每一行都表示订单的 ID 和订购该订单的客户的 ID。</pre>

<p>&nbsp;</p>

<p>找出所有从不点任何东西的顾客。</p>

<p>以 <strong>任意顺序</strong> 返回结果表。</p>

<p>结果格式如下所示。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>
Customers table:
+----+-------+
| id | name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+
Orders table:
+----+------------+
| id | customerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+
<b>输出：</b>
+-----------+
| Customers |
+-----------+
| Henry     |
| Max       |
+-----------+</pre>

<div><li>👍 518</li><li>👎 0</li></div>


这道题的目标是找出所有从未下过订单的顾客。具体来说，我们需要找出 `Customers` 表中没有在 `Orders` 表中出现的客户。

### 思路：

- `Customers` 表包含了所有顾客的信息，而 `Orders` 表则记录了每个订单和对应的顾客 ID。
- 我们的目标是从 `Customers` 表中找出那些 `customerId` 在 `Orders` 表中没有出现的顾客。

### 解法：

可以使用 SQL 中的 **`LEFT JOIN`** 或 **`NOT EXISTS`** 来实现这一需求。

#### 方法 1：使用 `LEFT JOIN`

我们可以使用 `LEFT JOIN` 连接 `Customers` 和 `Orders` 表，然后检查 `Orders` 表中是否有相应的订单。如果没有订单，则表示该顾客没有下单。

```sql
SELECT c.name AS Customers
FROM Customers c
LEFT JOIN Orders o ON c.id = o.customerId
WHERE o.customerId IS NULL;
```

### 解释：
1. **`LEFT JOIN`**：我们将 `Customers` 表和 `Orders` 表连接，连接条件是 `customerId` 等于 `id`。
2. **`WHERE o.customerId IS NULL`**：这意味着在 `Orders` 表中没有找到对应的记录，也就是顾客没有下过订单。

#### 方法 2：使用 `NOT EXISTS`

另一种方法是使用 **`NOT EXISTS`**，检查 `Orders` 表中是否存在匹配的记录。

```sql
SELECT c.name AS Customers
FROM Customers c
WHERE NOT EXISTS (
    SELECT 1
    FROM Orders o
    WHERE o.customerId = c.id
);
```

### 解释：
1. **`NOT EXISTS`**：子查询检查是否有任何订单存在对于当前顾客（`c.id`）的记录。如果没有记录返回 `TRUE`，那么这个顾客就是从不订购的顾客。
2. **`SELECT 1`**：因为我们只关心是否有对应的记录，`SELECT 1` 就足够了。

#### 方法 3：使用 `NOT IN`

还可以使用 **`NOT IN`** 来查找那些没有出现在 `Orders` 表中的顾客。

```sql
SELECT name AS Customers
FROM Customers
WHERE id NOT IN (SELECT DISTINCT customerId FROM Orders);
```

### 解释：
1. **`SELECT DISTINCT customerId FROM Orders`**：从 `Orders` 表中获取所有出现过的 `customerId`。
2. **`WHERE id NOT IN (...)`**：查找 `Customers` 表中那些其 `id` 不在上述子查询结果中的顾客。

### 总结：
- **`LEFT JOIN`** 和 **`NOT EXISTS`** 都可以有效地找到没有下订单的顾客。两者在性能上可能有所不同，通常 **`NOT EXISTS`** 会在大数据集时表现得更好。
- **`NOT IN`** 方法也有效，但在某些数据库中，子查询结果中有 `NULL` 时可能会导致意外的行为，因此需要谨慎使用。

### 示例输入输出：

#### 输入：

**Customers 表：**
| id | name  |
|----|-------|
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |

**Orders 表：**
| id | customerId |
|----|------------|
| 1  | 3          |
| 2  | 1          |

#### 输出：

| Customers |
|-----------|
| Henry     |
| Max       |

在这个例子中，`Joe` 和 `Sam` 在 `Orders` 表中都有订单，所以他们不会被选中。`Henry` 和 `Max` 从未下过订单，所以他们会出现在结果中。

