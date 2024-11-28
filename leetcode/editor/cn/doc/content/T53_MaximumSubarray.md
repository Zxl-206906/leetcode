<p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>

<p><strong><span data-keyword="subarray-nonempty">子数组 </span></strong>是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong>6
<strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,-1,7,8]
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>

<div><li>👍 6851</li><li>👎 0</li></div>



### 问题描述

给定一个整数数组 `nums`，请你找出一个具有最大和的连续子数组（子数组至少包含一个元素），并返回其最大和。

子数组是数组中的一个连续部分。

---

### **思路解析**

本问题可以通过 **动态规划** 或 **分治法** 来解决。首先，我们介绍动态规划的解法，因为它能以 `O(n)` 的时间复杂度有效解决问题。

#### **动态规划方法**

我们可以使用动态规划来解决该问题。我们维护一个变量 `currentSum` 来记录当前子数组的和，另一个变量 `maxSum` 来记录最大子数组和。

1. **动态规划状态转移**：
    - 对于每个元素 `nums[i]`，我们考虑是否将其加入当前的子数组中：
        - 如果加入当前元素 `nums[i]` 后的子数组和比只选择当前元素 `nums[i]` 更小，则我们从当前元素 `nums[i]` 开始一个新的子数组。
        - 否则，将 `nums[i]` 加入当前的子数组中。
    - 每次更新 `maxSum`，它始终保存最大子数组和。

2. **初始化**：
    - 初始时，`currentSum = nums[0]`（表示从第一个元素开始的子数组和）。
    - `maxSum = nums[0]`（表示当前的最大和）。

3. **边界情况**：
    - 如果数组的长度为 1，直接返回该元素值。

---

### **Java 实现**

```java
public class MaximumSubarray {
    
    // 动态规划解法
    public static int maxSubArray(int[] nums) {
        // 初始化当前子数组和及最大和
        int currentSum = nums[0];
        int maxSum = nums[0];
        
        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 如果当前和为负，则重新开始新的子数组
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新最大和
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums1));  // 输出: 6
        
        int[] nums2 = {1};
        System.out.println(maxSubArray(nums2));  // 输出: 1
        
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println(maxSubArray(nums3));  // 输出: 23
    }
}
```

---

### **详细步骤**

1. **初始化**：
    - `currentSum = nums[0]`：表示当前子数组的和，初始化为数组的第一个元素。
    - `maxSum = nums[0]`：表示当前最大的子数组和，初始化为第一个元素。

2. **遍历数组**：
    - 从数组的第二个元素开始遍历，对每个元素 `nums[i]`：
        - 如果 `currentSum + nums[i] < nums[i]`，则表明加入当前元素后的子数组和变小，应该从当前元素 `nums[i]` 开始一个新的子数组，因此更新 `currentSum = nums[i]`。
        - 否则，将当前元素加入到已有的子数组中，更新 `currentSum = currentSum + nums[i]`。

3. **更新最大和**：
    - 每次更新 `currentSum` 后，比较并更新 `maxSum`，即 `maxSum = Math.max(maxSum, currentSum)`，确保 `maxSum` 始终保存最大子数组和。

4. **返回结果**：
    - 最终返回 `maxSum`，它包含了最大子数组和。

---

### **时间复杂度**

- 时间复杂度：`O(n)`，我们仅遍历了一次数组，`n` 是数组的长度。
- 空间复杂度：`O(1)`，只使用了常数空间。

---

### **进阶：分治法解法**

分治法的思路是将问题分解为子问题，通过递归的方式来解决。其基本思路如下：

1. 将数组分为左右两个部分，分别求解左半部分和右半部分的最大子数组和。
2. 还需要考虑一个跨越中点的子数组，它的最大和可能同时包含左半部分和右半部分的元素。
3. 对于每个子数组的计算，我们可以使用递归的方式，将数组不断分割，直到每个子数组只有一个元素。

分治法的时间复杂度是 `O(n log n)`，但是由于递归调用会涉及到函数栈，因此空间复杂度是 `O(log n)`。

---

### **总结**

1. **动态规划方法**：通过维护一个当前子数组的和和最大子数组的和，可以在线性时间内求解最大子数组和。
2. **时间复杂度**：`O(n)`，适合处理大规模数据。
3. **空间复杂度**：`O(1)`，是原地解法，空间开销小。

该解法是解决此类问题最常用且高效的解法之一。