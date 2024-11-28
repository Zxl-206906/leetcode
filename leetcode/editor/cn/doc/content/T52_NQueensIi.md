<p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n × n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>

<p>给你一个整数 <code>n</code> ，返回 <strong>n 皇后问题</strong> 不同的解决方案的数量。</p>

<p>&nbsp;</p>

<div class="original__bRMd"> 
 <div> 
  <p><strong>示例 1：</strong></p> 
  <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" /> 
  <pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>2
<strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
</pre> 
 </div>
</div>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 9</code></li> 
</ul>

<div><li>👍 535</li><li>👎 0</li></div>


好的，针对你刚才的要求：**返回 N 皇后问题的不同解法的数量**，我们可以修改现有的代码，去除返回解的过程，而仅统计解的数量。

具体来说，我们只需要：

1. 在回溯的过程中，当找到一个有效解时，**增加一个计数器**。
2. 保留棋盘的回溯过程，但不需要将解转化为 `List<String>`，只需要统计解的数量。

### 修改后的代码：

```java
package leetcode.editor.cn;

// Java: N 皇后II

import java.util.ArrayList;
import java.util.List;

public class T51_NQueens {
    public static void main(String[] args) {
        Solution solution = new T51_NQueens().new Solution();
        // TO TEST
        System.out.println(solution.totalNQueens(4)); // 输出解法数量
        System.out.println(solution.totalNQueens(1)); // 输出解法数量
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int totalNQueens(int n) {
        // 用来记录每一行皇后所在的列，d[i] = j 表示第 i 行皇后放在第 j 列
        int[] d = new int[n];

        // 回溯计数器
        int[] count = new int[1]; // 用数组包裹，以便在回溯过程中能够修改值

        // 开始回溯，初始化从第 0 行开始
        backtrack(0, n, d, count);
        
        // 返回解的数量
        return count[0];
    }

    // 回溯函数，尝试在第 row 行放置皇后
    private void backtrack(int row, int n, int[] d, int[] count) {
        // 如果当前行已经放置完所有皇后，说明一个解已经完成
        if (row == n) {
            // 增加计数
            count[0]++;
            return;
        }

        // 尝试在第 row 行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 检查当前列和对角线是否安全
            if (isSafe(row, col, d)) {
                // 放置皇后
                d[row] = col;

                // 递归放置下一行的皇后
                backtrack(row + 1, n, d, count);

                // 回溯，撤销放置的皇后
                d[row] = 0;
            }
        }
    }

    // 检查当前放置是否安全，即检查列和两条对角线
    private boolean isSafe(int row, int col, int[] d) {
        for (int i = 0; i < row; i++) {
            // 检查列是否有皇后
            if (d[i] == col) {
                return false;
            }
            // 检查是否与之前的皇后在同一对角线
            if (i - d[i] == row - col || i + d[i] == row + col) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
```

### 解释：

1. **计数器 `count`**：
    - 我们使用一个数组 `count[1]` 来存储解法的数量。通过使用数组可以在回溯的过程中修改其值。
    - 每当我们放置完所有皇后（即 `row == n` 时），就将 `count[0]` 自增，表示找到了一个有效解。

2. **回溯函数 `backtrack`**：
    - `row` 表示当前要放置皇后的行。
    - 在每一行，我们尝试将皇后放置到每一列（`col`），并通过 `isSafe` 函数检查该列和两条对角线是否没有其他皇后。
    - 如果可以放置皇后，我们递归到下一行。
    - 如果找到了一个解（即成功放置了所有的皇后），就增加计数器。
    - 如果递归完成，我们撤销放置的皇后，回溯到上一行继续尝试其他可能的解。

3. **`isSafe` 函数**：
    - 该函数用于检查当前行和列放置的皇后是否安全。即，检查是否有其他皇后在同一列或对角线上。

### 示例：

对于输入 `n = 4`，回溯的过程会找到两个不同的解法，返回的解法数量是 `2`：

```
输入: n = 4
输出: 2
```

对于输入 `n = 1`，由于棋盘只有一个格子，唯一的解是放置一个皇后，返回的解法数量是 `1`：

```
输入: n = 1
输出: 1
```

### 时间复杂度：
- 由于回溯的过程中每一行都可以尝试放置 `n` 个皇后，最坏情况下的时间复杂度为 `O(n!)`。
