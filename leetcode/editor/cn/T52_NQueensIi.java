package leetcode.editor.cn;

// Java: N 皇后 II

public class T52_NQueensIi {
    public static void main(String[] args) {
        Solution solution = new T52_NQueensIi().new Solution();
        // TO TEST
        
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
