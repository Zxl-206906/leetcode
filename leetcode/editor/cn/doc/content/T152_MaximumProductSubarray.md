<p>给你一个整数数组 <code>nums</code>&nbsp;，请你找出数组中乘积最大的非空连续 <span data-keyword="subarray-nonempty">子数组</span>（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。</p>

<p>测试用例的答案是一个&nbsp;<strong>32-位</strong> 整数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [2,3,-2,4]
<strong>输出:</strong> <span><code>6</code></span>
<strong>解释:</strong>&nbsp;子数组 [2,3] 有最大乘积 6。
</pre>

<p><strong class="example">示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [-2,0,-1]
<strong>输出:</strong> 0
<strong>解释:</strong>&nbsp;结果不能为 2, 因为 [-2,-1] 不是子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li> 
 <li><code>-10 &lt;= nums[i] &lt;= 10</code></li> 
 <li><code>nums</code> 的任何子数组的乘积都 <strong>保证</strong>&nbsp;是一个 <strong>32-位</strong> 整数</li> 
</ul>

<div><li>👍 2354</li><li>👎 0</li></div>

这个问题可以通过动态规划来解决，重点是处理负数对乘积的影响。我们需要维护两个变量来存储当前子数组的最大乘积和最小乘积。

### 解题思路：
1. **最大乘积**：如果当前元素是正数，则它与之前的最大乘积相乘可能会得到更大的乘积；如果当前元素是负数，则它与最小乘积相乘可能会得到更大的乘积。
2. **最小乘积**：负数的乘积可能会使得最小乘积变成最大乘积。因此，需要跟踪最小乘积，以便在负数出现时更新最大乘积。
3. **更新最大乘积**：每一步都要更新当前的最大乘积和最小乘积，并维护一个全局最大值。

### 动态规划步骤：
- **初始化**：我们可以通过两个变量来存储当前的最大乘积 `maxSoFar` 和最小乘积 `minSoFar`。另外，我们还需要一个 `result` 来记录全局最大乘积。
- **遍历数组**：对于每个元素，我们需要根据当前元素的值更新最大乘积和最小乘积，然后更新全局最大乘积。

### 代码实现：
```java
public class Solution {
    public int maxProduct(int[] nums) {
        // 初始化最大值和最小值
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];
        
        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素是负数，交换最大值和最小值
            if (nums[i] < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }
            
            // 更新当前的最大值和最小值
            maxSoFar = Math.max(nums[i], maxSoFar * nums[i]);
            minSoFar = Math.min(nums[i], minSoFar * nums[i]);
            
            // 更新全局最大值
            result = Math.max(result, maxSoFar);
        }
        
        return result;
    }
}
```

### 解释：
1. **初始化**：`maxSoFar` 和 `minSoFar` 都被初始化为数组的第一个元素，这样可以处理数组只有一个元素的情况。
2. **遍历数组**：
    - 每次处理一个新的元素时，首先检查如果当前元素是负数，那么最大和最小乘积需要交换，因为负数与小负数相乘会变成一个大正数。
    - 通过 `Math.max` 和 `Math.min` 更新 `maxSoFar` 和 `minSoFar`。
    - 每次计算新的最大乘积时，都更新全局的 `result`。
3. **最终返回**：`result` 存储了数组中乘积最大的子数组的乘积。

### 示例：
对于输入 `[2, 3, -2, 4]`：
- 初始状态：`maxSoFar = 2`, `minSoFar = 2`, `result = 2`
- 遍历第一个元素 `2`，`maxSoFar = 2`, `minSoFar = 2`
- 遍历第二个元素 `3`，`maxSoFar = 6`, `minSoFar = 3`, 更新 `result = 6`
- 遍历第三个元素 `-2`，`maxSoFar = -2`, `minSoFar = -12`, 更新 `result = 6`
- 遍历第四个元素 `4`，`maxSoFar = 4`, `minSoFar = -48`, 更新 `result = 6`
  最终返回的结果是 `6`。

### 复杂度分析：
- **时间复杂度**：O(n)，其中 n 是数组的长度。我们只需要一次遍历来计算最大乘积。
- **空间复杂度**：O(1)，我们只使用常数空间来存储几个变量。


从你的描述来看，问题出在当数组中有零时，我们应该把零当作一个新的子数组的起点，跳过它并重新计算乘积。然而在原始的解法中，零的处理没有被单独考虑，因此即使遇到零，`maxSoFar` 和 `minSoFar` 也会被更新为零，从而导致错误结果。

### 问题分析：
- 当遇到零时，零会使得当前子数组的乘积变成零，因此需要将 `maxSoFar` 和 `minSoFar` 重新初始化为零，表示从该位置开始重新计算子数组的乘积。
- 如果整个子数组都乘积为零，应该返回零。

### 修改后的解决方案：
1. **遇到零时初始化最大乘积和最小乘积**：遇到零时，重新初始化 `maxSoFar` 和 `minSoFar` 为零，这样我们从零的位置开始重新计算乘积。
2. **维护最大值**：我们还需要继续维护全局最大值 `result`，它记录的是从整个数组中得到的最大乘积。

### 代码实现：
```java
public class Solution {
    public int maxProduct(int[] nums) {
        // 初始化最大值和最小值
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];
        
        // 从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == 0) {
                // 如果遇到零，重新初始化当前子数组的乘积
                maxSoFar = 0;
                minSoFar = 0;
            } else {
                // 如果当前元素是负数，交换最大值和最小值
                if (nums[i] < 0) {
                    int temp = maxSoFar;
                    maxSoFar = minSoFar;
                    minSoFar = temp;
                }
                
                // 更新当前的最大值和最小值
                maxSoFar = Math.max(nums[i], maxSoFar * nums[i]);
                minSoFar = Math.min(nums[i], minSoFar * nums[i]);
            }
            
            // 更新全局最大值
            result = Math.max(result, maxSoFar);
        }
        
        return result;
    }
}
```

### 解释：
1. **遇到零**：如果 `nums[i] == 0`，我们将 `maxSoFar` 和 `minSoFar` 都重新初始化为 0，因为任何数字与零相乘都会得到零，从而中断当前子数组的积。
2. **负数处理**：如果当前元素是负数，我们交换 `maxSoFar` 和 `minSoFar`，因为负数可能将最小的乘积变成最大乘积。
3. **最大乘积计算**：通过更新 `maxSoFar` 和 `minSoFar`，并在每次更新时维护全局最大乘积 `result`。

### 测试：
对于输入 `[-2, 0]`：
- 初始时：`maxSoFar = -2`, `minSoFar = -2`, `result = -2`
- 遍历第一个元素 `-2`，`maxSoFar = -2`, `minSoFar = -2`
- 遍历第二个元素 `0`，由于遇到零，`maxSoFar = 0`, `minSoFar = 0`
- 结果为 `0`，符合期望。

### 复杂度分析：
- **时间复杂度**：O(n)，只需遍历一次数组。
- **空间复杂度**：O(1)，只使用常数空间来保存变量。

这样，处理零的情况就能正确返回期望的结果了。