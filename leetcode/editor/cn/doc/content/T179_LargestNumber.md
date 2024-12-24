<p>给定一组非负整数 <code>nums</code>，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。</p>

<p><strong>注意：</strong>输出结果可能非常大，所以你需要返回一个字符串而不是整数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入<code>：</code></strong><span><code>nums = [10,2]</code></span>
<strong>输出：</strong><span><code>"210"</code></span></pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入<code>：</code></strong><span><code>nums = [3,30,34,5,9]</code></span>
<strong>输出：</strong><span><code>"9534330"</code></span>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li> 
</ul>

<div><li>👍 1312</li><li>👎 0</li></div>

这个问题的核心是如何通过重新排列数字，使得它们组合起来形成一个尽可能大的整数。需要注意的是，直接的数字排序无法达到期望的结果。我们需要自定义比较规则来判断两个数字应该如何排列。

### 解决思路：
1. **数字拼接比较**：对每对数字 `x` 和 `y`，我们将它们转换为字符串，并比较两个组合的结果 `x + y` 和 `y + x`。如果 `x + y` 比 `y + x` 大，那么 `x` 应该排在 `y` 前面。
2. **自定义排序**：根据上面的比较规则，排序所有数字。
3. **处理特殊情况**：如果排序后第一个数字是 `0`，那么所有数字都是 `0`，应该直接返回 "0"。

### 步骤：
1. 将每个数字转为字符串，方便拼接和比较。
2. 使用自定义的排序规则，来决定数字的顺序。
3. 将排序后的数字组合成最终结果。
4. 处理特殊情况，比如所有数字都是 `0` 的情况。

### 代码实现：

```python
from functools import cmp_to_key

# 自定义比较函数
def compare(x, y):
    # 比较 x + y 和 y + x 拼接结果
    if x + y > y + x:
        return -1  # x 排在 y 前
    elif x + y < y + x:
        return 1   # y 排在 x 前
    else:
        return 0   # 相等，不需要改变顺序

def largestNumber(nums):
    # 将数字转换为字符串
    nums = list(map(str, nums))
    # 排序，使用自定义的比较函数
    nums.sort(key=cmp_to_key(compare))
    # 拼接排序后的数字
    result = ''.join(nums)
    # 处理特殊情况：如果结果是以 "0" 开头，说明所有数字都是 0
    if result[0] == '0':
        return '0'
    return result

# 示例
print(largestNumber([10, 2]))         # 输出 "210"
print(largestNumber([3, 30, 34, 5, 9]))  # 输出 "9534330"
```

### 解释：
1. **自定义比较函数 `compare`**：这个函数根据拼接后的字符串大小来决定两个数字的顺序。如果 `x + y` 大于 `y + x`，说明 `x` 应该排在前面，反之则排后。
2. **排序 `nums.sort(key=cmp_to_key(compare))`**：这里我们将数字转换成字符串后，用自定义的比较函数进行排序。
3. **拼接和返回**：排序后的数字拼接成字符串后，返回最终结果。如果拼接后的字符串以 `0` 开头（例如 `["0", "0", "0"]`），说明所有数字都是零，直接返回 `"0"`。

### 示例解析：
- **示例 1**：`nums = [10, 2]`
    - 拼接比较：`2 + 10 = "210"`, `10 + 2 = "102"`，所以 `2` 排在 `10` 前面。
    - 输出 `"210"`。

- **示例 2**：`nums = [3, 30, 34, 5, 9]`
    - 拼接比较：`9 + 5 = "95"`, `5 + 9 = "59"`，所以 `9` 排在 `5` 前面。
    - 类似地，排序后的顺序是 `[9, 5, 34, 3, 30]`。
    - 拼接后输出 `"9534330"`。

### 时间复杂度：
- 排序的时间复杂度是 `O(n log n)`，其中 `n` 是数组的长度。
- 每次比较涉及到字符串拼接，拼接的最大长度为数字的位数，在最坏情况下，时间复杂度为 `O(k)`，其中 `k` 是数字的最大位数。
- 综合时间复杂度为 `O(n log n * k)`。

### 空间复杂度：
- 空间复杂度主要由排序和字符串存储引起，空间复杂度为 `O(n * k)`，其中 `n` 是数字的个数，`k` 是数字的最大位数。

好的，下面是一个完整的 Java 解决方案，基于题目描述的要求，通过自定义比较规则来排列整数，从而组成最大的数字。

### Java 代码实现

```java
public class T179_LargestNumber {
    public static void main(String[] args) {
        Solution solution = new T179_LargestNumber().new Solution();
        
        // 测试用例 1
        int[] nums1 = {10, 2};
        System.out.println(solution.largestNumber(nums1));  // 输出 "210"
        
        // 测试用例 2
        int[] nums2 = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums2));  // 输出 "9534330"
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestNumber(int[] nums) {
            // 将整数数组转为字符串数组
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }
            
            // 使用自定义比较器进行排序
            Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));
            
            // 如果排序后的第一个元素是 "0"，则所有元素都是零，直接返回 "0"
            if (strs[0].equals("0")) {
                return "0";
            }
            
            // 拼接排序后的结果
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}
```

### 代码解析：
1. **将整数转换为字符串**：
    - 首先，我们将整数数组 `nums` 转换为字符串数组 `strs`，以便后续进行拼接和比较。

2. **自定义排序规则**：
    - 我们使用 `Arrays.sort` 来对字符串数组进行排序。排序的关键在于比较两个字符串 `a` 和 `b` 的拼接结果：`b + a` 和 `a + b`。这样做是因为我们希望通过比较两个字符串拼接后的结果来决定哪个字符串应该排在前面。
    - 具体地，我们用 `(b + a).compareTo(a + b)` 作为排序的比较器。这个比较器确保大的拼接结果排在前面。

3. **处理特殊情况**：
    - 如果排序后，第一个元素是 `"0"`，意味着数组中的所有数字都是零。在这种情况下，应该返回 `"0"`，而不是多个 `"0"` 拼接。

4. **拼接最终结果**：
    - 使用 `StringBuilder` 将排序后的字符串数组拼接成最终结果。

### 测试：
```java
// 测试用例 1
int[] nums1 = {10, 2};
System.out.println(solution.largestNumber(nums1));  // 输出 "210"

// 测试用例 2
int[] nums2 = {3, 30, 34, 5, 9};
System.out.println(solution.largestNumber(nums2));  // 输出 "9534330"
```

### 时间复杂度：
- `Arrays.sort` 的时间复杂度是 `O(n log n)`，其中 `n` 是数组的长度。
- 在排序过程中，比较操作的复杂度是 `O(k)`，其中 `k` 是数字的最大位数。
- 所以整体的时间复杂度是 `O(n log n * k)`。

### 空间复杂度：
- 由于我们需要存储字符串数组和最终的拼接结果，所以空间复杂度是 `O(n * k)`，其中 `n` 是数组的长度，`k` 是数字的最大位数。

这个解法对于题目给定的约束（`nums.length <= 100` 和 `nums[i] <= 10^9`）是有效的。



