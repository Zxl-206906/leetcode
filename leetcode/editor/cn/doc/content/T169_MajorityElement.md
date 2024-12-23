<p>给定一个大小为 <code>n</code><em> </em>的数组&nbsp;<code>nums</code> ，返回其中的多数元素。多数元素是指在数组中出现次数 <strong>大于</strong>&nbsp;<code>⌊ n/2 ⌋</code>&nbsp;的元素。</p>

<p>你可以假设数组是非空的，并且给定的数组总是存在多数元素。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,3]
<strong>输出：</strong>3</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,1,1,1,2,2]
<strong>输出：</strong>2
</pre>

<p>&nbsp;</p> 
<strong>提示：</strong>

<ul> 
 <li><code>n == nums.length</code></li> 
 <li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。</p>

<div><li>👍 2351</li><li>👎 0</li></div>

这个问题的要求是找到一个数组中的 **多数元素**，即出现次数大于 `n/2` 的元素。在数组中必定存在这样一个元素，因此可以利用这个条件来设计高效的算法。

### 思路分析

1. **多数元素的定义**：如果数组 `nums` 的长度为 `n`，则多数元素是出现次数大于 `n/2` 的元素。换句话说，出现次数超过一半的元素在数组中是唯一的。

2. **摩尔投票法**：根据题目的要求，时间复杂度应为 `O(n)`，并且空间复杂度应为 `O(1)`。摩尔投票法（Boyer-Moore Voting Algorithm）正是解决这一问题的理想方法。

### 摩尔投票法的核心思路：
- **投票过程**：
    - 我们初始化一个候选人 `candidate` 和一个计数器 `count`。
    - 遍历数组中的每个元素：
        - 如果 `count == 0`，则将当前元素作为新的候选人，并将 `count` 设置为 1。
        - 如果当前元素等于 `candidate`，则 `count` 增加 1。
        - 如果当前元素不等于 `candidate`，则 `count` 减少 1。

  由于多数元素的出现次数大于 `n/2`，最终遍历结束时 `candidate` 必定就是多数元素。

- **为什么有效**：
    - 由于多数元素的出现次数超过了所有其他元素的总和，所以它会在投票过程中“胜出”。即便是有不同的元素参与“反对”，多数元素最终仍然会成为候选。

### 代码实现

```java
public class MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];  // 初始化候选元素为第一个元素
        int count = 1;  // 初始化计数器

        // 进行摩尔投票
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];  // 选择新的候选人
                count = 1;
            } else if (nums[i] == candidate) {
                count++;  // 当前元素和候选人相同，计数器加1
            } else {
                count--;  // 当前元素和候选人不同，计数器减1
            }
        }

        return candidate;  // 最终候选人就是多数元素
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();
        
        // 测试用例
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));  // 输出: 3
        System.out.println(solution.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));  // 输出: 2
    }
}
```

### 代码解释：
1. **初始化**：
    - `candidate` 用来保存当前的候选多数元素。
    - `count` 用来记录候选元素的“票数”。

2. **遍历数组**：
    - 对于每个元素，如果 `count == 0`，说明我们需要换候选人。
    - 如果当前元素和候选元素相同，增加 `count`。
    - 如果当前元素与候选元素不同，减少 `count`。

3. **最后返回候选人**：
    - 最终，`candidate` 保存的就是多数元素。

### 时间复杂度
- **时间复杂度**：`O(n)`，因为我们只遍历了一次数组。
- **空间复杂度**：`O(1)`，我们只用了常量空间来存储 `candidate` 和 `count`。

### 测试用例

1. **输入**：`[3, 2, 3]`
    - 输出：`3`
    - 解释：3 出现两次，次数大于 `n/2`，是多数元素。

2. **输入**：`[2, 2, 1, 1, 1, 2, 2]`
    - 输出：`2`
    - 解释：2 出现四次，次数大于 `n/2`，是多数元素。

### 结论
摩尔投票法通过巧妙地利用计数器和候选人机制，可以在 `O(n)` 时间内找到多数元素，并且只使用常量的空间，满足题目对时间和空间复杂度的要求。