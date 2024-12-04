<p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>

<p>你可以按 <strong>任何顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 4, k = 2
<strong>输出：</strong>
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1, k = 1
<strong>输出：</strong>[[1]]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 20</code></li> 
 <li><code>1 &lt;= k &lt;= n</code></li> 
</ul>

<div><li>👍 1702</li><li>👎 0</li></div>


这是一个经典的组合问题，我们可以使用回溯法来解决它。回溯法的基本思想是逐步构建所有可能的组合，并在每一步上做出选择。对于这个问题，我们需要从 `1` 到 `n` 中选择 `k` 个数，并且不允许重复选择。

### 思路：
1. 从 `1` 到 `n` 中选取 `k` 个数，返回所有可能的组合。
2. 可以使用递归（回溯法）来生成组合：
    - 每次选择一个数，并递归生成剩下 `k-1` 个数的组合。
    - 如果当前选择的数已经达到 `k` 个，就将其保存为结果。
    - 为了确保组合的顺序，我们可以在选择时保证选择的数字是递增的。

### 解法：

```java
import java.util.*;

public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        // 如果当前组合已经包含k个数，则将其加入结果
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 从start开始选取数字，保证递增
        for (int i = start; i <= n; i++) {
            current.add(i);  // 选择当前数字
            backtrack(n, k, i + 1, current, result);  // 递归选择下一个数字
            current.remove(current.size() - 1);  // 撤销选择，回溯
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combine(4, 2)); // 示例1
        System.out.println(solution.combine(1, 1)); // 示例2
    }
}
```

### 解释：
1. `combine` 方法是主函数，接受参数 `n` 和 `k`，并调用 `backtrack` 来生成所有可能的组合。
2. `backtrack` 方法的参数：
    - `n` 是我们从 `1` 到 `n` 中选择数的范围。
    - `k` 是需要选择的数的个数。
    - `start` 用于控制递归的起始位置，保证选择的数字是递增的。
    - `current` 用于存储当前的组合。
    - `result` 用于存储所有的组合。
3. 在每次递归中，如果 `current` 的大小已经达到 `k`，则将当前组合加入结果中。如果没有达到，继续从 `start` 开始递归选择下一个数。

### 时间复杂度：
- 回溯法的时间复杂度是 `O(C(n, k))`，其中 `C(n, k)` 是从 `n` 个元素中选择 `k` 个的组合数，等价于 `n! / (k! * (n-k)!)`。

### 输出：
示例 1：
```
[[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
```

示例 2：
```
[[1]]
```