package leetcode.editor.cn;

// Java: 三角形最小路径和

import java.util.List;

public class T120_Triangle {
    public static void main(String[] args) {
        Solution solution = new T120_Triangle().new Solution();
        // TO TEST
        List<List<Integer>> triangle1 = List.of(
                List.of(2),
                List.of(3, 4),
                List.of(6, 5, 7),
                List.of(4, 1, 8, 3)
        );
        // 输出: 11
        System.out.println("最小路径和: " + solution.minimumTotal(triangle1));

        // 示例 2
        List<List<Integer>> triangle2 = List.of(
                List.of(-10)
        );
        // 输出: -10
        System.out.println("最小路径和: " + solution.minimumTotal(triangle2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // 记录三角形的层数
        int triangleSize = triangle.size();

        //用于存储当前行的最小路径和
        int[] dp = new int[triangleSize];

        //初始化dp为最后一行的值
        for (int i = 0; i < triangleSize; i++) {
            dp[i] = triangle.get(triangleSize - 1).get(i);
         }

        //从倒数第二行开始向上更新
        for (int i = triangleSize - 2; i >= 0 ; i--) {
            // 当前行的元素个数从左到右遍历
            for (int j = 0; j <= i; j++) {
                // 更新当前位置的最小路径和，取左下方和右下方的最小值，再加上当前位置的值
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }

        // dp[0] 存储了从顶到底的最小路径和
        return dp[0];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
