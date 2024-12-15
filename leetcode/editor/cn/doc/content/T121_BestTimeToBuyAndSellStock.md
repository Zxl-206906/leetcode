<p>给定一个数组 <code>prices</code> ，它的第&nbsp;<code>i</code> 个元素&nbsp;<code>prices[i]</code> 表示一支给定股票第 <code>i</code> 天的价格。</p>

<p>你只能选择 <strong>某一天</strong> 买入这只股票，并选择在 <strong>未来的某一个不同的日子</strong> 卖出该股票。设计一个算法来计算你所能获取的最大利润。</p>

<p>返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 <code>0</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[7,1,5,3,6,4]
<strong>输出：</strong>5
<strong>解释：</strong>在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
     注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>prices = [7,6,4,3,1]
<strong>输出：</strong>0
<strong>解释：</strong>在这种情况下, 没有交易完成, 所以最大利润为 0。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= prices[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 3649</li><li>👎 0</li></div>

这道题目可以通过一次遍历的贪心算法高效解决，核心思路是记录到当前为止的最低股票价格，并计算在该价格下卖出的最大利润。

---

### **解法：贪心算法**
1. 遍历数组 `prices`。
2. 在遍历的过程中，维护两个变量：
    - `minPrice`：记录当前为止的最低股票价格。
    - `maxProfit`：记录当前为止的最大利润。
3. 对于每一天的价格：
    - 如果当前价格比 `minPrice` 低，更新 `minPrice`。
    - 否则，计算以当前价格卖出时的利润，并更新 `maxProfit`。

---

### **Java 实现代码**
```java
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 初始化为最大值
        int maxProfit = 0; // 初始化为0
        
        for (int price : prices) {
            if (price < minPrice) {
                // 更新最低价格
                minPrice = price;
            } else {
                // 计算当前价格卖出的利润并更新最大利润
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }
        
        return maxProfit;
    }
}
```

---

### **示例运行**
#### 示例 1：
- 输入：`prices = [7,1,5,3,6,4]`
- 执行过程：
    1. 初始：`minPrice = ∞`, `maxProfit = 0`
    2. 第1天价格=7，`minPrice = 7`，`maxProfit = 0`
    3. 第2天价格=1，`minPrice = 1`，`maxProfit = 0`
    4. 第3天价格=5，`maxProfit = max(0, 5-1) = 4`
    5. 第4天价格=3，`maxProfit = max(4, 3-1) = 4`
    6. 第5天价格=6，`maxProfit = max(4, 6-1) = 5`
    7. 第6天价格=4，`maxProfit = max(5, 4-1) = 5`
- 输出：`5`

#### 示例 2：
- 输入：`prices = [7,6,4,3,1]`
- 执行过程：
    1. 初始：`minPrice = ∞`, `maxProfit = 0`
    2. 每一天都更新 `minPrice`，但 `maxProfit` 始终为 `0`。
- 输出：`0`

---

### **复杂度分析**
- **时间复杂度：** O(n)，只需遍历一次数组。
- **空间复杂度：** O(1)，只用常数空间。

---

### **扩展问题**
- 如果允许多次交易，且两次交易之间不能重叠，可以用动态规划解决。
- 如果只允许最多两次交易，或者引入交易费用，这些问题都可以通过不同的动态规划状态设计来解决。