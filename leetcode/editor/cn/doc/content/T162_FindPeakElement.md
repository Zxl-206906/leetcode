<p>峰值元素是指其值严格大于左右相邻值的元素。</p>

<p>给你一个整数数组&nbsp;<code>nums</code>，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 <strong>任何一个峰值</strong> 所在位置即可。</p>

<p>你可以假设&nbsp;<code>nums[-1] = nums[n] = -∞</code> 。</p>

<p>你必须实现时间复杂度为 <code>O(log n)</code><em> </em>的算法来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = <span><code>[1,2,3,1]</code></span>
<strong>输出：</strong>2
<strong>解释：</strong>3 是峰值元素，你的函数应该返回其索引 2。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = <span><code>[</code></span>1,2,1,3,5,6,4]
<strong>输出：</strong>1 或 5 
<strong>解释：</strong>你的函数可以返回索引 1，其峰值元素为 2；
&nbsp;    或者返回索引 5， 其峰值元素为 6。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 1000</code></li> 
 <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
 <li>对于所有有效的 <code>i</code> 都有 <code>nums[i] != nums[i + 1]</code></li> 
</ul>

<div><li>👍 1346</li><li>👎 0</li></div>

要解决这个问题，题目要求我们以 \( O(\log n) \) 的时间复杂度找到一个峰值元素的索引。这意味着我们需要使用 **二分查找** 的方式，而不是简单的线性扫描。

---

## 思路解析

1. **定义峰值**:
   峰值是指数组中某个元素比其左右相邻元素都大。例如，若 `nums[i] > nums[i-1]` 且 `nums[i] > nums[i+1]`，则 `nums[i]` 是峰值。

2. **二分查找的核心思想**:
    - 我们每次取中间位置 `mid`，比较 `nums[mid]` 和 `nums[mid+1]`：
        - 如果 `nums[mid] > nums[mid+1]`，说明峰值在左侧（包含 `mid`）。
        - 如果 `nums[mid] < nums[mid+1]`，说明峰值在右侧（不包含 `mid`）。
    - 根据上述比较，不断缩小搜索范围，最终确定峰值位置。

3. **边界条件**:
    - 数组的两端可以看作是 \(-\infty\)，因此无需显式处理边界条件。
    - 数组中的元素互不相同（即 \( nums[i] \neq nums[i+1] \)），不会出现平坦的区域。

---

## Java 实现

以下是基于二分查找的解决方案：

```java
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            // 比较 mid 和 mid+1 的值
            if (nums[mid] > nums[mid + 1]) {
                // 峰值在左侧（包含 mid）
                right = mid;
            } else {
                // 峰值在右侧
                left = mid + 1;
            }
        }
        
        // 最终 left == right，返回任意一个都可以
        return left;
    }
}
```

---

## 示例分析

### 示例 1

**输入**: `nums = [1, 2, 3, 1]`  
**过程**:
- 初始 \( left = 0, right = 3 \)，计算 \( mid = 1 \)。比较 \( nums[1] = 2 \) 和 \( nums[2] = 3 \)，右侧较大，令 \( left = 2 \)。
- 再次计算 \( mid = 2 \)，比较 \( nums[2] = 3 \) 和 \( nums[3] = 1 \)，左侧较大，令 \( right = 2 \)。
- 此时 \( left == right == 2 \)，返回 `2`。

**输出**: `2`

---

### 示例 2

**输入**: `nums = [1, 2, 1, 3, 5, 6, 4]`  
**过程**:
- 初始 \( left = 0, right = 6 \)，计算 \( mid = 3 \)。比较 \( nums[3] = 3 \) 和 \( nums[4] = 5 \)，右侧较大，令 \( left = 4 \)。
- 再次计算 \( mid = 5 \)，比较 \( nums[5] = 6 \) 和 \( nums[6] = 4 \)，左侧较大，令 \( right = 5 \)。
- 此时 \( left == right == 5 \)，返回 `5`。

**输出**: `1` 或 `5`

---

## 时间与空间复杂度

1. **时间复杂度**: \( O(\log n) \)，二分查找将范围每次缩小一半。
2. **空间复杂度**: \( O(1) \)，只使用常量空间。

---

如果有其他问题或需要进一步分析，欢迎继续探讨！ 😊
