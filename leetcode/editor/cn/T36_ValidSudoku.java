package leetcode.editor.cn;

// Java: 有效的数独

import java.util.HashSet;
import java.util.Set;

public class T36_ValidSudoku {
    public static void main(String[] args) {
        Solution solution = new T36_ValidSudoku().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isValidSudoku(char[][] board) {
            // 检查每一行
            for (int i = 0; i < 9; i++) {
                Set<Character> rowSet = new HashSet<>();
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        if (rowSet.contains(c)) {
                            return false; // 如果行中已经有这个数字，返回 false
                        }
                        rowSet.add(c);
                    }
                }
            }
            // 检查每一列
            for (int j = 0; j < 9; j++) {
                Set<Character> colSet = new HashSet<>();
                for (int i = 0; i < 9; i++) {
                    char c = board[i][j];
                    if (c != '.') {
                        if (colSet.contains(c)) {
                            return false; // 如果列中已经有这个数字，返回 false
                        }
                        colSet.add(c);
                    }
                }
            }
            // 检查每一个 3x3 宫格
            for (int block = 0; block < 9; block++) {
                Set<Character> blockSet = new HashSet<>();
                int rowStart = (block / 3) * 3;
                int colStart = (block % 3) * 3;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        char c = board[rowStart + i][colStart + j];
                        if (c != '.') {
                            if (blockSet.contains(c)) {
                                return false; // 如果 3x3 宫格中已经有这个数字，返回 false
                            }
                            blockSet.add(c);
                        }
                    }
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
