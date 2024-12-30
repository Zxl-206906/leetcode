<p>颠倒给定的 32 位无符号整数的二进制位。</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。</li> 
 <li>在 Java 中，编译器使用<a href="https://baike.baidu.com/item/二进制补码/5295284" target="_blank">二进制补码</a>记法来表示有符号整数。因此，在 <strong>示例 2</strong>&nbsp;中，输入表示有符号整数 <code>-3</code>，输出表示有符号整数 <code>-1073741825</code>。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 00000010100101000001111010011100
<strong>输出：</strong>964176192 (00111001011110000010100101000000)
<strong>解释：</strong>输入的二进制串 <strong>00000010100101000001111010011100 </strong>表示无符号整数<strong> 43261596</strong><strong>，
    </strong> 因此返回 964176192，其二进制表示形式为 <strong>00111001011110000010100101000000</strong>。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 11111111111111111111111111111101
<strong>输出：</strong>3221225471 (10111111111111111111111111111111)
<strong>解释：</strong>输入的二进制串 <strong>11111111111111111111111111111101</strong> 表示无符号整数 4294967293，
   &nbsp; 因此返回 3221225471 其二进制表示形式为 <strong>10111111111111111111111111111111 。</strong></pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>输入是一个长度为 <code>32</code> 的二进制字符串</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶</strong>: 如果多次调用这个函数，你将如何优化你的算法？</p>

<div><li>👍 718</li><li>👎 0</li></div>


这道题要求我们颠倒一个32位无符号整数的二进制位。我们可以通过位操作来实现这一目标。

### 思路：

1. **逐位反转**：我们可以通过逐位将输入的数字从低位到高位依次反转。每次获取最低位，将它放到结果的最高位，然后左移一位，继续处理下一个最低位，直到所有位都被反转。
2. **位操作**：
    - **右移** (`>>`)：用于将 `n` 右移一位，从最低位逐个提取。
    - **左移** (`<<`)：用于将反转后的结果向左移动一位，为下一位腾出空间。
    - **按位或** (`|`)：用于将当前位设置到结果的正确位置。

3. **时间复杂度**：每次只处理一个二进制位，所以时间复杂度是 O(32)，即 O(1)，因为32是常数。

### 解法：

我们可以使用一个简单的 `for` 循环来遍历所有32位，每次提取最低位并将它添加到结果的合适位置。

### Java实现：

```java
package leetcode.editor.cn;

public class T190_ReverseBits {
    public static void main(String[] args) {
        Solution solution = new T190_ReverseBits().new Solution();
        
        // Test cases
        int n1 = 43261596; // 00000010100101000001111010011100
        System.out.println(solution.reverseBits(n1)); // Expected: 964176192
        
        int n2 = 4294967293; // 11111111111111111111111111111101
        System.out.println(solution.reverseBits(n2)); // Expected: 3221225471
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        // you need treat n as an unsigned value
        public int reverseBits(int n) {
            int result = 0;
            // Loop through each of the 32 bits
            for (int i = 0; i < 32; i++) {
                // Shift result to the left to make room for the new bit
                result <<= 1;
                // Add the current bit of n to the result (either 0 or 1)
                result |= (n & 1);
                // Shift n to the right to process the next bit
                n >>= 1;
            }
            return result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
```

### 解释：

1. **`result <<= 1;`**：每次将 `result` 左移一位，为新的最低位腾出空间。
2. **`result |= (n & 1);`**：通过 `n & 1` 获取 `n` 当前最低位（`0` 或 `1`），然后使用按位或操作将其添加到 `result` 中。
3. **`n >>= 1;`**：将 `n` 右移一位，以便下一次循环时获取新的最低位。

### 流程：

- 输入：`n = 43261596`，二进制表示为 `00000010100101000001111010011100`
- 输出：`964176192`，二进制表示为 `00111001011110000010100101000000`

### 进阶优化：

对于多次调用该函数的情况，可以利用位运算的技巧来进一步优化：
- 如果函数调用次数非常多，可以考虑使用查找表 (Lookup Table)，即预先计算每个字节反转的结果，从而避免每次都进行32次循环。
- 另外可以使用并行处理的方式，分段进行反转，尤其在多核处理器上效果更为显著，但通常这种优化对于题目中的32位数字来说效果不大。

