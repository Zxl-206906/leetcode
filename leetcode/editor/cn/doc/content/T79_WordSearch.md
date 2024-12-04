<p>给定一个&nbsp;<code>m x n</code> 二维字符网格&nbsp;<code>board</code> 和一个字符串单词&nbsp;<code>word</code> 。如果&nbsp;<code>word</code> 存在于网格中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
<strong>输出：</strong>true
</pre>

<p><strong>示例 3：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == board.length</code></li> 
 <li><code>n = board[i].length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 6</code></li> 
 <li><code>1 &lt;= word.length &lt;= 15</code></li> 
 <li><code>board</code> 和 <code>word</code> 仅由大小写英文字母组成</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以使用搜索剪枝的技术来优化解决方案，使其在 <code>board</code> 更大的情况下可以更快解决问题？</p>

<div><li>👍 1903</li><li>👎 0</li></div>



这个问题是一个经典的 **单词搜索** 问题，可以使用 **回溯法** 来解决。我们需要在二维网格中找出是否存在某个单词。通过回溯，我们可以从网格的每个位置开始尝试查找单词的每个字母，并且我们需要确保不会重复访问相同的单元格。

### 思路：
1. 我们可以从网格的每一个单元格出发，检查是否可以找到整个单词。
2. 对于每一个单元格，我们判断它是否匹配单词的第一个字母。如果匹配，则继续尝试找下一个字母，直到单词的所有字母都被找到。
3. 在搜索过程中，为了避免重复使用同一个单元格，我们可以将当前单元格标记为已访问（比如将字符替换为一个特殊的字符或直接记录访问状态）。
4. 如果在某个路径上找到了整个单词，返回 `true`；如果没有找到，继续尝试其它路径。

### 解法：
我们可以使用深度优先搜索（DFS）来实现回溯。

### 代码实现：

```text
public class Solution {
    public boolean exist(char[][] board, String word) {
        // 遍历整个网格
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                // 从每个位置开始搜索
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 深度优先搜索函数，i, j是当前的位置，index是当前匹配的字符在word中的位置
    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        // 如果匹配到整个单词，返回true
        if (index == word.length()) {
            return true;
        }
        
        // 判断边界条件，越界或者字符不匹配
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(index)) {
            return false;
        }

        // 记录当前字符
        char temp = board[i][j];
        // 将当前字符标记为已访问，可以用一个特殊字符，如'#'
        board[i][j] = '#';

        // 继续在四个方向上进行搜索
        boolean result = dfs(board, word, i + 1, j, index + 1) || 
                         dfs(board, word, i - 1, j, index + 1) ||
                         dfs(board, word, i, j + 1, index + 1) || 
                         dfs(board, word, i, j - 1, index + 1);

        // 恢复当前格子的字符
        board[i][j] = temp;

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.exist(new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        }, "ABCCED")); // 输出 true
        System.out.println(solution.exist(new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        }, "SEE")); // 输出 true
        System.out.println(solution.exist(new char[][]{
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        }, "ABCB")); // 输出 false
    }
}
```

### 解释：
1. **exist** 方法是主方法，遍历整个 `board` 网格，尝试从每个位置出发调用 `dfs` 方法来搜索单词。
2. **dfs** 方法是深度优先搜索（DFS）方法：
    - `i` 和 `j` 是当前的位置。
    - `index` 是当前要匹配的字符在单词中的索引。
    - 如果 `index == word.length()`，说明已经成功匹配完了整个单词，返回 `true`。
    - 如果当前字符不匹配或者越界，返回 `false`。
    - 为了避免重复使用同一个单元格，使用 `#` 或其它标记来临时标记该单元格为已访问。
    - 使用递归在四个方向上进行搜索：上下左右。
    - 如果找到一个有效路径，返回 `true`，否则继续搜索其他可能的路径。

3. **恢复字符**：为了确保不影响其他的搜索路径，搜索完成后恢复当前格子的字符。

### 时间复杂度：
- 最坏情况下，DFS 会遍历整个网格，每个格子最多被访问一次，因此时间复杂度是 `O(m * n * 4^L)`，其中：
    - `m` 和 `n` 分别是网格的行和列数。
    - `L` 是单词的长度（最多为 15）。
    - 每个单元格最多会有 4 个方向的搜索，因此复杂度为 `4^L`。

### 空间复杂度：
- 空间复杂度是 `O(m * n)`，主要由递归栈空间和存储网格状态（如果使用额外的标记）所占用。

### 示例输出：
#### 示例 1：
输入：
```text
board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]];
word = "ABCCED";
```
输出：`true`

#### 示例 2：
输入：
```text
board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]];
word = "SEE";
```
输出：`true`

#### 示例 3：
输入：
```text
board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]];
word = "ABCB";
```
输出：`false`