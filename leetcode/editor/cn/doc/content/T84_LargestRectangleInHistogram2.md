<p>给定 <em>n</em> 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。</p>

<p>求在该柱状图中，能够勾勒出来的矩形的最大面积。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram.jpg" /></p>

<pre>
<strong>输入：</strong>heights = [2,1,5,6,2,3]
<strong>输出：</strong>10
<strong>解释：</strong>最大的矩形为图中红色区域，面积为 10
</pre>

<p><strong>示例 2：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2021/01/04/histogram-1.jpg" /></p>

<pre>
<strong>输入：</strong> heights = [2,4]
<b>输出：</b> 4</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= heights.length &lt;=10<sup>5</sup></code></li> 
 <li><code>0 &lt;= heights[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 2836</li><li>👎 0</li></div>

由于遍历了两遍，所以效率一般。

能不能优化一下呢？



当然可以！



其实在所有的大于等于自己的元素出栈之后，剩下的那个，就是左边的第一个比自己小的元素！这样子就不需要从右到左再遍历一次了。

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        // 定义单调栈，栈内元素是柱子的索引，栈内索引对应的高度是单调递增的
        Stack<Integer> s = new Stack<>();
        // 初始化lefLower数组存储每根柱子向左遇到的第一个高度小于它的柱子的索引
        int[] lefLower = new int[heights.length];
        // 初始化rigLower数组存储每根柱子向右遇到的第一个高度小于它的柱子的索引
        int[] rigLower = new int[heights.length];
        // 默认将rigLower中的所有值设为heights的长度，这表示默认情况下每根柱子向右可以扩展到数组的最右边
        Arrays.fill(rigLower, heights.length);
        
        for (int i = 0; i < heights.length; i++) {
            // 如果栈不为空，且当前柱子的高度小于栈顶柱子的高度，则表示当前柱子是栈顶柱子向右的第一个低柱
            while (!s.empty() && heights[s.peek()] > heights[i]) {
                rigLower[s.pop()] = i;
            }
            // 当前柱子向左的第一个低柱的索引是栈顶的索引。如果栈为空，则表示没有这样的柱子，所以值为-1
            lefLower[i] = s.empty() ? -1 : s.peek();
            // 将当前柱子的索引入栈
            s.push(i);
        }
        
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            // 对于每根柱子，其对应的最大矩形面积是 (rigLower[i] - lefLower[i] - 1) * heights[i]
            res = Math.max(res, (rigLower[i] - lefLower[i] - 1) * heights[i]);
        }
        return res;
    }
}
```

