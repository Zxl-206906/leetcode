<p>给定一个非负整数&nbsp;<em><code>numRows</code>，</em>生成「杨辉三角」的前&nbsp;<em><code>numRows</code>&nbsp;</em>行。</p>

<p><small>在「杨辉三角」中，每个数是它左上方和右上方的数的和。</small></p>

<p><img alt="" src="https://pic.leetcode-cn.com/1626927345-DZmfxB-PascalTriangleAnimated2.gif" /></p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> numRows = 5
<strong>输出:</strong> [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> numRows = 1
<strong>输出:</strong> [[1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= numRows &lt;= 30</code></li> 
</ul>

<div><li>👍 1206</li><li>👎 0</li></div>


构造杨辉三角的前 `numRows` 行，可以使用迭代的方式逐层生成，每一行的值由上一行的左右相邻元素相加得出。以下是 Java 解法和详细解析：

### Java 解法
```java
import text.util.ArrayList;
import text.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        
        // 第 1 行固定为 [1]
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            // 每行的首尾为 1
            row.add(1);
            
            for (int j = 1; j < i; j++) {
                // 中间的元素为上一行相邻两个元素之和
                row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
            }
            
            // 除第 1 行外，每行末尾也为 1
            if (i > 0) {
                row.add(1);
            }
            
            // 将当前行加入到杨辉三角中
            triangle.add(row);
        }
        
        return triangle;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int numRows = 5;
        List<List<Integer>> result = solution.generate(numRows);
        System.out.println(result);
    }
}
```

### 示例输入输出
#### 示例 1
- **输入：** `numRows = 5`
- **输出：**
```text
[
 [1],
 [1, 1],
 [1, 2, 1],
 [1, 3, 3, 1],
 [1, 4, 6, 4, 1]
]
```

#### 示例 2
- **输入：** `numRows = 1`
- **输出：**
```text
[
 [1]
]
```

### 代码解析
1. **初始化：** 创建一个 `List<List<Integer>>` 用于存储每一行。
2. **逐行构造：**
    - 每行第一个元素为 `1`。
    - 遍历上一行，计算当前行的中间元素：`row[j] = previous_row[j - 1] + previous_row[j]`。
    - 每行的最后一个元素为 `1`。
3. **存储结果：** 构造完成后，将该行加入三角形列表。
4. **返回结果：** 生成完整的杨辉三角后返回。

### 时间和空间复杂度
- **时间复杂度：** O(\(n^2\))，其中 \(n\) 为 `numRows`，生成每一行需遍历上一行。
- **空间复杂度：** O(\(n^2\))，用于存储杨辉三角。

### 测试
运行代码时，输入不同的 `numRows`，可验证生成的杨辉三角是否正确。