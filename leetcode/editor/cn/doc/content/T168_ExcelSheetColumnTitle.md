<p>给你一个整数&nbsp;<code>columnNumber</code> ，返回它在 Excel 表中相对应的列名称。</p>

<p>例如：</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28 
...
</pre>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>columnNumber = 1
<strong>输出：</strong>"A"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>columnNumber = 28
<strong>输出：</strong>"AB"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>columnNumber = 701
<strong>输出：</strong>"ZY"
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>columnNumber = 2147483647
<strong>输出：</strong>"FXSHRXW"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= columnNumber &lt;= 2<sup>31</sup> - 1</code></li> 
</ul>

<div><li>👍 709</li><li>👎 0</li></div>

这个问题的本质是将给定的整数 `columnNumber` 转换为 Excel 列名称。它与 26 进制类似，但与普通的进制不同，它是从 1 开始的，而不是从 0 开始。因此，我们需要在转换过程中做一些调整。

### 思路

我们可以将 `columnNumber` 逐步转换成对应的字母。由于 Excel 列名称使用的是 **字母表的 1 到 26** 编码（即 `A` 对应 1，`B` 对应 2，直到 `Z` 对应 26），但是在转换过程中需要考虑到“进制”的特性。

**步骤：**
1. 每次取 `columnNumber` 减 1 后，对 26 取余数，这个余数对应于字母表中的一个字母（从 0 对应 `A` 到 25 对应 `Z`）。
2. 将余数映射为字母，然后更新 `columnNumber` 为 `columnNumber / 26`（向下取整）。
3. 重复这个过程，直到 `columnNumber` 为 0。

### 关键点：
- **`columnNumber - 1`**：这是为了避免 `A` 对应 0 的情况。
- **取余数后映射字母**：通过 `chr(remainder + ord('A'))` 可以得到对应的字母。
- **更新 `columnNumber`**：除以 26 后，继续进行下一位的计算。

### Java 实现

```java
public class ExcelColumn {
    public String convertToTitle(int columnNumber) {
        StringBuilder result = new StringBuilder();
        
        // 不断处理列编号，直到 columnNumber 为 0
        while (columnNumber > 0) {
            // 计算余数并映射成对应的字母
            columnNumber--; // 这里减去 1 是为了避免出现从 0 开始的情况
            result.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        
        // 结果是逆序的，需要反转字符串
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        ExcelColumn solution = new ExcelColumn();

        // 示例测试
        System.out.println(solution.convertToTitle(1)); // 输出: "A"
        System.out.println(solution.convertToTitle(28)); // 输出: "AB"
        System.out.println(solution.convertToTitle(701)); // 输出: "ZY"
        System.out.println(solution.convertToTitle(2147483647)); // 输出: "FXSHRXW"
    }
}
```

### 代码解释

1. **`columnNumber--`**：每次在取余前，将 `columnNumber` 减去 1，以确保列的编号从 1 开始。
2. **`columnNumber % 26`**：取余数得到当前列对应的字母位置（0-25）。
3. **`(char) ('A' + columnNumber % 26)`**：将余数转化为字符，例如余数为 0 时得到 `A`，余数为 1 时得到 `B`。
4. **`columnNumber /= 26`**：将 `columnNumber` 除以 26，进入下一位的计算。
5. 最终得到的字母是按逆序顺序排列的，需要调用 `result.reverse()` 将其反转。

### 示例分析

#### 示例 1:
输入：`columnNumber = 1`
- 1 对应字母 `A`，所以输出 `"A"`。

#### 示例 2:
输入：`columnNumber = 28`
- 28 对应字母 `AB`。
    - 第一次计算，余数为 1，对应 `B`，然后 `columnNumber` 更新为 28 / 26 = 1。
    - 第二次计算，余数为 0，对应 `A`，然后 `columnNumber` 更新为 0。
- 输出 `"AB"`。

#### 示例 3:
输入：`columnNumber = 701`
- 701 对应字母 `ZY`。
    - 第一次计算，余数为 0，对应 `Z`，然后 `columnNumber` 更新为 701 / 26 = 26。
    - 第二次计算，余数为 25，对应 `Y`，然后 `columnNumber` 更新为 26 / 26 = 1。
    - 最后输出 `"ZY"`。

#### 示例 4:
输入：`columnNumber = 2147483647`
- 通过反复计算，可以得出对应字母 `"FXSHRXW"`。

### 时间复杂度

每次通过除以 26，`columnNumber` 会减少。假设 `columnNumber` 的值为 `n`，则所需的计算次数为 \( O(\log_{26} n) \)，即大约为 \( O(\log n) \)，其中 `n` 是 `columnNumber` 的值。因此，时间复杂度是 **O(log n)**。

### 空间复杂度

使用了一个 `StringBuilder` 来存储最终的结果，空间复杂度为 **O(log n)**。