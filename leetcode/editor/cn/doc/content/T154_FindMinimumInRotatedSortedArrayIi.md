已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组 <code>nums = [0,1,4,4,5,6,7]</code> 在变化后可能得到：

<ul> 
 <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,4]</code></li> 
 <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,4,4,5,6,7]</code></li> 
</ul>

<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>

<p>给你一个可能存在 <strong>重复</strong> 元素值的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 <strong>最小元素</strong> 。</p>

<p>你必须尽可能减少整个过程的操作步骤。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,5]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,2,0,1]
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == nums.length</code></li> 
 <li><code>1 &lt;= n &lt;= 5000</code></li> 
 <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li> 
 <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>这道题与 <a href="https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/description/">寻找旋转排序数组中的最小值</a> 类似，但 <code>nums</code> 可能包含重复元素。允许重复会影响算法的时间复杂度吗？会如何影响，为什么？</p>

<div><li>👍 701</li><li>👎 0</li></div>

### 题目解析

我们需要在一个经过多次旋转的升序数组中找到最小元素。数组可能包含重复元素，因此需要特别注意处理重复值对搜索过程的影响。

### 解题思路

这道题是 **寻找旋转排序数组最小值** 的变种。由于存在重复元素，传统的二分查找需要增加额外的步骤来跳过重复值，从而使算法复杂度可能退化为 \(O(n)\)。

核心思路是结合二分查找的方式：

1. **二分查找**:
    - 用两个指针 `left` 和 `right`，分别指向数组的两端。
    - 计算中间索引 `mid`。
    - 判断中间元素 `nums[mid]` 的值与右端点 `nums[right]` 的关系：
        - 如果 `nums[mid] < nums[right]`：
            - 最小值一定在 `mid` 的左侧或是 `mid` 本身（右半部分是升序）。
            - 更新 `right = mid`。
        - 如果 `nums[mid] > nums[right]`：
            - 最小值一定在 `mid` 的右侧。
            - 更新 `left = mid + 1`。
        - 如果 `nums[mid] == nums[right]`：
            - 无法判断最小值的具体位置，缩小搜索空间，`right--`。
2. **终止条件**:
    - 当 `left == right` 时，指针锁定最小值。

### 为什么允许重复会影响时间复杂度？

- 如果数组中不存在重复元素，二分查找每次可以有效缩小搜索区间，时间复杂度为 \(O(\log n)\)。
- 但当存在重复元素时，`nums[mid] == nums[right]` 的情况会导致我们无法有效地缩小搜索区间，只能通过 `right--` 线性缩小区间，最坏情况下时间复杂度退化为 \(O(n)\)。

---

### Java 实现

以下是基于上述逻辑的 Java 实现：

```java
public class FindMinInRotatedArray {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                // 最小值在左半部分，包含 mid
                right = mid;
            } else if (nums[mid] > nums[right]) {
                // 最小值在右半部分，不包含 mid
                left = mid + 1;
            } else {
                // 无法确定，缩小右边界
                right--;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinInRotatedArray solution = new FindMinInRotatedArray();

        // 示例测试
        int[] nums1 = {1, 3, 5};
        System.out.println("最小值是: " + solution.findMin(nums1)); // 输出: 1

        int[] nums2 = {2, 2, 2, 0, 1};
        System.out.println("最小值是: " + solution.findMin(nums2)); // 输出: 0
    }
}
```

---

### 复杂度分析

1. **时间复杂度**:
    - 最好情况：数组中无重复元素，二分查找的时间复杂度为 \(O(\log n)\)。
    - 最坏情况：数组中存在大量重复元素，时间复杂度退化为 \(O(n)\)。

2. **空间复杂度**:
    - 仅使用了常数级别的额外空间，复杂度为 \(O(1)\)。

---

### 示例运行分析

#### 输入: `nums = [2, 2, 2, 0, 1]`
- 初始 `left = 0, right = 4`
- 第一次迭代: `mid = 2, nums[mid] = 2, nums[right] = 1`
    - `nums[mid] > nums[right]`，`left = mid + 1 = 3`
- 第二次迭代: `mid = 3, nums[mid] = 0, nums[right] = 1`
    - `nums[mid] < nums[right]`，`right = mid = 3`
- 结束条件: `left == right == 3`
- 返回 `nums[left] = 0`

最终输出为 `0`。

---

### 总结

这道题考查了二分查找的灵活性，尤其是在处理边界和重复元素时的细节优化。通过适当调整，我们可以保证在大多数情况下实现较优的时间复杂度。