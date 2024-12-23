<p>给定两个整数，分别表示分数的分子&nbsp;<code>numerator</code> 和分母 <code>denominator</code>，以 <strong>字符串形式返回小数</strong> 。</p>

<p>如果小数部分为循环小数，则将循环的部分括在括号内。</p>

<p>如果存在多个答案，只需返回 <strong>任意一个</strong> 。</p>

<p>对于所有给定的输入，<strong>保证</strong> 答案字符串的长度小于 <code>10<sup>4</sup></code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numerator = 1, denominator = 2
<strong>输出：</strong>"0.5"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numerator = 2, denominator = 1
<strong>输出：</strong>"2"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>numerator = 4, denominator = 333
<strong>输出：</strong>"0.(012)"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>-2<sup>31</sup> &lt;=&nbsp;numerator, denominator &lt;= 2<sup>31</sup> - 1</code></li> 
 <li><code>denominator != 0</code></li> 
</ul>

<div><li>👍 503</li><li>👎 0</li></div>

这个问题要求我们将一个分数（由两个整数 `numerator` 和 `denominator` 表示）转换为一个字符串格式的小数。对于循环小数部分，我们需要将其括起来。

### 思路

我们可以按以下步骤来解决这个问题：

1. **判断符号**：
    - 如果 `numerator` 和 `denominator` 的符号不同，结果应该是负数。否则是正数。

2. **整数部分**：
    - 使用整数除法计算分数的整数部分。

3. **小数部分**：
    - 使用模拟长除法的方法来计算小数部分。
    - 当你遇到相同的余数时，说明从该位置开始小数部分会循环，因此需要标记并将循环部分括起来。

4. **循环检测**：
    - 使用一个哈希表（`map`）来记录每次出现的余数及其对应的小数位置。如果同一个余数再次出现，说明从该位置开始小数部分是循环的。

### 解决方案

我们可以通过模拟长除法来解决这个问题。下面是代码实现：

```java
import java.util.HashMap;

public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        // 如果分子分母为0，直接返回 "0"
        if(denominator == 0 || numerator == 0) {
            return "0";
        }

        // 结果是否为负数
        StringBuilder result = new StringBuilder();
        
        // 确定符号
        if ((numerator < 0) ^ (denominator < 0)) {
            result.append("-");
        }

        // 转换为长整型，避免溢出问题
        long num = Math.abs((long) numerator);
        long denom = Math.abs((long) denominator);

        // 计算整数部分
        result.append(num / denom);
        
        // 计算小数部分
        long remainder = num % denom;
        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");

        // 记录余数及其对应的小数位置
        HashMap<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            // 如果当前余数已出现过，则说明从此位置开始小数部分循环
            if (map.containsKey(remainder)) {
                int index = map.get(remainder);
                result.insert(index, "(");
                result.append(")");
                return result.toString();
            }

            // 记录当前余数的位置
            map.put(remainder, result.length());

            // 进行长除法
            remainder *= 10;
            result.append(remainder / denom);
            remainder %= denom;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        FractionToDecimal solution = new FractionToDecimal();
        
        // 测试示例
        System.out.println(solution.fractionToDecimal(1, 2)); // 输出: "0.5"
        System.out.println(solution.fractionToDecimal(2, 1)); // 输出: "2"
        System.out.println(solution.fractionToDecimal(4, 333)); // 输出: "0.(012)"
    }
}
```

### 代码解释

1. **符号判断**：
    - 使用异或 (`^`) 判断 `numerator` 和 `denominator` 的符号。如果符号不同，则结果为负数。

2. **整数部分**：
    - 通过 `num / denom` 计算整数部分并直接添加到结果中。

3. **小数部分**：
    - 使用余数模拟长除法。每次将余数乘以 10，并计算新的商。
    - 使用一个 `map` 来记录每个余数及其对应的位置。这样可以在检测到循环时插入括号。

4. **循环检测**：
    - 如果余数重复出现，说明从这个位置开始，后面的数字会循环出现。

### 时间复杂度

- 计算整数部分是常数时间 O(1)。
- 计算小数部分的时间复杂度是 O(N)，其中 N 是小数部分的长度。由于小数部分可能会在余数重复时开始循环，所以最多会遇到最多 `denominator` 个不同的余数。

### 测试示例

- 输入：`fractionToDecimal(1, 2)`
    - 输出：`"0.5"`

- 输入：`fractionToDecimal(2, 1)`
    - 输出：`"2"`

- 输入：`fractionToDecimal(4, 333)`
    - 输出：`"0.(012)"`

这个解法可以高效处理大部分情况，并且能够正确处理循环小数的括号显示。