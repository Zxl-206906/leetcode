<p>实现&nbsp;<a href="https://www.cplusplus.com/reference/valarray/pow/" target="_blank">pow(<em>x</em>, <em>n</em>)</a>&nbsp;，即计算 <code>x</code> 的整数&nbsp;<code>n</code> 次幂函数（即，<code>x<sup>n</sup></code><sup><span style="font-size:10.8333px"> </span></sup>）。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = 10
<strong>输出：</strong>1024.00000
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>x = 2.10000, n = 3
<strong>输出：</strong>9.26100
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>x = 2.00000, n = -2
<strong>输出：</strong>0.25000
<strong>解释：</strong>2<sup>-2</sup> = 1/2<sup>2</sup> = 1/4 = 0.25
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>-100.0 &lt; x &lt; 100.0</code></li> 
 <li><code>-2<sup>31</sup> &lt;= n &lt;= 2<sup>31</sup>-1</code></li> 
 <li><code>n</code>&nbsp;是一个整数</li> 
 <li>要么 <code>x</code> 不为零，要么 <code>n &gt; 0</code> 。</li> 
 <li><code>-10<sup>4</sup> &lt;= x<sup>n</sup> &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 1409</li><li>👎 0</li></div>



### 问题描述

给定一个浮点数 `x` 和一个整数 `n`，我们需要计算 `x` 的 `n` 次幂，即 `x^n`。如果 `n` 是负数，表示的是 `1 / x^|n|`。

---

### **思路解析**

我们可以通过 **快速幂算法** 来高效计算 `x^n`，即通过将问题分解成更小的子问题并递归地求解。这个方法能够将时间复杂度从 `O(n)` 降到 `O(log n)`，因为每次我们通过将 `n` 进行二分来减少计算量。

#### **快速幂算法**：

1. **偶数幂的处理**：
   如果 `n` 是偶数，则：
   \[
   x^n = (x^{n/2})^2
   \]
   也就是说，我们只需要计算 `x^(n/2)`，然后将结果平方。

2. **奇数幂的处理**：
   如果 `n` 是奇数，则：
   \[
   x^n = x \times x^{n-1}
   \]
   也就是说，我们可以将问题转化为计算 `x^(n-1)`，然后再乘上 `x`。

3. **负数幂的处理**：
   对于负数幂 `n`，我们可以利用负幂的定义：
   \[
   x^{-n} = \frac{1}{x^n}
   \]
   所以如果 `n` 是负数，最后返回 `1 / result` 即可。

通过不断地将 `n` 进行二分并递归计算，最终能够在 `O(log n)` 时间内得到结果。

#### **递归实现和非递归实现**：

- 递归实现：每次递归计算 `x^(n//2)`，根据 `n` 是偶数还是奇数来决定是否平方。
- 非递归实现：通过循环的方式来实现二分的过程。

---

### **Java 实现**

```text
public class PowerFunction {
    
    // 计算 x 的 n 次幂
    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;  // 任何数的零次幂为 1
        }
        
        // 处理负幂的情况
        long N = n;  // 使 n 成为 long 类型，避免 n = Integer.MIN_VALUE 时溢出
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        while (N > 0) {
            if (N % 2 == 1) {
                result *= x;  // 如果是奇数次幂，则乘上当前的 x
            }
            x *= x;  // 将 x 的次方每次翻倍
            N /= 2;  // 每次将 n 减半
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 10));  // 输出: 1024.00000
        System.out.println(myPow(2.10000, 3));   // 输出: 9.26100
        System.out.println(myPow(2.00000, -2));  // 输出: 0.25000
    }
}
```

---

### **详细步骤**

1. **输入处理**：
    - 如果 `n == 0`，任何数的零次幂都等于 1。
    - 对于负幂的情况，我们将 `x` 变为 `1/x`，并将 `n` 转为正数（因为负数幂等于其正数幂的倒数）。

2. **二分法计算**：
    - 初始化 `result` 为 1。
    - 当 `n > 0` 时，检查 `n` 是否为奇数。如果是奇数，则将当前的 `x` 乘到 `result` 上。
    - 每次将 `x` 自身平方（这就是“快速幂”的精髓），并将 `n` 减半。
    - 最终得到的 `result` 即为 `x^n`。

3. **时间复杂度**：
    - 每次将 `n` 减半，因此时间复杂度为 `O(log n)`。

4. **空间复杂度**：
    - 由于使用的是循环的方式来实现，没有递归栈，因此空间复杂度是 `O(1)`。

---

### **示例**

#### 示例 1：

输入：
```text
x = 2.00000, n = 10
```

步骤：
- 计算 `2^10`。
- 通过快速幂算法，我们将计算过程分解为：
  \[
  2^{10} = (2^5)^2 = (2 \times 2^4)^2
  \]
- 最终得到 `1024.00000`。

输出：
```text
1024.00000
```

#### 示例 2：

输入：
```text
x = 2.10000, n = 3
```

步骤：
- 计算 `2.1^3`，通过快速幂分解。
- 最终得到 `9.26100`。

输出：
```text
9.26100
```

#### 示例 3：

输入：
```text
x = 2.00000, n = -2
```

步骤：
- 计算 `2^-2`，即 `1 / (2^2)`，最终得到 `0.25000`。

输出：
```text
0.25000
```

---

### **总结**

1. **快速幂算法**：通过将 `n` 的计算分解为更小的子问题（每次将 `n` 减半），从而大大降低计算复杂度。
2. **时间复杂度**：`O(log n)`，比传统的逐次相乘方法 `O(n)` 更高效。
3. **空间复杂度**：`O(1)`，没有额外的递归栈或者数据结构。

这种方法能高效地解决问题，尤其适用于大规模数据。