<p>给定一个非负索引 <code>rowIndex</code>，返回「杨辉三角」的第 <code>rowIndex</code><em>&nbsp;</em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 3
<strong>输出:</strong> [1,3,3,1]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 0
<strong>输出:</strong> [1]
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> rowIndex = 1
<strong>输出:</strong> [1,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>0 &lt;= rowIndex &lt;= 33</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<p>你可以优化你的算法到 <code><em>O</em>(<i>rowIndex</i>)</code> 空间复杂度吗？</p>

<div><li>👍 558</li><li>👎 0</li></div>


解决这个问题的关键在于高效地计算「杨辉三角」的指定行，而无需生成整个三角形。以下是基于组合公式的优化解法和代码示例：

---

### **解法说明**
「杨辉三角」的每个值满足组合数公式：
\[
C(rowIndex, k) = \frac{rowIndex!}{k! \cdot (rowIndex-k)!}
\]
通过使用组合数的递推关系，可以避免使用阶乘直接计算：
\[
C(rowIndex, k+1) = C(rowIndex, k) \cdot \frac{rowIndex-k}{k+1}
\]

这种递推关系可以从左到右依次计算出第 `rowIndex` 行的所有值，从而节省计算时间和空间。

---

### **Java 实现代码**
```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        long value = 1; // 用于存储组合数 C(rowIndex, 0)

        for (int k = 0; k <= rowIndex; k++) {
            row.add((int) value); // 添加当前值到结果中
            // 使用递推公式计算下一个组合数
            value = value * (rowIndex - k) / (k + 1);
        }

        return row;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int rowIndex = 3;
        System.out.println(solution.getRow(rowIndex)); // 输出：[1, 3, 3, 1]
    }
}
```

---

### **示例运行**
#### 示例 1：
- **输入：** `rowIndex = 3`
- **输出：** `[1, 3, 3, 1]`
- **计算过程：**
    1. 初始值：`value = 1`
    2. 计算：`value = 1 * (3 - 0) / (0 + 1) = 3`，加入结果。
    3. 计算：`value = 3 * (3 - 1) / (1 + 1) = 3`，加入结果。
    4. 计算：`value = 3 * (3 - 2) / (2 + 1) = 1`，加入结果。
    5. 完成：返回 `[1, 3, 3, 1]`。

#### 示例 2：
- **输入：** `rowIndex = 0`
- **输出：** `[1]`

#### 示例 3：
- **输入：** `rowIndex = 1`
- **输出：** `[1, 1]`

---

### **复杂度分析**
- **时间复杂度：** O(\(rowIndex\))  
  只需计算第 `rowIndex` 行的值，使用组合数递推公式，避免冗余计算。
- **空间复杂度：** O(\(rowIndex\))  
  仅存储当前行的结果。

---

### **总结**
这种基于组合数递推公式的方法高效且简洁，避免了生成整个杨辉三角的浪费，非常适合处理大规模问题（如 \(rowIndex = 10^5\)）。