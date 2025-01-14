<p>给定一个未排序的整数数组 <code>nums</code> ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。</p>

<p>请你设计并实现时间复杂度为&nbsp;<code>O(n)</code><em> </em>的算法解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [100,4,200,1,3,2]
<strong>输出：</strong>4
<strong>解释：</strong>最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,3,7,2,5,8,4,6,0,1]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= nums.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
</ul>

<div><li>👍 2300</li><li>👎 0</li></div>


要解决这个问题，我们可以使用哈希集（HashSet）来帮助我们找到数字连续的最长序列，并确保算法的时间复杂度为 \(O(n)\)。

### 思路：

1. **哈希集存储所有元素**：
    - 我们首先将数组中的所有元素插入到一个哈希集 `set` 中，这样可以在 \(O(1)\) 时间内判断某个元素是否存在。

2. **查找连续序列**：
    - 对于数组中的每个元素，我们检查它是否是某个连续序列的起点。一个元素 `x` 是序列的起点，当且仅当 `x-1` 不在哈希集中。这样可以避免重复计算。

3. **扩展序列**：
    - 一旦确定了序列的起点，我们就可以通过查找 `x+1`, `x+2`, … 来扩展这个序列。每找到一个连续的元素，就继续查找下一个元素，直到序列不再连续为止。

4. **记录最大长度**：
    - 对每个序列的长度进行计算，最终得到最长的连续序列。

### 代码实现：

```java
import java.util.HashSet;

public class Solution {
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 使用 HashSet 存储所有元素
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longest = 0;

        // 遍历每个元素，寻找它的连续序列
        for (int num : set) {
            // 如果 num - 1 不在 set 中，说明 num 是一个序列的起点
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentLength = 1;

                // 扩展序列，找到最长的连续子序列
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentLength++;
                }

                // 更新最长序列的长度
                longest = Math.max(longest, currentLength);
            }
        }

        return longest;
    }
}
```

### 解释：

1. **初始化哈希集**：
    - 将数组 `nums` 中的所有元素插入到哈希集 `set` 中。哈希集的查找、插入操作都能在平均 \(O(1)\) 时间内完成。

2. **遍历哈希集**：
    - 对每个元素 `num`，检查它是否是某个连续序列的起点。如果 `num - 1` 不在集合中，说明 `num` 是一个新的连续序列的开始。

3. **扩展序列**：
    - 从当前的起点 `num` 开始，检查 `num+1`, `num+2`, … 是否在哈希集中，直到找到不连续的元素为止。这样可以得到一个连续的序列。

4. **更新最大长度**：
    - 记录每次找到的连续序列的长度，并更新 `longest` 变量，最终得到最长的连续序列长度。

### 复杂度分析：

- **时间复杂度**：\(O(n)\)，其中 \(n\) 是数组的长度。每个元素最多被访问两次：一次是插入到哈希集中，另一次是检查连续序列。因此，整个算法的时间复杂度是线性的。

- **空间复杂度**：\(O(n)\)，需要额外的空间来存储哈希集 `set`，其大小为数组中不同元素的数量。

### 示例分析：

#### 示例 1：

输入：
```text
nums = [100, 4, 200, 1, 3, 2]
```

- 将所有元素插入到哈希集 `set` 中：`{100, 4, 200, 1, 3, 2}`。
- 遍历元素：
    - 从 `1` 开始，找到 `[1, 2, 3, 4]`，长度为 4。
    - 其他元素（如 `100`, `200`）不是新的序列起点，不会重新计算。
- 最终输出最长的连续序列长度：`4`。

#### 示例 2：

输入：
```text
nums = [0, 3, 7, 2, 5, 8, 4, 6, 0, 1]
```

- 将所有元素插入到哈希集 `set` 中：`{0, 3, 7, 2, 5, 8, 4, 6, 1}`。
- 遍历元素：
    - 从 `0` 开始，找到 `[0, 1, 2, 3, 4, 5, 6, 7, 8]`，长度为 9。
- 最终输出最长的连续序列长度：`9`。

通过这种方法，我们能够在 \(O(n)\) 时间内解决问题，并且能有效处理大规模的输入数据。