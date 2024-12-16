<p>给定一个三角形 <code>triangle</code> ，找出自顶向下的最小路径和。</p>

<p>每一步只能移动到下一行中相邻的结点上。<strong>相邻的结点 </strong>在这里指的是 <strong>下标</strong> 与 <strong>上一层结点下标</strong> 相同或者等于 <strong>上一层结点下标 + 1</strong> 的两个结点。也就是说，如果正位于当前行的下标 <code>i</code> ，那么下一步可以移动到下一行的下标 <code>i</code> 或 <code>i + 1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
<strong>输出：</strong>11
<strong>解释：</strong>如下面简图所示：
   <strong>2</strong>
  <strong>3</strong> 4
 6 <strong>5</strong> 7
4 <strong>1</strong> 8 3
自顶向下的最小路径和为&nbsp;11（即，2&nbsp;+&nbsp;3&nbsp;+&nbsp;5&nbsp;+&nbsp;1&nbsp;= 11）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>triangle = [[-10]]
<strong>输出：</strong>-10
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= triangle.length &lt;= 200</code></li> 
 <li><code>triangle[0].length == 1</code></li> 
 <li><code>triangle[i].length == triangle[i - 1].length + 1</code></li> 
 <li><code>-10<sup>4</sup> &lt;= triangle[i][j] &lt;= 10<sup>4</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul> 
 <li>你可以只使用 <code>O(n)</code>&nbsp;的额外空间（<code>n</code> 为三角形的总行数）来解决这个问题吗？</li> 
</ul>

<div><li>👍 1395</li><li>👎 0</li></div>

这是一个经典的动态规划问题，可以通过自底向上的动态规划方法高效解决。以下是详细分析和代码实现。

---

### 思路分析

#### 1. 问题描述
我们要找出从三角形顶部到底部的最小路径和，每次只能移动到下一行相邻的节点。

#### 2. 动态规划思路
我们定义 `dp[i][j]` 为从位置 `(i, j)` 到达三角形底部的最小路径和。
1. **状态转移方程**：
    - 到达位置 `(i, j)` 的最小路径和等于其值加上下方两个相邻位置的最小路径和：
      \[
      dp[i][j] = \text{triangle}[i][j] + \min(dp[i+1][j], dp[i+1][j+1])
      \]

2. **自底向上的优化**：
    - 从三角形的底部开始计算，逐步向上更新。
    - 用一个一维数组 `dp` 存储当前行的状态，逐行覆盖，达到空间优化的效果。

3. **边界条件**：
    - 最后一行的 `dp[i][j]` 等于 `triangle[i][j]`。

#### 3. 时间和空间复杂度
- **时间复杂度**：O(n²)，其中 \( n \) 为三角形的层数，因为需要遍历每个元素一次。
- **空间复杂度**：O(n)，使用一维数组 `dp` 存储当前行状态。

---

### Java 实现

以下是基于上述思路的 Java 实现，带有详细注释：

```java
import java.util.List;

public class MinimumTotal {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // 用于存储当前行的最小路径和
        int[] dp = new int[n];

        // 初始化 dp 为最后一行的值
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }

        // 从倒数第二行开始向上更新
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        // dp[0] 存储了从顶到底的最小路径和
        return dp[0];
    }

    public static void main(String[] args) {
        MinimumTotal mt = new MinimumTotal();

        // 示例 1
        List<List<Integer>> triangle1 = List.of(
            List.of(2),
            List.of(3, 4),
            List.of(6, 5, 7),
            List.of(4, 1, 8, 3)
        );
        System.out.println("最小路径和: " + mt.minimumTotal(triangle1)); // 输出: 11

        // 示例 2
        List<List<Integer>> triangle2 = List.of(
            List.of(-10)
        );
        System.out.println("最小路径和: " + mt.minimumTotal(triangle2)); // 输出: -10
    }
}
```

---

### 执行流程详解

#### 示例输入：`triangle = [[2], [3, 4], [6, 5, 7], [4, 1, 8, 3]]`

**初始化 `dp` 为最后一行：**
\[
dp = [4, 1, 8, 3]
\]

**从倒数第二行开始更新：**

1. **第 2 行：**
    - \( dp[0] = 6 + \min(4, 1) = 7 \)
    - \( dp[1] = 5 + \min(1, 8) = 6 \)
    - \( dp[2] = 7 + \min(8, 3) = 10 \)
      \[
      dp = [7, 6, 10, 3]
      \]

2. **第 1 行：**
    - \( dp[0] = 3 + \min(7, 6) = 9 \)
    - \( dp[1] = 4 + \min(6, 10) = 10 \)
      \[
      dp = [9, 10, 10, 3]
      \]

3. **第 0 行：**
    - \( dp[0] = 2 + \min(9, 10) = 11 \)
      \[
      dp = [11, 10, 10, 3]
      \]

**最终结果：`dp[0] = 11`**

---

### 可视化过程

```
初始三角形：
   2
  3 4
 6 5 7
4 1 8 3

自底向上的 dp 更新：
底部初始化: [4, 1, 8, 3]
倒数第 2 行: [7, 6, 10]
倒数第 1 行: [9, 10]
顶部结果  : [11]
```

---

### 总结

1. **优点**：代码简洁，时间和空间效率高。
2. **改进方向**：如果只需要输出最小路径和，不需要路径本身，此解法非常高效；如果需要记录路径，可以增加额外的数组记录路径信息。