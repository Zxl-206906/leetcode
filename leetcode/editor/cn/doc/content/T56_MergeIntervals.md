<p>以数组 <code>intervals</code> 表示若干个区间的集合，其中单个区间为 <code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code> 。请你合并所有重叠的区间，并返回&nbsp;<em>一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[2,6],[8,10],[15,18]]
<strong>输出：</strong>[[1,6],[8,10],[15,18]]
<strong>解释：</strong>区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,4],[4,5]]
<strong>输出：</strong>[[1,5]]
<strong>解释：</strong>区间 [1,4] 和 [4,5] 可被视为重叠区间。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>intervals[i].length == 2</code></li> 
 <li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 2466</li><li>👎 0</li></div>


### 问题描述

给定一个区间集合 `intervals`，其中每个区间由 `start` 和 `end` 组成。你的任务是合并所有重叠的区间，返回一个不重叠的区间数组，覆盖原始的所有区间。

### **思路解析**

这道题的核心思路是基于排序和遍历：

1. **排序**：
    - 首先，按照每个区间的 `start` 排序（如果 `start` 相同，则根据 `end` 排序）。这样，排序后，重叠的区间一定是相邻的。

2. **合并区间**：
    - 遍历排序后的区间，使用一个列表 `merged` 来保存合并后的区间。
    - 对于每个新区间，检查它是否与当前 `merged` 列表中的最后一个区间重叠：
        - 如果重叠，更新最后一个区间的 `end` 值。
        - 如果不重叠，将当前区间加入 `merged`。

3. **结束条件**：
    - 遍历完成后，`merged` 中的区间就是合并后的结果。

---

### **Java 实现**

```text
import text.util.Arrays;
import text.util.LinkedList;
import text.util.List;

public class MergeIntervals {

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        
        // 1. 按照区间的起始位置排序
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        // 2. 创建一个链表来存储合并后的区间
        List<int[]> merged = new LinkedList<>();
        
        // 3. 遍历所有区间
        for (int[] interval : intervals) {
            // 如果 merged 为空或者当前区间与最后一个合并的区间不重叠，直接加入 merged
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // 如果重叠，合并区间，更新最后一个区间的 end
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        
        // 4. 将链表转回数组
        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {
        int[][] intervals1 = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result1 = merge(intervals1);
        System.out.println(Arrays.deepToString(result1));  // 输出: [[1, 6], [8, 10], [15, 18]]

        int[][] intervals2 = {{1, 4}, {4, 5}};
        int[][] result2 = merge(intervals2);
        System.out.println(Arrays.deepToString(result2));  // 输出: [[1, 5]]
    }
}
```

---

### **详细步骤**

1. **排序**：
    - 使用 `Arrays.sort()` 按照区间的起始位置 `start` 对区间进行升序排序。
    - 这样做可以确保相邻区间的 `start` 值是递增的，从而方便判断是否重叠。

2. **合并区间**：
    - 遍历排序后的区间：
        - 如果 `merged` 为空或者当前区间的 `start` 大于上一个区间的 `end`，说明它们不重叠，直接将当前区间加入 `merged`。
        - 如果当前区间的 `start` 小于或等于上一个区间的 `end`，说明它们重叠，更新 `merged` 中最后一个区间的 `end` 值。

3. **转换为结果数组**：
    - 使用 `merged.toArray()` 将链表转换为二维数组。

---

### **时间复杂度**

- **时间复杂度**：`O(n log n)`，其中 `n` 是区间的数量。排序的时间复杂度为 `O(n log n)`，合并的时间复杂度为 `O(n)`。
- **空间复杂度**：`O(n)`，用于存储合并后的区间。

---

### **示例**

#### 示例 1：

输入：
```text
intervals = [[1, 3], [2, 6], [8, 10], [15, 18]]
```

- 排序后的区间是：`[[1, 3], [2, 6], [8, 10], [15, 18]]`。
- 合并：
    - 第一个区间 `[1, 3]` 和第二个区间 `[2, 6]` 重叠，合并为 `[1, 6]`。
    - 第三个区间 `[8, 10]` 不与上一个区间重叠，加入结果。
    - 第四个区间 `[15, 18]` 不与上一个区间重叠，加入结果。

输出：
```text
[[1, 6], [8, 10], [15, 18]]
```

#### 示例 2：

输入：
```text
intervals = [[1, 4], [4, 5]]
```

- 排序后的区间是：`[[1, 4], [4, 5]]`。
- 合并：区间 `[1, 4]` 和 `[4, 5]` 可以合并为 `[1, 5]`。

输出：
```text
[[1, 5]]
```

---

### **总结**

1. **排序**：首先对区间按 `start` 排序，可以确保我们从左到右逐个检查区间是否重叠。
2. **贪心算法**：遍历排序后的区间，合并重叠区间，避免不必要的重复。
3. **时间复杂度**：`O(n log n)`，适合大规模输入。

该方法高效且直观，是解决区间合并问题的经典解法。