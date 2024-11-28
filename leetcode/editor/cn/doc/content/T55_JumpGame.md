<p>给你一个非负整数数组&nbsp;<code>nums</code> ，你最初位于数组的 <strong>第一个下标</strong> 。数组中的每个元素代表你在该位置可以跳跃的最大长度。</p>

<p>判断你是否能够到达最后一个下标，如果可以，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,3,1,1,4]
<strong>输出：</strong>true
<strong>解释：</strong>可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,0,4]
<strong>输出：</strong>false
<strong>解释：</strong>无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><li>👍 2924</li><li>👎 0</li></div>


### 问题描述

给定一个非负整数数组 `nums`，每个元素代表你在该位置可以跳跃的最大长度。你最初位于数组的第一个下标。判断你是否能够到达最后一个下标。如果可以，返回 `true`；否则返回 `false`。

### **思路解析**

这道题目可以通过贪心算法来解决。

1. **贪心策略**：
    - 我们需要一个变量 `maxReach` 来记录我们目前能到达的最远下标。
    - 从数组的第一个位置开始遍历，对于每一个下标 `i`，如果 `i` 的值加上 `i` 的最大跳跃长度可以达到的最远下标大于当前记录的 `maxReach`，就更新 `maxReach`。
    - 如果在遍历过程中，`maxReach` 能够覆盖到数组的最后一个下标，说明可以到达最后一个下标，返回 `true`。
    - 如果遍历到某个位置时，`i` 已经大于 `maxReach`，说明无法继续跳跃，返回 `false`。

2. **边界条件**：
    - 如果数组的长度为 1，已经在最后一个下标，直接返回 `true`。
    - 如果数组的第一个元素是 0 且长度大于 1，则无法跳跃，返回 `false`。

---

### **Java 实现**

```text
public class JumpGame {
    
    // 判断是否能够到达最后一个下标
    public static boolean canJump(int[] nums) {
        int maxReach = 0;  // 初始化能到达的最远位置
        
        // 遍历每个下标
        for (int i = 0; i < nums.length; i++) {
            // 如果当前位置无法到达，返回 false
            if (i > maxReach) {
                return false;
            }
            // 更新最远可以到达的位置
            maxReach = Math.max(maxReach, i + nums[i]);
            
            // 如果最远可达位置已经覆盖到最后一个下标，返回 true
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }
        
        return false;  // 如果循环结束，无法到达最后一个下标
    }

    public static void main(String[] args) {
        // 测试示例
        int[] nums1 = {2, 3, 1, 1, 4};
        System.out.println(canJump(nums1));  // 输出: true
        
        int[] nums2 = {3, 2, 1, 0, 4};
        System.out.println(canJump(nums2));  // 输出: false
    }
}
```

---

### **详细步骤**

1. **初始化变量**：
    - `maxReach`：记录当前能到达的最远位置，初始为 0。

2. **遍历数组**：
    - 对于数组中的每个元素 `nums[i]`：
        - 如果当前位置 `i` 已经超过了 `maxReach`，说明当前位置无法到达，直接返回 `false`。
        - 否则，更新 `maxReach` 为 `Math.max(maxReach, i + nums[i])`，即当前位置 `i` 能跳跃的最远位置。
        - 如果 `maxReach` 已经大于或等于数组的最后一个下标，说明可以到达最后一个位置，返回 `true`。

3. **返回结果**：
    - 如果循环结束后还没有返回 `true`，说明无法到达最后一个下标，返回 `false`。

---

### **时间复杂度**

- **时间复杂度**：`O(n)`，其中 `n` 是数组的长度。我们只遍历一次数组，因此时间复杂度是线性的。
- **空间复杂度**：`O(1)`，我们只使用了常数级别的额外空间。

---

### **示例**

#### 示例 1：

输入：
```text
nums = [2, 3, 1, 1, 4]
```

- 初始时，`maxReach = 0`。
- 在位置 `0`，可以跳跃 2 步，所以 `maxReach = max(0, 0 + 2) = 2`。
- 在位置 `1`，可以跳跃 3 步，所以 `maxReach = max(2, 1 + 3) = 4`，已经可以到达最后一个位置。
- 因此返回 `true`。

输出：
```text
true
```

#### 示例 2：

输入：
```text
nums = [3, 2, 1, 0, 4]
```

- 初始时，`maxReach = 0`。
- 在位置 `0`，可以跳跃 3 步，所以 `maxReach = max(0, 0 + 3) = 3`。
- 在位置 `1`，可以跳跃 2 步，所以 `maxReach = max(3, 1 + 2) = 3`。
- 在位置 `2`，可以跳跃 1 步，所以 `maxReach = max(3, 2 + 1) = 3`。
- 在位置 `3`，只能跳跃 0 步，`maxReach = 3`，但当前位置 `i = 3` 超过了 `maxReach`，因此无法到达最后一个位置。
- 因此返回 `false`。

输出：
```text
false
```

---

### **总结**

1. **贪心算法**：通过记录当前能到达的最远位置，确保我们能够快速判断是否能到达最后一个下标。
2. **时间复杂度**：`O(n)`，适合大规模输入。
3. **空间复杂度**：`O(1)`，是一个原地解法。

该算法高效且简洁，可以处理该类跳跃问题。