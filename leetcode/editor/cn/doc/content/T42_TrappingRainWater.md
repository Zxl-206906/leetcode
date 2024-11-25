<p>给定&nbsp;<code>n</code> 个非负整数表示每个宽度为 <code>1</code> 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rainwatertrap.png" style="height: 161px; width: 412px;" /></p>

<pre>
<strong>输入：</strong>height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>输出：</strong>6
<strong>解释：</strong>上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>height = [4,2,0,3,2,5]
<strong>输出：</strong>9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == height.length</code></li> 
 <li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li> 
 <li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><li>👍 5397</li><li>👎 0</li></div>


### **问题解析**

#### **题目要求**
- 给定一个数组 `height`，每个元素代表柱子的高度，宽度固定为 `1`。
- 计算柱子之间可以接住的雨水总量。

#### **解题思路**
接雨水问题可以通过以下几个方法解决：
1. **按列计算（暴力法）**：
    - 逐列计算每个位置可以容纳的水量。
    - 计算当前列左边和右边的最高柱子，取其最小值减去当前高度。
    - 时间复杂度为 \(O(n^2)\)，不满足效率要求。

2. **动态编程（两次遍历）**：
    - 预先计算每一列左边的最高柱子和右边的最高柱子。
    - 根据两次结果，计算每列的积水量。
    - 时间复杂度为 \(O(n)\)，空间复杂度为 \(O(n)\)。

3. **双指针法（优化空间）**：
    - 使用两个指针从两端向中间遍历。
    - 通过维护左边和右边的最高高度，计算雨水量。
    - 时间复杂度为 \(O(n)\)，空间复杂度为 \(O(1)\)。
    - **本题推荐使用此方法**。

---

### **解决步骤**

#### **双指针法**
1. **初始化双指针和变量：**
    - 左指针 `left = 0`，右指针 `right = n - 1`。
    - 记录左右的最大高度：`leftMax` 和 `rightMax`。
    - 结果变量 `water = 0`。

2. **从两端向中间遍历：**
    - 如果 `height[left] < height[right]`：
        - 比较 `height[left]` 和 `leftMax`：
            - 若 `height[left] < leftMax`，当前位置可以接水：`water += leftMax - height[left]`。
            - 否则，更新 `leftMax = height[left]`。
        - 左指针右移。
    - 否则：
        - 比较 `height[right]` 和 `rightMax`：
            - 若 `height[right] < rightMax`，当前位置可以接水：`water += rightMax - height[right]`。
            - 否则，更新 `rightMax = height[right]`。
        - 右指针左移。

3. **结束遍历：**
    - 当左右指针相遇时，所有的雨水量计算完成。

---

### **Java代码实现**

```java
public class TrapRainWater {
    public static int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0; // 少于两个柱子无法存储水
        }

        // 初始化双指针
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        // 双指针从两端向中间遍历
        while (left < right) {
            if (height[left] < height[right]) {
                // 检查左边的高度
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // 更新左侧最高高度
                } else {
                    water += leftMax - height[left]; // 计算当前列的水量
                }
                left++; // 左指针右移
            } else {
                // 检查右边的高度
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // 更新右侧最高高度
                } else {
                    water += rightMax - height[right]; // 计算当前列的水量
                }
                right--; // 右指针左移
            }
        }

        return water; // 返回总的雨水量
    }

    public static void main(String[] args) {
        // 示例测试
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1})); // 输出：6
        System.out.println(trap(new int[]{4, 2, 0, 3, 2, 5})); // 输出：9
    }
}
```

---

### **代码执行流程**

#### 示例 1：`height = [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]`

1. **初始化**：
    - `left = 0, right = 11, leftMax = 0, rightMax = 0, water = 0`。

2. **迭代过程**：
    - `left = 0, right = 11`：
        - `height[left] = 0 < height[right] = 1`：
            - 更新 `leftMax = 0`，水量不变。
            - `left++`。

    - `left = 1, right = 11`：
        - `height[left] = 1 < height[right] = 1`：
            - 更新 `leftMax = 1`，水量不变。
            - `left++`。

    - `left = 2, right = 11`：
        - `height[left] = 0 < height[right] = 1`：
            - `water += leftMax - height[left] = 1`。
            - `left++`。

    - **继续迭代**，重复以上逻辑，最终累计 `water = 6`。

---

### **时间复杂度分析**
- 双指针遍历整个数组一次，时间复杂度为 **\(O(n)\)**。

### **空间复杂度分析**
- 只使用了常数变量 `left`, `right`, `leftMax`, `rightMax`, `water`，空间复杂度为 **\(O(1)\)**。

---

### **总结**
- 采用双指针法解决接雨水问题，时间复杂度 \(O(n)\)、空间复杂度 \(O(1)\)。
- 思路清晰、代码简洁，非常适合大规模数据输入的场景。