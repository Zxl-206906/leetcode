<p>给你一个由&nbsp;<code>'1'</code>（陆地）和 <code>'0'</code>（水）组成的的二维网格，请你计算网格中岛屿的数量。</p>

<p>岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。</p>

<p>此外，你可以假设该网格的四条边均被水包围。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == grid.length</code></li> 
 <li><code>n == grid[i].length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 300</code></li> 
 <li><code>grid[i][j]</code> 的值为 <code>'0'</code> 或 <code>'1'</code></li> 
</ul>

<div><li>👍 2662</li><li>👎 0</li></div>


这个问题要求我们计算二维网格中岛屿的数量。岛屿是由相邻的陆地（'1'）组成的区域，水（'0'）将这些区域隔开。相邻的陆地可以是水平或竖直方向上的相邻。

### 思路：
1. **岛屿的定义**：岛屿是由相邻的陆地组成的区域，陆地与陆地之间通过水平或竖直方向连接。
2. **遍历网格**：我们可以遍历整个网格，遇到一个未被访问的陆地（'1'），然后使用深度优先搜索（DFS）或广度优先搜索（BFS）来标记这个岛屿的所有部分，确保每个岛屿只被计算一次。
3. **DFS 或 BFS**：选择 DFS 或 BFS 来遍历一个岛屿的所有陆地，将其标记为已访问（'0' 或其他标记）。
4. **岛屿计数**：每次遇到未访问的陆地时，岛屿数量加 1，并开始标记这个岛屿。

### 算法步骤：
1. 遍历整个网格。
2. 对于每一个陆地（'1'），执行 DFS 或 BFS 标记所有相连的陆地（该岛屿）。
3. 每找到一个岛屿，岛屿计数加 1。

### Java 实现：

```java
public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int islandCount = 0;

        // 遍历整个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前是陆地，说明找到了一个岛屿
                if (grid[i][j] == '1') {
                    // 使用 DFS 标记这个岛屿
                    dfs(grid, i, j, m, n);
                    islandCount++; // 找到一个岛屿，计数加 1
                }
            }
        }

        return islandCount;
    }

    // DFS 遍历函数，将与当前陆地相连的部分标记为 '0'
    private void dfs(char[][] grid, int i, int j, int m, int n) {
        // 边界条件检查
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
            return;
        }

        // 标记当前陆地为水（已访问）
        grid[i][j] = '0';

        // 递归调用四个方向
        dfs(grid, i + 1, j, m, n); // 下
        dfs(grid, i - 1, j, m, n); // 上
        dfs(grid, i, j + 1, m, n); // 右
        dfs(grid, i, j - 1, m, n); // 左
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 示例 1
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println(solution.numIslands(grid1)); // 输出 1

        // 示例 2
        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println(solution.numIslands(grid2)); // 输出 3
    }
}
```

### 解释：
1. **`numIslands` 方法**：这是主方法，遍历网格中的每个格子。如果遇到陆地（'1'），就调用 `dfs` 方法来标记该岛屿的所有部分。每找到一个岛屿，岛屿计数 `islandCount` 加 1。
2. **`dfs` 方法**：深度优先搜索，递归遍历与当前陆地相连的所有陆地，并将它们标记为已访问。通过递归调用四个方向（上、下、左、右）来访问相邻的陆地。
3. **边界条件**：确保在 DFS 中不会越界访问，或者访问已经是水（'0'）的格子。

### 时间和空间复杂度：
- **时间复杂度**：O(m * n)，其中 m 是网格的行数，n 是网格的列数。每个格子最多被访问一次。
- **空间复杂度**：O(m * n)，在最坏情况下，递归栈深度为 m * n（例如，岛屿覆盖了整个网格）。

### 示例分析：
#### 示例 1：
输入：
```java
grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
```
输出：
```
1
```
解释：整个网格是一片岛屿，岛屿数量为 1。

#### 示例 2：
输入：
```java
grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
```
输出：
```
3
```
解释：网格中有三个岛屿。

### 优化：
这个问题的核心在于如何遍历岛屿并标记已访问区域。DFS 方法已经是解决该问题的标准方法，它简单且清晰。此外，BFS（广度优先搜索）也可以实现相同的效果，只是实现稍复杂一些。