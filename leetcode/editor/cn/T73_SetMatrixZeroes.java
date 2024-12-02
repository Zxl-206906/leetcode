package leetcode.editor.cn;

// Java: 矩阵置零

public class T73_SetMatrixZeroes {
    public static void main(String[] args) {
        Solution solution = new T73_SetMatrixZeroes().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        boolean[] zeroInRow = new boolean[n];
        boolean[] zeroInLine = new boolean[m];
        for(int i = 0;i < n;i++)
            for(int j = 0;j < m;j++)
                if(matrix[i][j] == 0){
                    zeroInRow[i] = true;
                    zeroInLine[j] = true;
                }
        for(int i = 0;i < n;i++)
            for(int j = 0;j < m;j++)
                if(zeroInRow[i] || zeroInLine[j]){
                    matrix[i][j] = 0;
                }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
