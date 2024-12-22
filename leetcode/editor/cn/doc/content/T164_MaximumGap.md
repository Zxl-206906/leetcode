<p>给定一个无序的数组&nbsp;<code>nums</code>，返回 <em>数组在排序之后，相邻元素之间最大的差值</em> 。如果数组元素个数小于 2，则返回 <code>0</code> 。</p>

<p>您必须编写一个在「线性时间」内运行并使用「线性额外空间」的算法。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> nums = [3,6,9,1]
<strong>输出:</strong> 3
<strong>解释:</strong> 排序后的数组是 [1,3,6,9]<strong><em>, </em></strong>其中相邻元素 (3,6) 和 (6,9) 之间都存在最大差值 3。</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> nums = [10]
<strong>输出:</strong> 0
<strong>解释:</strong> 数组元素个数小于 2，因此返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
</ul>

<div><li>👍 637</li><li>👎 0</li></div>


这是一个经典的「最大间距问题」（Maximum Gap Problem），要求在排序数组中找到相邻元素之间的最大差值，同时要求算法具有线性时间复杂度和线性额外空间复杂度。

由于要求线性时间复杂度，我们不能直接使用常规的排序算法（例如 `O(n log n)` 的快速排序或归并排序）。可以通过 **桶排序（Bucket Sort）** 的思想来解决此问题。

---

### 思路解析

1. **最小值和最大值：**
   计算数组中的最小值和最大值，记为 `minNum` 和 `maxNum`。

2. **计算桶的大小和数量：**
   每个桶的大小 `bucketSize` 可以定义为：
   \[
   \text{bucketSize} = \max(1, \lfloor \frac{\text{maxNum} - \text{minNum}}{n - 1} \rfloor)
   \]
   这里的 `n` 是数组的长度，使用 `max(1, ...)` 确保桶的大小至少为 1。

   桶的数量为：
   \[
   \text{bucketCount} = \lceil \frac{\text{maxNum} - \text{minNum}}{\text{bucketSize}} \rceil
   \]

3. **分配元素到桶：**
   将数组中的每个元素放入对应的桶中，每个桶只存储当前桶的最小值和最大值，忽略其他元素。这样可以节省空间。

4. **计算最大间距：**
   遍历所有桶，计算相邻非空桶之间的最大差值，这个差值等于前一个桶的最大值与后一个桶的最小值之差。

5. **特例处理：**
    - 如果数组长度小于 2，直接返回 0。

---

### Java 实现

以下是基于上述思路的完整代码实现：

```java
import java.util.Arrays;

public class MaximumGap {
    public int maximumGap(int[] nums) {
        // 如果数组长度小于 2，直接返回 0
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        int minNum = Arrays.stream(nums).min().getAsInt();
        int maxNum = Arrays.stream(nums).max().getAsInt();

        // 如果数组中的所有元素相等，返回 0
        if (minNum == maxNum) {
            return 0;
        }

        // 计算桶的大小和数量
        int bucketSize = Math.max(1, (maxNum - minNum) / (n - 1));
        int bucketCount = (maxNum - minNum) / bucketSize + 1;

        // 初始化桶，每个桶存储两个值：最小值和最大值
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // 将每个元素放入对应的桶中
        for (int num : nums) {
            int bucketIndex = (num - minNum) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // 遍历桶，计算最大间距
        int maxGap = 0;
        int previousMax = minNum; // 上一个非空桶的最大值
        for (int i = 0; i < bucketCount; i++) {
            // 跳过空桶
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            // 当前桶的最小值与前一个非空桶的最大值之间的差值
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            // 更新前一个非空桶的最大值
            previousMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        MaximumGap solution = new MaximumGap();

        // 示例测试
        int[] nums1 = {3, 6, 9, 1};
        System.out.println(solution.maximumGap(nums1)); // 输出: 3

        int[] nums2 = {10};
        System.out.println(solution.maximumGap(nums2)); // 输出: 0
    }
}
```

---

### 复杂度分析

1. **时间复杂度：**
    - 计算最小值和最大值需要 \(O(n)\)。
    - 将元素放入桶中需要 \(O(n)\)。
    - 遍历桶计算最大间距需要 \(O(k)\)，其中 \(k\) 是桶的数量，且 \(k \leq n\)。
    - 总体时间复杂度为 \(O(n)\)。

2. **空间复杂度：**
    - 额外空间用于存储桶的最小值和最大值，空间复杂度为 \(O(k)\)，其中 \(k \leq n\)。
    - 总体空间复杂度为 \(O(n)\)。

---

### 示例分析

#### 示例 1:
输入: `nums = [3,6,9,1]`
- 最小值: `1`，最大值: `9`
- 桶大小: \( \lfloor \frac{9 - 1}{4 - 1} \rfloor = 2 \)
- 桶分布:
    - 桶 0: [1] (最小值: 1, 最大值: 1)
    - 桶 1: [3] (最小值: 3, 最大值: 3)
    - 桶 2: [6] (最小值: 6, 最大值: 6)
    - 桶 3: [9] (最小值: 9, 最大值: 9)
