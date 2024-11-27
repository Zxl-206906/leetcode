<p>给定一个 <em>n&nbsp;</em>×&nbsp;<em>n</em> 的二维矩阵&nbsp;<code>matrix</code> 表示一个图像。请你将图像顺时针旋转 90 度。</p>

<p>你必须在<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank"> 原地</a></strong> 旋转图像，这意味着你需要直接修改输入的二维矩阵。<strong>请不要 </strong>使用另一个矩阵来旋转图像。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="height: 188px; width: 500px;" /> 
<pre>
<strong>输入：</strong>matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>输出：</strong>[[7,4,1],[8,5,2],[9,6,3]]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="height: 201px; width: 500px;" /> 
<pre>
<strong>输入：</strong>matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
<strong>输出：</strong>[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == matrix.length == matrix[i].length</code></li> 
 <li><code>1 &lt;= n &lt;= 20</code></li> 
 <li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li> 
</ul>

<p>&nbsp;</p>

<div><li>👍 1967</li><li>👎 0</li></div>


好的，以后我会使用 Java 实现算法并提供详细的思路解释，同时使用 Markdown 格式来展示过程。以下是用 Java 实现二维矩阵顺时针旋转 90 度的解决方案：

---

### **问题描述**

给定一个 `n × n` 的二维矩阵 `matrix`，表示一个图像。请你将图像顺时针旋转 90 度。要求**原地旋转**，即不能使用另一个矩阵来存储旋转后的结果。

---

### **思路解析**

1. **转置矩阵**：
    - 将矩阵的行和列交换。例如，`matrix[i][j]` 和 `matrix[j][i]` 交换。
    - 这个操作可以通过双重循环来完成，只需遍历上三角部分的元素（避免重复交换）。

2. **反转每一行**：
    - 在转置后的矩阵中，反转每一行。即将每一行的元素顺序逆序。

这两步操作能在原地将矩阵顺时针旋转 90 度。

---

### **Java 实现**

```java
public class MatrixRotation {
    
    // 旋转矩阵
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // 第一步：转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 交换 matrix[i][j] 和 matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // 第二步：反转每一行
        for (int i = 0; i < n; i++) {
            int left = 0, right = n - 1;
            while (left < right) {
                // 交换 matrix[i][left] 和 matrix[i][right]
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
                left++;
                right--;
            }
        }
    }

    // 打印矩阵
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        rotate(matrix1);
        printMatrix(matrix1);  // 输出: [[7, 4, 1], [8, 5, 2], [9, 6, 3]]

        int[][] matrix2 = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };
        rotate(matrix2);
        printMatrix(matrix2);  // 输出: [[15, 13, 2, 5], [14, 3, 4, 1], [12, 6, 8, 9], [16, 7, 10, 11]]
    }
}
```

---

### **详细步骤**

#### **步骤 1：转置矩阵**

转置操作是将矩阵的行列进行交换。具体来说，对于矩阵 `matrix`，`matrix[i][j]` 和 `matrix[j][i]` 交换。为了避免重复交换，我们只遍历矩阵的上三角部分（即 `i < j` 的部分）。

- **举例**：

  原矩阵：
  ```plaintext
  [[1, 2, 3],
   [4, 5, 6],
   [7, 8, 9]]
  ```

  转置后的矩阵：
  ```plaintext
  [[1, 4, 7],
   [2, 5, 8],
   [3, 6, 9]]
  ```

#### **步骤 2：反转每一行**

转置完成后，我们需要反转每一行的元素。反转操作是将每一行的第一个元素与最后一个元素交换，第二个元素与倒数第二个元素交换，依此类推。

- **举例**：

  转置后的矩阵：
  ```plaintext
  [[1, 4, 7],
   [2, 5, 8],
   [3, 6, 9]]
  ```

  反转每一行后的矩阵：
  ```plaintext
  [[7, 4, 1],
   [8, 5, 2],
   [9, 6, 3]]
  ```

#### **最终结果**

通过上述两步操作，矩阵成功完成了顺时针旋转 90 度。

- 输入：
  ```plaintext
  [[1, 2, 3],
   [4, 5, 6],
   [7, 8, 9]]
  ```

- 输出：
  ```plaintext
  [[7, 4, 1],
   [8, 5, 2],
   [9, 6, 3]]
  ```

---

### **时间复杂度分析**

- **转置矩阵**：需要两重循环遍历矩阵的上三角部分，时间复杂度是 `O(n^2)`。
- **反转每一行**：每一行需要进行 `n/2` 次交换，总的时间复杂度是 `O(n^2)`。

因此，整体时间复杂度是 `O(n^2)`。

### **空间复杂度分析**

- 由于我们是原地修改矩阵，没有使用额外的空间，所以空间复杂度是 `O(1)`。

---

### **总结**

1. **转置矩阵**：通过交换 `matrix[i][j]` 和 `matrix[j][i]` 来实现行列交换。
2. **反转每一行**：通过反转每一行的顺序来完成顺时针旋转。
3. **时间复杂度**：`O(n^2)`
4. **空间复杂度**：`O(1)`