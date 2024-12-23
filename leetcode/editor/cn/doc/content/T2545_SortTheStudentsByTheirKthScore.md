<p>班里有 <code>m</code> 位学生，共计划组织 <code>n</code> 场考试。给你一个下标从 <strong>0</strong> 开始、大小为 <code>m x n</code> 的整数矩阵 <code>score</code> ，其中每一行对应一位学生，而 <code>score[i][j]</code> 表示第 <code>i</code> 位学生在第 <code>j</code> 场考试取得的分数。矩阵 <code>score</code> 包含的整数&nbsp;<strong>互不相同</strong>&nbsp;。</p>

<p>另给你一个整数 <code>k</code> 。请你按第 <code>k</code> 场考试分数从高到低完成对这些学生（矩阵中的行）的排序。</p>

<p>返回排序后的矩阵。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2022/11/30/example1.png" style="width: 600px; height: 136px;" /></p>

<pre>
<strong>输入：</strong>score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]], k = 2
<strong>输出：</strong>[[7,5,11,2],[10,6,9,1],[4,8,3,15]]
<strong>解释：</strong>在上图中，S 表示学生，E 表示考试。
- 下标为 1 的学生在第 2 场考试取得的分数为 11 ，这是考试的最高分，所以 TA 需要排在第一。
- 下标为 0 的学生在第 2 场考试取得的分数为 9 ，这是考试的第二高分，所以 TA 需要排在第二。
- 下标为 2 的学生在第 2 场考试取得的分数为 3 ，这是考试的最低分，所以 TA 需要排在第三。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2022/11/30/example2.png" style="width: 486px; height: 121px;" /></p>

<pre>
<strong>输入：</strong>score = [[3,4],[5,6]], k = 0
<strong>输出：</strong>[[5,6],[3,4]]
<strong>解释：</strong>在上图中，S 表示学生，E 表示考试。
- 下标为 1 的学生在第 0 场考试取得的分数为 5 ，这是考试的最高分，所以 TA 需要排在第一。
- 下标为 0 的学生在第 0 场考试取得的分数为 3 ，这是考试的最低分，所以 TA 需要排在第二。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == score.length</code></li> 
 <li><code>n == score[i].length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 250</code></li> 
 <li><code>1 &lt;= score[i][j] &lt;= 10<sup>5</sup></code></li> 
 <li><code>score</code> 由 <strong>不同</strong> 的整数组成</li> 
 <li><code>0 &lt;= k &lt; n</code></li> 
</ul>

<div><li>👍 40</li><li>👎 0</li></div>


这个问题的核心是要求按第 `k` 场考试的分数来对学生进行排序。我们需要将二维矩阵按指定列（第 `k` 列）对每一行（每个学生）的分数进行排序，按从高到低的顺序。

### 思路：
1. **理解数据结构**：给定的 `score` 是一个 `m x n` 的矩阵，其中 `m` 是学生人数，`n` 是考试场数。每行表示一个学生在每个考试中的分数。我们需要按第 `k` 列的分数对学生进行排序。
2. **排序操作**：我们只需要对二维数组进行排序，但排序的依据是第 `k` 列的元素。
3. **排序方式**：因为要求从高到低排序，所以我们需要进行降序排序。

### 关键步骤：
1. 提取矩阵的第 `k` 列。
2. 按第 `k` 列的元素对学生（矩阵的行）进行排序。
3. 返回排序后的矩阵。

### 代码实现：

```java
import java.util.*;

public class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        // 使用 Arrays.sort 来对学生进行排序
        // 由于 score[i] 是一个数组，按第 k 列的元素来排序
        Arrays.sort(score, (a, b) -> Integer.compare(b[k], a[k]));
        return score;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 示例1
        int[][] score1 = {
            {10, 6, 9, 1},
            {7, 5, 11, 2},
            {4, 8, 3, 15}
        };
        int k1 = 2;
        int[][] result1 = solution.sortTheStudents(score1, k1);
        System.out.println(Arrays.deepToString(result1));  // 输出：[[7, 5, 11, 2], [10, 6, 9, 1], [4, 8, 3, 15]]

        // 示例2
        int[][] score2 = {
            {3, 4},
            {5, 6}
        };
        int k2 = 0;
        int[][] result2 = solution.sortTheStudents(score2, k2);
        System.out.println(Arrays.deepToString(result2));  // 输出：[[5, 6], [3, 4]]
    }
}
```

### 代码解释：
1. **`Arrays.sort(score, (a, b) -> Integer.compare(b[k], a[k]));`**：
    - `score` 是一个二维数组，`a` 和 `b` 分别代表数组中的两行（学生）。
    - `a[k]` 和 `b[k]` 分别表示第 `k` 列的分数。
    - `Integer.compare(b[k], a[k])` 用于比较第 `k` 列的分数，`b[k]` 在前表示降序排序（从高到低）。

2. **`Arrays.deepToString(result)`**：
    - 用于打印二维数组，方便查看输出的矩阵。

### 运行结果：
1. **示例1**：
   ```java
   score = [[10,6,9,1],[7,5,11,2],[4,8,3,15]]
   k = 2
   输出：[[7,5,11,2],[10,6,9,1],[4,8,3,15]]
   ```
   解释：按第 2 列（从 0 开始计数）的分数排序后，排序结果为 `[[7, 5, 11, 2], [10, 6, 9, 1], [4, 8, 3, 15]]`。

2. **示例2**：
   ```java
   score = [[3,4],[5,6]]
   k = 0
   输出：[[5,6],[3,4]]
   ```
   解释：按第 0 列的分数排序后，排序结果为 `[[5, 6], [3, 4]]`。

### 时间复杂度：
- **时间复杂度**：`O(m log m)`，其中 `m` 是学生的数量。我们使用 `Arrays.sort` 来对学生进行排序，排序的复杂度是 `O(m log m)`，因为每个学生包含 `n` 个分数，但排序时只考虑第 `k` 列的值，所以排序复杂度是 `O(m log m)`。
- **空间复杂度**：`O(1)`，因为我们直接在原始的 `score` 数组上进行排序，没有使用额外的存储空间。

### 总结：
这个问题主要考察如何根据二维数组的特定列进行排序。通过使用 Java 的 `Arrays.sort` 方法，并提供一个自定义的比较器，我们能够很方便地根据第 `k` 列的值进行排序。