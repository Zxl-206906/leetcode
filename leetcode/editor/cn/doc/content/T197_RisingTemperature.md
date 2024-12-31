<div class="original__bRMd"> 
 <div> 
  <p>表：&nbsp;<code>Weather</code></p> 
 </div>
</div>

<pre>
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id 是该表具有唯一值的列。
没有具有相同 recordDate 的不同行。
该表包含特定日期的温度信息</pre>

<p>&nbsp;</p>

<p>编写解决方案，找出与之前（昨天的）日期相比温度更高的所有日期的 <code>id</code> 。</p>

<p>返回结果 <strong>无顺序要求</strong> 。</p>

<p>结果格式如下例子所示。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>
Weather 表：
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
<strong>输出：</strong>
+----+
| id |
+----+
| 2  |
| 4  |
+----+
<strong>解释：</strong>
2015-01-02 的温度比前一天高（10 -&gt; 25）
2015-01-04 的温度比前一天高（20 -&gt; 30）</pre>


<div><li>👍 632</li><li>👎 0</li></div>

要找出与昨天的温度相比更高的所有日期的 `id`，可以通过将 `Weather` 表进行自连接实现。自连接可以将每一天的记录与前一天的记录进行对比，从而找出温度更高的日期。

具体的 SQL 查询如下：

```sql
SELECT w1.id
FROM Weather w1
JOIN Weather w2 ON DATEDIFF(w1.recordDate, w2.recordDate) = 1
WHERE w1.temperature > w2.temperature;
```

### 解释：

1. `Weather w1` 和 `Weather w2` 表示对 `Weather` 表的两次自连接，分别代表“今天”的记录和“昨天”的记录。
2. `DATEDIFF(w1.recordDate, w2.recordDate) = 1` 表示日期 `w1.recordDate` 是 `w2.recordDate` 的后一天，即昨天的记录。
3. `w1.temperature > w2.temperature` 用于筛选出温度比昨天更高的记录。
4. 最终，返回的是所有符合条件的 `id`。

### 示例输出：

对于给定的输入：

```
+----+------------+-------------+
| id | recordDate | Temperature |
+----+------------+-------------+
| 1  | 2015-01-01 | 10          |
| 2  | 2015-01-02 | 25          |
| 3  | 2015-01-03 | 20          |
| 4  | 2015-01-04 | 30          |
+----+------------+-------------+
```

输出结果为：

```
+----+
| id |
+----+
| 2  |
| 4  |
+----+
```

解释：
- 2015-01-02 的温度比 2015-01-01 高（10 -> 25）
- 2015-01-04 的温度比 2015-01-03 高（20 -> 30）