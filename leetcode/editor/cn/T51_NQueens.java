package leetcode.editor.cn;

// Java: N 皇后

import java.util.ArrayList;
import java.util.List;

public class T51_NQueens {
    public static void main(String[] args) {
        Solution solution = new T51_NQueens().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // d[] 用来记录每一行皇后所在的列，d[i] = j 表示第 i 行皇后放在第 j 列
        int[] d = new int[n];

        // result[][] 用来记录棋盘状态，最终用来保存一个有效解
        char[][] board = new char[n][n];

        // 初始化棋盘，所有格子都为空 '.'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // 开始回溯，初始化从第 0 行开始
        backtrack(0, n, d, board, result);
        return result;
    }

    // 回溯函数，尝试在第 row 行放置皇后
    private void backtrack(int row, int n, int[] d, char[][] board, List<List<String>> result) {
        // 如果当前行已经放置完所有皇后，说明一个解已经完成
        if (row == n) {
            // 将当前棋盘状态转为 List<String>，加入结果集
            List<String> solution = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                solution.add(new String(board[i]));  // 将每一行转为字符串
            }
            result.add(solution);
            return;
        }

        // 尝试在第 row 行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            // 检查当前列和对角线是否安全
            if (isSafe(row, col, d)) {
                // 放置皇后
                d[row] = col;
                board[row][col] = 'Q';  // 在棋盘上放置皇后

                // 递归放置下一行的皇后
                backtrack(row + 1, n, d, board, result);

                // 回溯，撤销放置的皇后
                board[row][col] = '.';
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
