package leetcode.editor.cn;

// Java: 旋转图像

public class T48_RotateImage {
    public static void main(String[] args) {
        Solution solution = new T48_RotateImage().new Solution();
        // TO TEST
        // System.out.println(solution.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void rotate(int[][] matrix) {

        // 1. 先转置矩阵，再反转每一行
        int n = matrix.length;

        //1 转置矩阵
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
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
}
//leetcode submit region end(Prohibit modification and deletion)

}
