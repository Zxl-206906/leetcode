package leetcode.editor.cn;

// Java: 单词搜索

public class T79_WordSearch {
    public static void main(String[] args) {
        Solution solution = new T79_WordSearch().new Solution();
        // TO TEST
        System.out.println(solution.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
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
    }
//leetcode submit region end(Prohibit modification and deletion)

}
