<p>给你一个字符串&nbsp;<code>columnTitle</code> ，表示 Excel 表格中的列名称。返回 <em>该列名称对应的列序号</em>&nbsp;。</p>

<p>例如：</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28 
...</pre>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> columnTitle = "A"
<strong>输出:</strong> 1
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>columnTitle = "AB"
<strong>输出:</strong> 28
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>columnTitle = "ZY"
<strong>输出:</strong> 701</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= columnTitle.length &lt;= 7</code></li> 
 <li><code>columnTitle</code> 仅由大写英文组成</li> 
 <li><code>columnTitle</code> 在范围 <code>["A", "FXSHRXW"]</code> 内</li> 
</ul>

<div><li>👍 419</li><li>👎 0</li></div>

这个问题要求我们将 Excel 表格中的列名转换为对应的列序号。Excel 中的列名系统其实类似于一个 **26 进制** 的系统，其中每一位对应字母的顺序，A 对应 1，B 对应 2，一直到 Z 对应 26，然后是 AA（27）、AB（28）等。

### 思路

我们可以将这个问题转换为一个**26 进制数的计算**问题：

- 假设 `columnTitle` 是一个字符串，其中每个字符都是一个字母（A 到 Z）。
- 假设 `columnTitle` 中的字符从左到右依次表示：`columnTitle[0]` 为最高位，`columnTitle[1]` 为次高位，以此类推。
- 对于字符 `A` 到 `Z`，它们的值分别是 `1` 到 `26`。我们可以将字符串中的每个字符映射为一个数字，并根据其在字符串中的位置计算总的列序号。

### 具体的计算方法：
1. 每一位的字母代表了 `26` 的某个幂次方。例如，`AB` 就可以看作是 `A` 代表 `26^1` 的倍数，`B` 代表 `26^0` 的倍数。
2. 通过遍历字符串，我们可以从左到右逐位累加计算最终的列号。

### 计算步骤：
1. 从左到右遍历 `columnTitle` 中的每个字符。
2. 对于每个字符，将其转化为对应的数字（A -> 1, B -> 2, ..., Z -> 26）。
3. 累加时，将当前结果乘以 `26`，并加上当前字符的数字。

### 代码实现：

```java
public class ExcelColumnNumber {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        
        for (int i = 0; i < columnTitle.length(); i++) {
            // 获取当前字符的数字值（'A' -> 1, 'B' -> 2, ..., 'Z' -> 26）
            int currentValue = columnTitle.charAt(i) - 'A' + 1;
            
            // 更新结果：乘以 26 是因为每一位的权重是 26 的幂次
            result = result * 26 + currentValue;
        }
        
        return result;
    }

    public static void main(String[] args) {
        ExcelColumnNumber solution = new ExcelColumnNumber();
        
        // 测试示例
        System.out.println(solution.titleToNumber("A"));  // 输出: 1
        System.out.println(solution.titleToNumber("AB")); // 输出: 28
        System.out.println(solution.titleToNumber("ZY")); // 输出: 701
    }
}
```

### 代码解释：
1. **输入**：`columnTitle` 是一个表示 Excel 列名的字符串，长度在 1 到 7 之间。
2. **初始化 `result` 为 0**，用来保存最终的列序号。
3. **遍历字符串 `columnTitle`**：
    - 对于每个字符，我们计算该字符的值：`currentValue = columnTitle.charAt(i) - 'A' + 1`。例如，'A' 对应 1，'B' 对应 2，依此类推。
4. **更新结果**：
    - 对于每个字符，我们将当前的 `result` 乘以 26（因为每个字符相当于一个 26 进制的数位），然后加上当前字符的值。
5. **返回 `result`**，最终得到的就是对应的列序号。

### 时间复杂度：
- **时间复杂度**：`O(n)`，其中 `n` 是 `columnTitle` 的长度。我们需要遍历整个字符串一次。
- **空间复杂度**：`O(1)`，我们只使用了常数空间来保存中间结果。

### 测试用例：

1. **输入**：`"A"`
    - 输出：`1`
    - 解释：A 对应的列序号是 1。

2. **输入**：`"AB"`
    - 输出：`28`
    - 解释：AB 对应的列序号是 28。

3. **输入**：`"ZY"`
    - 输出：`701`
    - 解释：ZY 对应的列序号是 701。

4. **输入**：`"FXSHRXW"`
    - 输出：`2147483647`
    - 解释：FXSHRXW 对应的列序号是 2147483647。

### 总结：
- 这个问题其实就是一个 **26 进制转换** 的问题，通过逐位累加的方式计算出最终的列号。
- 代码实现简单，时间复杂度和空间复杂度都非常高效。