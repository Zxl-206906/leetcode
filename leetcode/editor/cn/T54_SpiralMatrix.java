package leetcode.editor.cn;

// Java: 螺旋矩阵

import java.util.ArrayList;
import java.util.List;

public class T54_SpiralMatrix {
    public static void main(String[] args) {
        Solution solution = new T54_SpiralMatrix().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public static List<Integer> spiralOrder(int[][] matrix) {
            // 结果数组
            List<Integer> result = new ArrayList<>();

            // 边界条件判断 长度和宽度都为0
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return result;
            }

            int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;

            while (top <= bottom && left <= right) {
                // 从左到右遍历 top 行
                for (int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                top++;  // 遍历完一行后，上边界下移

                // 从上到下遍历 right 列
                for (int i = top; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                right--;  // 遍历完一列后，右边界左移

                if (top <= bottom) {
                    // 从右到左遍历 bottom 行
                    for (int i = right; i >= left; i--) {
                        result.add(matrix[bottom][i]);
                    }
                    bottom--;  // 遍历完一行后，下边界上移
                }

                if (left <= right) {
                    // 从下到上遍历 left 列
                    for (int i = bottom; i >= top; i--) {
                        result.add(matrix[i][left]);
                    }
                    left++;  // 遍历完一列后，左边界右移
                }
            }

            return result;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