- 最大间距: \(\max(3 - 1, 6 - 3, 9 - 6) = 3\)  
  输出: `3`

#### 示例 2:
输入: `nums = [10]`
- 数组长度小于 2，直接返回 `0`。  
  输出: `0`这是一个经典的「最大间距问题」（Maximum Gap Problem），要求在排序数组中找到相邻元素之间的最大差值，同时要求算法具有线性时间复杂度和线性额外空间复杂度。

由于要求线性时间复杂度，我们不能直接使用常规的排序算法（例如 `O(n log n)` 的快速排序或归并排序）。可以通过 **桶排序（Bucket Sort）** 的思想来解决此问题。

---

### 思路解析

1. **最小值和最大值：**
   计算数组中的最小值和最大值，记为 `minNum` 和 `maxNum`。

2. **计算桶的大小和数量：**
   每个桶的大小 `bucketSize` 可以定义为：
   \[
   \text{bucketSize} = \max(1, \lfloor \frac{\text{maxNum} - \text{minNum}}{n - 1} \rfloor)
   \]
   这里的 `n` 是数组的长度，使用 `max(1, ...)` 确保桶的大小至少为 1。

   桶的数量为：
   \[
   \text{bucketCount} = \lceil \frac{\text{maxNum} - \text{minNum}}{\text{bucketSize}} \rceil
   \]

3. **分配元素到桶：**
   将数组中的每个元素放入对应的桶中，每个桶只存储当前桶的最小值和最大值，忽略其他元素。这样可以节省空间。

4. **计算最大间距：**
   遍历所有桶，计算相邻非空桶之间的最大差值，这个差值等于前一个桶的最大值与后一个桶的最小值之差。

5. **特例处理：**
    - 如果数组长度小于 2，直接返回 0。

---

### Java 实现

以下是基于上述思路的完整代码实现：

```java
import java.util.Arrays;

public class MaximumGap {
    public int maximumGap(int[] nums) {
        // 如果数组长度小于 2，直接返回 0
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int n = nums.length;
        int minNum = Arrays.stream(nums).min().getAsInt();
        int maxNum = Arrays.stream(nums).max().getAsInt();

        // 如果数组中的所有元素相等，返回 0
        if (minNum == maxNum) {
            return 0;
        }

        // 计算桶的大小和数量
        int bucketSize = Math.max(1, (maxNum - minNum) / (n - 1));
        int bucketCount = (maxNum - minNum) / bucketSize + 1;

        // 初始化桶，每个桶存储两个值：最小值和最大值
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // 将每个元素放入对应的桶中
        for (int num : nums) {
            int bucketIndex = (num - minNum) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // 遍历桶，计算最大间距
        int maxGap = 0;
        int previousMax = minNum; // 上一个非空桶的最大值
        for (int i = 0; i < bucketCount; i++) {
            // 跳过空桶
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            // 当前桶的最小值与前一个非空桶的最大值之间的差值
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            // 更新前一个非空桶的最大值
            previousMax = bucketMax[i];
        }

        return maxGap;
    }

    public static void main(String[] args) {
        MaximumGap solution = new MaximumGap();

        // 示例测试
        int[] nums1 = {3, 6, 9, 1};
        System.out.println(solution.maximumGap(nums1)); // 输出: 3

        int[] nums2 = {10};
        System.out.println(solution.maximumGap(nums2)); // 输出: 0
    }
}
```

---

### 复杂度分析

1. **时间复杂度：**
    - 计算最小值和最大值需要 \(O(n)\)。
    - 将元素放入桶中需要 \(O(n)\)。
    - 遍历桶计算最大间距需要 \(O(k)\)，其中 \(k\) 是桶的数量，且 \(k \leq n\)。
    - 总体时间复杂度为 \(O(n)\)。

2. **空间复杂度：**
    - 额外空间用于存储桶的最小值和最大值，空间复杂度为 \(O(k)\)，其中 \(k \leq n\)。
    - 总体空间复杂度为 \(O(n)\)。

---

### 示例分析

#### 示例 1:
输入: `nums = [3,6,9,1]`
- 最小值: `1`，最大值: `9`
- 桶大小: \( \lfloor \frac{9 - 1}{4 - 1} \rfloor = 2 \)
- 桶分布:
    - 桶 0: [1] (最小值: 1, 最大值: 1)
    - 桶 1: [3] (最小值: 3, 最大值: 3)
    - 桶 2: [6] (最小值: 6, 最大值: 6)
    - 桶 3: [9] (最小值: 9, 最大值: 9)
- 最大间距: \(\max(3 - 1, 6 - 3, 9 - 6) = 3\)  
  输出: `3`

#### 示例 2:
输入: `nums = [10]`
- 数组长度小于 2，直接返回 `0`。  
  输出: `0`