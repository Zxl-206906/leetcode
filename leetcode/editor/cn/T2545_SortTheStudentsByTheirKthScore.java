package leetcode.editor.cn;

// Java: 根据第 K 场考试的分数排序

import java.util.Arrays;

public class T2545_SortTheStudentsByTheirKthScore {
    public static void main(String[] args) {
        Solution solution = new T2545_SortTheStudentsByTheirKthScore().new Solution();
        // TO TEST
        // 示例1
        int[][] score1 = {
                {10, 6, 9, 1},
                {7, 5, 11, 2},
                {4, 8, 3, 15}
        };
        int k1 = 2;
        int[][] result1 = solution.sortTheStudents(score1, k1);
        // 输出：[[7, 5, 11, 2], [10, 6, 9, 1], [4, 8, 3, 15]]
        System.out.println(Arrays.deepToString(result1));

        // 示例2
        int[][] score2 = {
                {3, 4},
                {5, 6}
        };
        int k2 = 0;
        int[][] result2 = solution.sortTheStudents(score2, k2);
        // 输出：[[5, 6], [3, 4]]
        System.out.println(Arrays.deepToString(result2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] sortTheStudents(int[][] score, int k) {
        // 使用 Arrays.sort 来对学生进行排序
        // 由于 score[i] 是一个数组，按第 k 列的元素来排序
        Arrays.sort(score, (a, b) -> Integer.compare(b[k], a[k]));
        return score;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
