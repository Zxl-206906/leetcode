<p>给你一个整数数组&nbsp;<code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的<span data-keyword="subset">子集</span>（幂集）。</p>

<p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0]
<strong>输出：</strong>[[],[0]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10</code></li> 
 <li><code>-10 &lt;= nums[i] &lt;= 10</code></li> 
 <li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li> 
</ul>

<div><li>👍 2394</li><li>👎 0</li></div>


这是一个经典的子集问题，通常可以通过回溯法来解决。回溯法的基本思想是逐步构建解集，每次决定是否包含当前元素，并递归地构建所有可能的子集。

### 思路：
1. 对于每个数字，我们有两种选择：选择它或不选择它。
2. 通过回溯法来枚举所有可能的选择方式。
3. 每次递归时，我们将当前的选择结果存入一个列表，并继续递归。
4. 最终，返回所有子集。

### 解法：

```java
import java.util.*;

public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // 每次递归都将当前组合添加到结果中
        result.add(new ArrayList<>(current));

        // 遍历剩余的数字，选择每个数字加入当前组合
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);  // 选择当前数字
            backtrack(nums, i + 1, current, result);  // 递归选择下一个数字
            current.remove(current.size() - 1);  // 撤销选择，回溯
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.subsets(new int[]{1, 2, 3})); // 示例1
        System.out.println(solution.subsets(new int[]{0})); // 示例2
    }
}
```

### 解释：
1. `subsets` 方法是主函数，接受一个整数数组 `nums`，并调用 `backtrack` 方法来生成所有可能的子集。
2. `backtrack` 方法的参数：
    - `nums` 是给定的数组。
    - `start` 表示当前从哪个位置开始选择元素，保证每次递归时不会选择重复的元素。
    - `current` 存储当前子集。
    - `result` 存储所有生成的子集。
3. 每次递归时，我们都会将当前的 `current` 子集加入结果中，然后继续递归处理下一个元素。
4. 通过回溯，逐步生成所有可能的子集。

### 时间复杂度：
- 由于有 `2^n` 个可能的子集（其中 `n` 是数组的大小），时间复杂度是 `O(2^n)`。
- 对于每个子集，我们需要处理 `O(n)` 的元素（复制和移除操作），因此整体复杂度是 `O(n * 2^n)`。

### 输出：
示例 1：
```
[[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]
```

示例 2：
```
[[], [0]]
```