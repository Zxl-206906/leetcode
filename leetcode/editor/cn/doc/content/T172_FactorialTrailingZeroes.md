<p>给定一个整数 <code>n</code> ，返回 <code>n!</code> 结果中尾随零的数量。</p>

<p>提示&nbsp;<code>n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>0
<strong>解释：</strong>3! = 6 ，不含尾随 0
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 5
<strong>输出：</strong>1
<strong>解释：</strong>5! = 120 ，有一个尾随 0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= n &lt;= 10<sup>4</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你可以设计并实现对数时间复杂度的算法来解决此问题吗？</p>

<div><li>👍 859</li><li>👎 0</li></div>


要计算一个整数 `n!`（n的阶乘）结果中尾随零的数量，我们需要了解阶乘中的尾随零是如何产生的。

### 尾随零的产生：
尾随零是由因子 `10` 生成的，而 `10` 是由 `2` 和 `5` 组成的。在阶乘的计算中，`2` 和 `5` 的配对会生成一个 `10`。然而，`2` 的出现次数总是比 `5` 多，因此尾随零的数量由 `5` 的因子决定。

### 关键点：
- 在阶乘中，`5` 出现的次数决定了尾随零的数量。
- 我们需要统计在阶乘中能够被 `5` 整除的数字的个数，以及它们的倍数。

### 思路：
1. 我们先统计 `n` 中所有能被 `5` 整除的数字。
2. 然后继续检查能被 `25`（`5^2`）整除的数字，这些数字会提供额外的 `5` 因子。
3. 同理，检查能被 `125`（`5^3`）整除的数字，依此类推，直到 `5^k` 大于 `n`。

### 算法步骤：
1. 初始化一个计数器 `count` 为 0。
2. 从 `5` 开始，检查 `n` 中能被 `5`、`25`、`125` 等整除的数字。对于每个能够整除的数字，增加 `count`。
3. 最终，`count` 就是尾随零的数量。

### 代码实现：

```java
public class FactorialTrailingZeroes {
    public int trailingZeroes(int n) {
        int count = 0;
        
        // 每次将 n 除以 5，并累加结果
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        
        return count;
    }

    public static void main(String[] args) {
        FactorialTrailingZeroes solution = new FactorialTrailingZeroes();
        
        // 测试示例
        System.out.println(solution.trailingZeroes(3));  // 输出: 0
        System.out.println(solution.trailingZeroes(5));  // 输出: 1
        System.out.println(solution.trailingZeroes(10)); // 输出: 2
        System.out.println(solution.trailingZeroes(100)); // 输出: 24
    }
}
```

### 代码解释：
1. **初始化 `count` 为 0**：用于保存尾随零的数量。
2. **循环条件 `n >= 5`**：只要 `n` 大于或等于 `5`，就继续进行以下操作。
3. **`n / 5`**：计算出能被 `5` 整除的数字的个数（即每个能提供一个尾随零的 `5` 的倍数）。
4. **`n /= 5`**：每次将 `n` 除以 `5`，继续检查 `25`、`125` 等倍数的 `5`。
5. **返回 `count`**：最终的 `count` 就是尾随零的数量。

### 计算示例：
1. **输入：`3`**
    - 3! = 6，没有尾随零。
    - 输出：`0`

2. **输入：`5`**
    - 5! = 120，包含一个尾随零。
    - 输出：`1`

3. **输入：`10`**
    - 10! = 3,628,800，包含两个尾随零。
    - 输出：`2`

4. **输入：`100`**
    - 100! 包含 24 个尾随零（因为 `100 / 5 = 20`, `100 / 25 = 4`，所以总共是 `20 + 4 = 24`）。
    - 输出：`24`

### 时间复杂度：
- **时间复杂度**：`O(log n)`。每次我们将 `n` 除以 `5`，因此需要进行大约 `log_5(n)` 次迭代。
- **空间复杂度**：`O(1)`，我们只用了常量空间。

### 总结：
这个问题通过统计阶乘中 `5` 的倍数来计算尾随零的数量。每一个 `5` 的倍数都会产生一个尾随零，因此我们只需考虑 `n` 中所有能被 `5`、`25`、`125` 等整除的数字。