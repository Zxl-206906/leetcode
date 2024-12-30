<p>给定一个正整数 <code>n</code>，编写一个函数，获取一个正整数的二进制形式并返回其二进制表达式中 <span data-keyword="set-bit">设置位</span> 的个数（也被称为<a href="https://baike.baidu.com/item/%E6%B1%89%E6%98%8E%E9%87%8D%E9%87%8F" target="_blank">汉明重量</a>）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 11
<strong>输出：</strong>3
<strong>解释：</strong>输入的二进制串 <span><code><strong>1011</strong>&nbsp;中，共有 3 个设置位。</code></span>
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 128
<strong>输出：</strong>1
<strong>解释：</strong>输入的二进制串 <strong>10000000</strong>&nbsp;中，共有 1 个设置位。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 2147483645
<strong>输出：</strong>30
<strong>解释：</strong>输入的二进制串 <strong>1111111111111111111111111111101</strong> 中，共有 30 个设置位。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li> 
</ul>

<ul> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶</strong>：</p>

<ul> 
 <li>如果多次调用这个函数，你将如何优化你的算法？</li> 
</ul>

<div><li>👍 663</li><li>👎 0</li></div>

这个问题的目标是计算一个正整数的二进制表示中 "1" 的个数，也就是计算它的**汉明重量**。我们可以通过不同的方式来实现，常见的有以下几种方法：

### 方法 1：直接使用内置函数
如果语言本身提供了内置的计算二进制1的个数的函数，直接使用会非常高效。例如，在 Java 中，我们可以使用 `Integer.bitCount()` 来实现。

### 方法 2：逐位检查
另一种常见的方式是逐位检查二进制表示中的每一位。如果当前位是 `1`，则计数加一，然后右移一位直到 `n` 为 0。

### 方法 3：通过位运算优化
可以使用 **Brian Kernighan 算法**来优化，减少不必要的操作。每次我们将 `n` 中的最右边的一个 `1` 清零，然后计算剩下的部分。这种方法会比逐位检查更高效，特别是当数字的二进制表示中 `1` 的个数较少时。

### 代码实现：

```java
package leetcode.editor.cn;

public class T191_NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new T191_NumberOf1Bits().new Solution();
        
        // Test cases
        System.out.println(solution.hammingWeight(11)); // Expected: 3 (binary: 1011)
        System.out.println(solution.hammingWeight(128)); // Expected: 1 (binary: 10000000)
        System.out.println(solution.hammingWeight(2147483645)); // Expected: 30 (binary: 1111111111111111111111111111101)
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // Using the built-in function Integer.bitCount()
        public int hammingWeight(int n) {
            return Integer.bitCount(n);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
```

### 解释：

1. **`Integer.bitCount(n)`**：这个方法直接返回 `n` 的二进制表示中 `1` 的个数。对于这种简单的应用，直接使用内置方法会非常高效。

### 方法 2：逐位检查

如果不使用内置函数，我们可以通过逐位检查的方法来实现：

```java
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            // 检查最低位是否为 1
            count += n & 1;
            // 右移一位，继续检查下一个位
            n >>= 1;
        }
        return count;
    }
}
```

### 解释：
- **`n & 1`**：通过按位与运算检查当前最低位是否为 `1`。如果是 `1`，则加一。
- **`n >>= 1`**：将 `n` 右移一位，检查下一个二进制位。
- **时间复杂度**：每次检查一位，直到 `n` 变为 0。最坏情况下是 O(log n)，其中 `n` 是数字的大小。

### 方法 3：Brian Kernighan 算法

这种算法通过每次将最右边的 `1` 清零来优化：

```java
public class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1); // 清除最右边的 1
            count++;
        }
        return count;
    }
}
```

### 解释：
- **`n = n & (n - 1)`**：这个操作会将 `n` 中最右边的 `1` 清零。通过不断执行这个操作，直到 `n` 变为 0。
- **时间复杂度**：每次只需要操作 `n` 中一个 `1`，因此时间复杂度是 O(k)，其中 `k` 是 `n` 中 `1` 的个数（即 `n` 的汉明重量）。

### 总结：
1. **方法 1**：通过 `Integer.bitCount(n)`，最简单直接，推荐使用。
2. **方法 2**：逐位检查，适合不支持内置函数的情况，时间复杂度 O(log n)。
3. **方法 3**：使用 Brian Kernighan 算法，适合快速计算稀疏二进制的 `1`，时间复杂度 O(k)，其中 `k` 是 `1` 的个数。

### 示例：

#### 输入：
```java
n = 11  // 二进制表示: 1011
```

#### 输出：
```java
3  // 有三个 `1`
```

#### 输入：
```java
n = 128  // 二进制表示: 10000000
```

#### 输出：
```java
1  // 只有一个 `1`
```