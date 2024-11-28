<p>给你一个<strong> 无重叠的</strong><em> ，</em>按照区间起始端点排序的区间列表 <code>intervals</code>，其中&nbsp;<code>intervals[i] = [start<sub>i</sub>, end<sub>i</sub>]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;个区间的开始和结束，并且&nbsp;<code>intervals</code>&nbsp;按照&nbsp;<code>start<sub>i</sub></code>&nbsp;升序排列。同样给定一个区间&nbsp;<code>newInterval = [start, end]</code>&nbsp;表示另一个区间的开始和结束。</p>

<p>在&nbsp;<code>intervals</code> 中插入区间&nbsp;<code>newInterval</code>，使得&nbsp;<code>intervals</code>&nbsp;依然按照&nbsp;<code>start<sub>i</sub></code>&nbsp;升序排列，且区间之间不重叠（如果有必要的话，可以合并区间）。</p>

<p>返回插入之后的&nbsp;<code>intervals</code>。</p>

<p><strong>注意</strong> 你不需要原地修改&nbsp;<code>intervals</code>。你可以创建一个新数组然后返回它。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,3],[6,9]], newInterval = [2,5]
<strong>输出：</strong>[[1,5],[6,9]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
<strong>输出：</strong>[[1,2],[3,10],[12,16]]
<strong>解释：</strong>这是因为新的区间 <span><code>[4,8]</code></span> 与 <span><code>[3,5],[6,7],[8,10]</code></span>&nbsp;重叠。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= intervals.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>intervals[i].length == 2</code></li> 
 <li><code>0 &lt;=&nbsp;start<sub>i</sub> &lt;=&nbsp;end<sub>i</sub> &lt;= 10<sup>5</sup></code></li> 
 <li><code>intervals</code> 根据 <code>start<sub>i</sub></code> 按 <strong>升序</strong> 排列</li> 
 <li><code>newInterval.length == 2</code></li> 
 <li><code>0 &lt;=&nbsp;start &lt;=&nbsp;end &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><li>👍 940</li><li>👎 0</li></div>



### 问题描述

给定一个按照 `start` 升序排序的区间列表 `intervals`，以及一个新的区间 `newInterval`，我们需要将 `newInterval` 插入到 `intervals` 中，确保：
1. `intervals` 依然按升序排列。
2. 区间之间不重叠，如果有重叠，需要合并。

我们不能原地修改 `intervals`，可以创建一个新的列表并返回。

---

### **思路解析**

1. **按顺序插入**：
    - 由于 `intervals` 已经按 `start` 升序排列，我们可以遍历 `intervals`，将新区间 `newInterval` 插入合适的位置。
    - 如果新区间和某个区间没有重叠，则直接加入新区间。
    - 如果新区间和某个区间有重叠，合并这两个区间，并继续处理。

2. **合并区间**：
    - 如果新区间的 `start` 在当前区间的 `end` 之前，说明它们有重叠，我们更新新区间的 `end` 为两个区间的最大 `end` 值。
    - 如果新区间的 `end` 在当前区间的 `start` 之后，说明没有重叠，我们可以将新区间加入结果中。

3. **处理完新区间后**：
    - 对于剩余的区间，如果它们不再和新区间重叠，直接加入到结果中。

4. **边界条件**：
    - 如果 `intervals` 为空，直接返回 `newInterval`。
    - 如果 `newInterval` 在 `intervals` 的最前面或最后面，也可以直接插入。

---

### **Java 实现**

```text
import text.util.*;

public class InsertInterval {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        
        int i = 0, n = intervals.length;
        
        // 1. 将所有在 newInterval 前面的区间添加到结果中
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // 2. 合并所有与 newInterval 重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        
        // 将合并后的 newInterval 加入结果
        result.add(newInterval);
        
        // 3. 将所有在 newInterval 后面的区间添加到结果中
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        // 转换成二维数组并返回
        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
        // 测试示例 1
        int[][] intervals1 = {{1, 3}, {6, 9}};
        int[] newInterval1 = {2, 5};
        int[][] result1 = insert(intervals1, newInterval1);
        System.out.println(Arrays.deepToString(result1));  // 输出: [[1, 5], [6, 9]]

        // 测试示例 2
        int[][] intervals2 = {{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}};
        int[] newInterval2 = {4, 8};
        int[][] result2 = insert(intervals2, newInterval2);
        System.out.println(Arrays.deepToString(result2));  // 输出: [[1, 2], [3, 10], [12, 16]]
    }
}
```

---

### **详细步骤**

1. **初始化**：创建一个 `List<int[]> result` 来保存合并后的结果。初始化索引 `i` 和 `n`，分别表示当前区间的下标和区间列表的长度。

2. **插入新区间**：
    - **将所有在新区间之前的区间加入结果**：遍历所有区间，直到找到第一个 `end` 值大于 `newInterval[0]` 的区间。
    - **合并新区间和重叠区间**：遍历所有区间，合并所有与 `newInterval` 重叠的区间，更新 `newInterval` 的 `start` 和 `end`。
    - **将新区间加入结果**：当所有重叠区间处理完后，将合并后的 `newInterval` 添加到结果中。

3. **添加剩余区间**：将所有在新区间后面的区间加入结果。

4. **返回结果**：将 `result` 转换成二维数组返回。

---

### **时间复杂度**

- **时间复杂度**：`O(n)`，其中 `n` 是区间的数量。我们遍历了所有区间一次，因此时间复杂度为线性。
- **空间复杂度**：`O(n)`，用于存储合并后的区间。

---

### **示例**

#### 示例 1：

输入：
```text
intervals = [[1, 3], [6, 9]], newInterval = [2, 5]
```

- 区间 `[1, 3]` 和 `[2, 5]` 重叠，合并为 `[1, 5]`。
- 区间 `[6, 9]` 不重叠，加入结果。

输出：
```text
[[1, 5], [6, 9]]
```

#### 示例 2：

输入：
```text
intervals = [[1, 2], [3, 5], [6, 7], [8, 10], [12, 16]], newInterval = [4, 8]
```

- 区间 `[3, 5]`, `[6, 7]`, `[8, 10]` 与 `[4, 8]` 重叠，合并为 `[3, 10]`。
- 区间 `[1, 2]` 和 `[12, 16]` 不重叠，直接加入结果。

输出：
```text
[[1, 2], [3, 10], [12, 16]]
```

---

### **总结**

1. **排序与合并**：首先利用 `intervals` 的排序特性，逐步合并新区间与重叠的区间，最后处理不重叠的区间。
2. **时间复杂度**：该算法的时间复杂度是 `O(n)`，适合处理大规模数据。
3. **空间复杂度**：使用 `O(n)` 空间来存储合并后的结果，空间复杂度也是线性的。