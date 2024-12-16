<p>给你一个 <strong>非空</strong> 整数数组 <code>nums</code> ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。</p>

<p>你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。</p>

<div class="original__bRMd"> 
 <div> 
  <p>&nbsp;</p> 
 </div>
</div>

<p><strong class="example">示例 1 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,1]
<strong>输出：</strong>1
</pre>

<p><strong class="example">示例 2 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [4,1,2,1,2]
<strong>输出：</strong>4
</pre>

<p><strong class="example">示例 3 ：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
 <li><code>-3 * 10<sup>4</sup> &lt;= nums[i] &lt;= 3 * 10<sup>4</sup></code></li> 
 <li>除了某个元素只出现一次以外，其余每个元素均出现两次。</li> 
</ul>

<div><li>👍 3236</li><li>👎 0</li></div>


这是一个经典的 LeetCode 问题，题号是 **136. Single Number**。我们需要找出只出现一次的元素，要求时间复杂度为 O(n)，且空间复杂度为 O(1)。以下是详细的分析和解决方案。

---

### 思路分析

#### 1. 位操作的巧妙应用：异或运算（XOR）
- **性质**：
    1. 任意数与 0 异或为其本身：`a ^ 0 = a`
    2. 任意数与自身异或为 0：`a ^ a = 0`
    3. 异或运算满足交换律和结合律：`a ^ b ^ a = b`

- **核心思想**：
    - 将数组中的所有元素进行异或操作：
        - 重复的元素会因为 `a ^ a = 0` 而被消去。
        - 剩下的就是那个只出现一次的元素。

#### 2. 算法流程
1. 初始化变量 `result = 0`。
2. 遍历数组中的每个元素，将其与 `result` 进行异或操作：`result ^= num`。
3. 遍历结束后，`result` 即为只出现一次的元素。

#### 3. 时间和空间复杂度
- **时间复杂度**：O(n) —— 遍历一次数组。
- **空间复杂度**：O(1) —— 仅使用了常量额外空间。

---

### Java 实现

以下是基于上述思路的 Java 实现，带有详细注释：

```java
public class SingleNumber {
    public int singleNumber(int[] nums) {
        // 初始化结果为 0
        int result = 0;
        
        // 遍历数组中的每个元素
        for (int num : nums) {
            // 利用异或操作消除成对出现的数字
            result ^= num;
        }
        
        // 返回只出现一次的元素
        return result;
    }

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();

        // 示例 1
        int[] nums1 = {2, 2, 1};
        System.out.println("只出现一次的数字是: " + sn.singleNumber(nums1)); // 输出: 1

        // 示例 2
        int[] nums2 = {4, 1, 2, 1, 2};
        System.out.println("只出现一次的数字是: " + sn.singleNumber(nums2)); // 输出: 4

        // 示例 3
        int[] nums3 = {1};
        System.out.println("只出现一次的数字是: " + sn.singleNumber(nums3)); // 输出: 1
    }
}
```

---

### 步骤详解（配图）

#### 输入：`nums = [4, 1, 2, 1, 2]`

我们使用异或操作逐步更新 `result` 的值。

| 索引 | 当前数字 | 异或操作            | 中间结果 (`result`) |
|------|----------|---------------------|---------------------|
| 0    | 4        | `0 ^ 4`            | 4                   |
| 1    | 1        | `4 ^ 1`            | 5                   |
| 2    | 2        | `5 ^ 2`            | 7                   |
| 3    | 1        | `7 ^ 1`            | 6                   |
| 4    | 2        | `6 ^ 2`            | 4                   |

最终结果：`4`。

---

#### 可视化过程
- 初始：`result = 0`
- 每一步异或后的状态：

  ```
  0 ⊕ 4 = 4
  4 ⊕ 1 = 5
  5 ⊕ 2 = 7
  7 ⊕ 1 = 6
  6 ⊕ 2 = 4
  ```

  **最终结果为 4。**

---

### 总结

1. **优点**：
    - 算法简单且高效，满足题目要求的 O(n) 时间复杂度和 O(1) 空间复杂度。
2. **适用场景**：
    - 本方法适用于数组中每个元素恰好出现两次或一次的情况。

你可以直接复制以上代码运行，并根据输入验证结果！如果需要其他解法或扩展思路，欢迎继续讨论！