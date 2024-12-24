<p>有一棵特殊的苹果树，一连 <code>n</code> 天，每天都可以长出若干个苹果。在第 <code>i</code> 天，树上会长出 <code>apples[i]</code> 个苹果，这些苹果将会在 <code>days[i]</code> 天后（也就是说，第 <code>i + days[i]</code> 天时）腐烂，变得无法食用。也可能有那么几天，树上不会长出新的苹果，此时用 <code>apples[i] == 0</code> 且 <code>days[i] == 0</code> 表示。</p>

<p>你打算每天 <strong>最多</strong> 吃一个苹果来保证营养均衡。注意，你可以在这 <code>n</code> 天之后继续吃苹果。</p>

<p>给你两个长度为 <code>n</code> 的整数数组 <code>days</code> 和 <code>apples</code> ，返回你可以吃掉的苹果的最大数目<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>apples = [1,2,3,5,2], days = [3,2,1,4,2]
<strong>输出：</strong>7
<strong>解释：</strong>你可以吃掉 7 个苹果：
- 第一天，你吃掉第一天长出来的苹果。
- 第二天，你吃掉一个第二天长出来的苹果。
- 第三天，你吃掉一个第二天长出来的苹果。过了这一天，第三天长出来的苹果就已经腐烂了。
- 第四天到第七天，你吃的都是第四天长出来的苹果。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>apples = [3,0,0,0,0,2], days = [3,0,0,0,0,2]
<strong>输出：</strong>5
<strong>解释：</strong>你可以吃掉 5 个苹果：
- 第一天到第三天，你吃的都是第一天长出来的苹果。
- 第四天和第五天不吃苹果。
- 第六天和第七天，你吃的都是第六天长出来的苹果。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>apples.length == n</code></li> 
 <li><code>days.length == n</code></li> 
 <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= apples[i], days[i] &lt;= 2 * 10<sup>4</sup></code></li> 
 <li>只有在 <code>apples[i] = 0</code> 时，<code>days[i] = 0</code> 才成立</li> 
</ul>

<div><li>👍 222</li><li>👎 0</li></div>

这个问题是一个关于“贪心算法”的问题，主要考察如何在保证苹果不腐烂的情况下，尽可能吃掉最多的苹果。

### 问题分析：
- 每天可以吃一个苹果。
- 每天树上会长出一些苹果，这些苹果有一个保质期（由 `days[i]` 给出），过期后苹果腐烂不可食用。
- 需要利用一个贪心策略，优先吃掉“即将腐烂”的苹果，而不是等待其他的苹果。
- 同时，确保我们每天最多吃一个苹果。

### 思路：
1. 我们可以使用一个 **优先队列（最大堆）** 来存储每一天可以食用的苹果。堆中每个元素是苹果的保质期，最大堆可以帮助我们每次选择最接近腐烂的苹果。
2. 每天我们都从堆中取出一个苹果，如果当前堆顶的苹果可以食用，就吃掉它。如果该苹果的保质期还没到，我们将其重新放回堆中，等待下一天。
3. 我们需要循环遍历每一天，直到所有的苹果都吃完或腐烂。

### 解决方案：
1. 使用 **优先队列（最大堆）** 来存储可以食用的苹果，每次选择保质期最小的苹果（即最接近腐烂的苹果）。
2. 对于每一天，检查堆中是否有可以食用的苹果。
3. 如果某些苹果已经腐烂（即保质期已过），我们需要将它们从堆中移除。

### 代码实现：

```java
import java.util.*;

public class T1705_MaximumApplesEaten {
    public static void main(String[] args) {
        Solution solution = new T1705_MaximumApplesEaten().new Solution();
        
        // 示例 1
        int[] apples1 = {1, 2, 3, 5, 2};
        int[] days1 = {3, 2, 1, 4, 2};
        System.out.println(solution.eatenApples(apples1, days1));  // 输出 7
        
        // 示例 2
        int[] apples2 = {3, 0, 0, 0, 0, 2};
        int[] days2 = {3, 0, 0, 0, 0, 2};
        System.out.println(solution.eatenApples(apples2, days2));  // 输出 5
    }

    class Solution {
        public int eatenApples(int[] apples, int[] days) {
            int n = apples.length;
            int result = 0;  // 最终吃掉的苹果数量
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 小根堆，用于存储苹果的腐烂日期
            
            for (int i = 0; i < n || !pq.isEmpty(); i++) {
                // 每天检查是否有新的苹果
                if (i < n && apples[i] > 0) {
                    pq.offer(new int[]{i + days[i], apples[i]}); // 保存苹果的腐烂日期和数量
                }
                
                // 如果堆中有苹果且堆顶的苹果已腐烂，移除它
                while (!pq.isEmpty() && pq.peek()[0] <= i) {
                    pq.poll();
                }
                
                // 如果堆中有可食用的苹果，吃掉一个
                if (!pq.isEmpty()) {
                    int[] top = pq.poll(); // 获取堆顶的苹果
                    result++; // 吃掉一个苹果
                    top[1]--; // 该苹果数量减少
                    if (top[1] > 0) {
                        pq.offer(top); // 如果还有剩余的苹果，重新加入堆
                    }
                }
            }
            
            return result;
        }
    }
}
```

### 解释：
1. **优先队列 `pq`**：
    - 我们使用 `PriorityQueue<int[]>` 来存储苹果。队列中的元素是一个数组，其中 `int[0]` 表示苹果的腐烂日期（即 `i + days[i]`），`int[1]` 表示当前有多少个苹果。
    - 这个堆是小根堆，优先取出腐烂日期最早的苹果。

2. **遍历苹果**：
    - 我们遍历每一天。如果当天有苹果长出来（`apples[i] > 0`），就将这些苹果的腐烂日期和数量存入堆中。
    - 每天如果堆顶的苹果已经腐烂，我们就从堆中移除这些腐烂的苹果。

3. **吃苹果**：
    - 每天从堆中取出一个苹果（如果堆中有苹果），并将苹果数量减 1。如果剩余苹果数量大于 0，则将更新后的苹果重新放回堆中。

4. **结束条件**：
    - 当所有的苹果都腐烂或者都吃完时，返回结果。

### 复杂度分析：
- **时间复杂度**：
    - 对于每一天，我们最多进行一次堆操作，堆操作的时间复杂度是 `O(log k)`，其中 `k` 是堆中的元素数量（最多为 `n`，因为每个苹果最多只会进入堆一次）。
    - 总体的时间复杂度是 `O(n log n)`，其中 `n` 是 `apples` 数组的长度。

- **空间复杂度**：
    - 需要存储堆中的苹果，最坏情况下堆的大小为 `n`，所以空间复杂度是 `O(n)`。

### 示例：
1. **示例 1**：
   ```java
   apples = [1, 2, 3, 5, 2]
   days = [3, 2, 1, 4, 2]
   ```
    - 第一天吃第一个苹果。
    - 第二天吃第二天长出来的苹果。
    - 第三天吃第二天长出来的苹果。
    - 第四天到第七天，吃第四天长出来的苹果。
    - 结果为 7 个苹果。

2. **示例 2**：
   ```java
   apples = [3, 0, 0, 0, 0, 2]
   days = [3, 0, 0, 0, 0, 2]
   ```
    - 第一天到第三天，吃第一天长出来的苹果。
    - 第六天和第七天，吃第六天长出来的苹果。
    - 结果为 5 个苹果。

### 总结：
这道题通过合理利用优先队列（小根堆）来模拟每天吃苹果的过程，确保我们尽可能多地吃掉即将腐烂的苹果，并且在每一天都可以食用一个苹果。