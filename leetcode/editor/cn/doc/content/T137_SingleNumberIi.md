<p>给你一个整数数组&nbsp;<code>nums</code> ，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次 。</strong>请你找出并返回那个只出现了一次的元素。</p>

<p>你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,3,2]
<strong>输出：</strong>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,0,1,0,1,99]
<strong>输出：</strong>99
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li> 
 <li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li> 
 <li><code>nums</code> 中，除某个元素仅出现 <strong>一次</strong> 外，其余每个元素都恰出现 <strong>三次</strong></li> 
</ul>

<div><li>👍 1266</li><li>👎 0</li></div>

---

### 思路分析

#### 1. 位运算的核心思想
我们利用**位运算**的特性，统计每一位上 1 的个数。如果一个数字出现了 3 次，那么其二进制中每一位上 1 的个数也应该是 3 的倍数。对于只出现一次的数字，它的每一位会破坏这个倍数关系。

#### 2. 解法关键
1. 遍历数组中所有数字，并统计每个位上 1 的出现次数。
2. 对每个位的统计结果取模 3，可以得到只出现一次的数字在对应位上的值。

#### 3. 状态变量优化
为了满足空间复杂度 \(O(1)\) 的要求，我们不能直接用一个数组存储每个位的统计结果，而是用两位状态变量 `ones` 和 `twos` 表示：
- `ones` 表示当前位出现 1 次的结果；
- `twos` 表示当前位出现 2 次的结果。

使用以下规则更新 `ones` 和 `twos`：
- 当某个位出现 3 次时，会被清零，即 `(ones & twos)` 的位会被移除。

更新公式：
1. `ones = (ones ^ num) & ~twos`
2. `twos = (twos ^ num) & ~ones`

---

### 算法流程

1. 初始化两个变量 `ones` 和 `twos` 为 0。
2. 遍历数组中的每个数字 `num`：
    - 根据更新公式，更新 `ones` 和 `twos` 的状态。
3. 遍历结束后，`ones` 即为只出现一次的数字。

---

### 时间和空间复杂度
- **时间复杂度**：\(O(n)\)，遍历数组一次。
- **空间复杂度**：\(O(1)\)，只使用了常数级别的额外变量。

---

### Java 实现

以下是基于上述思路的 Java 实现，带有详细注释：

```java
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        // ones 和 twos 用于记录当前位出现 1 次和 2 次的状态
        int ones = 0, twos = 0;

        for (int num : nums) {
            // 更新 ones 和 twos
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        // 最终 ones 保存了只出现一次的数字
        return ones;
    }

    public static void main(String[] args) {
        SingleNumberII sn = new SingleNumberII();

        // 示例 1
        int[] nums1 = {2, 2, 3, 2};
        System.out.println("只出现一次的数字是: " + sn.singleNumber(nums1)); // 输出: 3

        // 示例 2
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("只出现一次的数字是: " + sn.singleNumber(nums2)); // 输出: 99
    }
}
```

---

### 执行流程详解

#### 示例输入：`nums = [2, 2, 3, 2]`

**初始状态**：
- `ones = 0`
- `twos = 0`

| 元素 | 更新公式                             | ones  | twos  |
|------|-------------------------------------|-------|-------|
| 2    | `ones = (0 ^ 2) & ~0 = 2`           | `2`   | `0`   |
| 2    | `twos = (0 ^ 2) & ~2 = 2`           | `0`   | `2`   |
| 2    | `ones = (0 ^ 2) & ~2 = 0`           | `0`   | `0`   |
| 3    | `ones = (0 ^ 3) & ~0 = 3`           | `3`   | `0`   |

最终结果：`ones = 3`。

---

#### 示例输入：`nums = [0, 1, 0, 1, 0, 1, 99]`

**初始状态**：
- `ones = 0`
- `twos = 0`

| 元素 | 更新公式                             | ones  | twos  |
|------|-------------------------------------|-------|-------|
| 0    | `ones = (0 ^ 0) & ~0 = 0`           | `0`   | `0`   |
| 1    | `ones = (0 ^ 1) & ~0 = 1`           | `1`   | `0`   |
| 0    | `twos = (0 ^ 0) & ~1 = 0`           | `1`   | `0`   |
| 1    | `ones = (1 ^ 1) & ~0 = 0`           | `0`   | `1`   |
| 0    | `twos = (1 ^ 0) & ~0 = 1`           | `0`   | `1`   |
| 1    | `ones = (0 ^ 1) & ~1 = 0`           | `0`   | `0`   |
| 99   | `ones = (0 ^ 99) & ~0 = 99`         | `99`  | `0`   |

最终结果：`ones = 99`。

---

### 总结

1. **关键点**：通过位运算统计每一位的出现次数，利用状态变量 `ones` 和 `twos` 实现状态更新。
2. **优点**：满足线性时间复杂度和常数空间复杂度要求，算法简洁高效。
3. **扩展**：如果问题改为「其他元素出现 \( k \) 次」，可以基于类似思路进行扩展。