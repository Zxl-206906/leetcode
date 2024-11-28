<p>给你一个 <code>m</code> 行 <code>n</code> 列的矩阵&nbsp;<code>matrix</code> ，请按照 <strong>顺时针螺旋顺序</strong> ，返回矩阵中的所有元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral1.jpg" style="width: 242px; height: 242px;" /> 
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[1,2,3,6,9,8,7,4,5]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/spiral.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
<strong>输出：</strong>[1,2,3,4,8,12,11,10,9,5,6,7]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == matrix.length</code></li> 
 <li><code>n == matrix[i].length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 10</code></li> 
 <li><code>-100 &lt;= matrix[i][j] &lt;= 100</code></li> 
</ul>

<div><li>👍 1798</li><li>👎 0</li></div>


### 问题描述

给定一个 `m` 行 `n` 列的矩阵 `matrix`，请按顺时针的螺旋顺序返回矩阵中的所有元素。

### **思路解析**

1. **边界管理**：
    - 我们需要逐步“螺旋”遍历矩阵。每次遍历时，都会在矩阵的外圈进行一次遍历。
    - 通过设置四个边界：`top`, `bottom`, `left`, `right` 来标记当前的有效区域。这四个边界会逐渐收缩，直到矩阵的所有元素都被访问过。

2. **遍历顺序**：
    - **从左到右**：遍历当前的上边界 `top`，然后 `top` 向下移动。
    - **从上到下**：遍历当前的右边界 `right`，然后 `right` 向左移动。
    - **从右到左**：遍历当前的下边界 `bottom`，然后 `bottom` 向上移动。
    - **从下到上**：遍历当前的左边界 `left`，然后 `left` 向右移动。

3. **终止条件**：
    - 当 `top` 超过 `bottom` 或者 `left` 超过 `right` 时，表示矩阵已经完全遍历，结束循环。

---

### **Java 实现**

```java
import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return result;
        }
        
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // 从左到右遍历 top 行
            for (int i = left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            top++;  // 遍历完一行后，上边界下移
            
            // 从上到下遍历 right 列
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--;  // 遍历完一列后，右边界左移
            
            if (top <= bottom) {
                // 从右到左遍历 bottom 行
                for (int i = right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;  // 遍历完一行后，下边界上移
            }
            
            if (left <= right) {
                // 从下到上遍历 left 列
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;  // 遍历完一列后，左边界右移
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        System.out.println(spiralOrder(matrix1));  // 输出: [1, 2, 3, 6, 9, 8, 7, 4, 5]
        
        int[][] matrix2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        System.out.println(spiralOrder(matrix2));  // 输出: [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
    }
}
```

---

### **详细步骤**

1. **初始化边界**：
    - 设置四个边界：`top = 0`，`bottom = matrix.length - 1`，`left = 0`，`right = matrix[0].length - 1`。
    - `top` 和 `bottom` 控制行的上下边界，`left` 和 `right` 控制列的左右边界。

2. **从左到右遍历**：
    - 遍历上边界 `top` 所对应的行，从 `left` 到 `right`。
    - 遍历后，将 `top` 边界下移。

3. **从上到下遍历**：
    - 遍历右边界 `right` 所对应的列，从 `top` 到 `bottom`。
    - 遍历后，将 `right` 边界左移。

4. **从右到左遍历**：
    - 如果 `top` 小于等于 `bottom`，则遍历下边界 `bottom` 所对应的行，从 `right` 到 `left`。
    - 遍历后，将 `bottom` 边界上移。

5. **从下到上遍历**：
    - 如果 `left` 小于等于 `right`，则遍历左边界 `left` 所对应的列，从 `bottom` 到 `top`。
    - 遍历后，将 `left` 边界右移。

6. **终止条件**：
    - 当 `top` 超过 `bottom` 或者 `left` 超过 `right` 时，退出循环。

---

### **时间复杂度**

- 时间复杂度：`O(m * n)`，其中 `m` 是矩阵的行数，`n` 是矩阵的列数。我们需要遍历矩阵中的每个元素一次。
- 空间复杂度：`O(m * n)`，用于存储结果数组。

---

### **总结**

1. **螺旋遍历**：通过不断更新边界，逐步收缩有效区域，完成螺旋顺序遍历。
2. **边界管理**：通过四个边界控制遍历范围，确保每个元素只访问一次。
3. **时间复杂度**：`O(m * n)`，适合处理较大的矩阵。

这个方法高效且直观，可以用于解决类似的矩阵遍历问题。