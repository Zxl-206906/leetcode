package leetcode.editor.cn;

// Java: 切蛋糕的最小总开销 I

import java.util.Arrays;

public class T3218_MinimumCostForCuttingCakeI {
    public static void main(String[] args) {
        Solution solution = new T3218_MinimumCostForCuttingCakeI().new Solution();
        // TO TEST
        // 示例 1
        int m1 = 3, n1 = 2;
        int[] horizontalCut1 = {1, 3};
        int[] verticalCut1 = {5};
        System.out.println(solution.minimumCost(m1, n1, horizontalCut1, verticalCut1));  // 输出 13

        // 示例 2
        int m2 = 2, n2 = 2;
        int[] horizontalCut2 = {7};
        int[] verticalCut2 = {4};
        System.out.println(solution.minimumCost(m2, n2, horizontalCut2, verticalCut2));  // 输出 15
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        // 对水平和垂直切割线进行排序，从大到小
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);

        // 当前的行数和列数
        int horizontalCount = 1;
        int verticalCount = 1;

        // 总开销
        int totalCost = 0;

        // 双指针分别指向水平切割线和垂直切割线
        int i = horizontalCut.length - 1;
        int j = verticalCut.length - 1;

        // 贪心选择切割线
        while (i >= 0 && j >= 0) {
            // 如果水平切割线的开销大于垂直切割线，则选择水平切割
            if (horizontalCut[i] > verticalCut[j]) {
                totalCost += horizontalCut[i] * verticalCount; // 当前剩余的列数
                horizontalCount++; // 增加一行
                i--;
            } else {
                totalCost += verticalCut[j] * horizontalCount; // 当前剩余的行数
                verticalCount++; // 增加一列
                j--;
            }
        }

        // 如果还有剩余的水平切割线
        while (i >= 0) {
            totalCost += horizontalCut[i] * verticalCount;
            horizontalCount++;
            i--;
        }

        // 如果还有剩余的垂直切割线
        while (j >= 0) {
            totalCost += verticalCut[j] * horizontalCount;
            verticalCount++;
            j--;
        }

        return totalCost;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
