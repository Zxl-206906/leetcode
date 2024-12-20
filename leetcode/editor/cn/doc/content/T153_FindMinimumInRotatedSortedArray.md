已知一个长度为 <code>n</code> 的数组，预先按照升序排列，经由 <code>1</code> 到 <code>n</code> 次 <strong>旋转</strong> 后，得到输入数组。例如，原数组 <code>nums = [0,1,2,4,5,6,7]</code> 在变化后可能得到：

<ul> 
 <li>若旋转 <code>4</code> 次，则可以得到 <code>[4,5,6,7,0,1,2]</code></li> 
 <li>若旋转 <code>7</code> 次，则可以得到 <code>[0,1,2,4,5,6,7]</code></li> 
</ul>

<p>注意，数组 <code>[a[0], a[1], a[2], ..., a[n-1]]</code> <strong>旋转一次</strong> 的结果为数组 <code>[a[n-1], a[0], a[1], a[2], ..., a[n-2]]</code> 。</p>

<p>给你一个元素值 <strong>互不相同</strong> 的数组 <code>nums</code> ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 <strong>最小元素</strong> 。</p>

<p>你必须设计一个时间复杂度为&nbsp;<code>O(log n)</code> 的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,5,6,7,0,1,2]
<strong>输出：</strong>0
<strong>解释：</strong>原数组为 [0,1,2,4,5,6,7] ，旋转 3 次得到输入数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [11,13,15,17]
<strong>输出：</strong>11
<strong>解释：</strong>原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == nums.length</code></li> 
 <li><code>1 &lt;= n &lt;= 5000</code></li> 
 <li><code>-5000 &lt;= nums[i] &lt;= 5000</code></li> 
 <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li> 
 <li><code>nums</code> 原来是一个升序排序的数组，并进行了 <code>1</code> 至 <code>n</code> 次旋转</li> 
</ul>

<div><li>👍 1182</li><li>👎 0</li></div>



这道题要求在一个经过旋转的升序数组中找到最小元素，且算法的时间复杂度必须是 \(O(\log n)\)。这提示我们需要使用 **二分查找** 来解决问题。

---

### 思路分析

1. **观察性质**:
    - 原数组是升序排列，旋转后会形成一个“折断”的序列。例如 `[4,5,6,7,0,1,2]`。
    - 数组的左半部分是递增的，右半部分也是递增的，但右半部分的最小值比左半部分的最大值小。

2. **二分查找**:
    - 利用数组的性质，我们可以通过中点的值与左右边界的值来判断哪一部分可能包含最小值：
        - 如果 `nums[mid] > nums[right]`，说明最小值在右半部分。
        - 如果 `nums[mid] <= nums[right]`，说明最小值在左半部分（包括 `mid` 本身）。

3. **边界条件**:
    - 当 `left == right` 时，搜索区间只剩下一个元素，即为最小值。

---

### 算法流程

1. 初始化指针 `left` 为 0，`right` 为 `n-1`。
2. 循环执行以下操作，直到 `left == right`：
    - 计算中点 `mid = left + (right - left) / 2`。
    - 比较 `nums[mid]` 和 `nums[right]`：
        - 如果 `nums[mid] > nums[right]`，最小值一定在右半部分，令 `left = mid + 1`。
        - 否则，最小值在左半部分或就是 `mid`，令 `right = mid`。
3. 返回 `nums[left]`。

---

### Java 实现

```java
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 判断中间值与右边界值的关系
            if (nums[mid] > nums[right]) {
                // 最小值一定在右半部分
                left = mid + 1;
            } else {
                // 最小值在左半部分或就是 mid
                right = mid;
            }
        }

        // 循环结束时，left == right
        return nums[left];
    }
}
```

---

### 示例运行

#### 示例 1:
- 输入: `nums = [3,4,5,1,2]`
- 过程:
    1. `left = 0, right = 4, mid = 2, nums[mid] = 5, nums[right] = 2` → `left = mid + 1 = 3`
    2. `left = 3, right = 4, mid = 3, nums[mid] = 1, nums[right] = 2` → `right = mid = 3`
    3. `left = 3, right = 3` → 返回 `nums[3] = 1`
- 输出: `1`

#### 示例 2:
- 输入: `nums = [4,5,6,7,0,1,2]`
- 输出: `0` （同理分析）

---

### 时间复杂度与空间复杂度

- **时间复杂度**: \(O(\log n)\)，每次循环将搜索区间缩小一半。
- **空间复杂度**: \(O(1)\)，仅使用了常数级别的额外空间。

这是一种高效且优雅的解决方法。