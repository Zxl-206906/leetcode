package leetcode.editor.cn;

// Java: 最大矩形

import java.util.Arrays;
import java.util.Stack;

public class T85_MaximalRectangle {
    public static void main(String[] args) {
        Solution solution = new T85_MaximalRectangle().new Solution();
        // TO TEST
        System.out.println(solution.maximalRectangle(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 主函数：寻找最大矩形
    public int maximalRectangle(char[][] matrix) {
        // 判断空矩阵情况
        if(matrix.length == 0) return 0;

        // 创建高度数组，大小等于矩阵的列数
        int[] height = new int[matrix[0].length];
        int res = 0; // 最大矩形面积

        // 遍历矩阵的每一行
        for (char[] chars : matrix) {
            // 更新当前行的高度
            for (int col = 0; col < chars.length; col++) {
                // 如果当前位置为'1'，则累加高度
                if (chars[col] == '1')
                    height[col]++;
                else // 否则，重置高度为0
                    height[col] = 0;
            }
            // 对于每一行，计算最大的矩形面积，并更新res
            res = Math.max(res, largestRectangleArea(height));
        }
        return res; // 返回最大矩形面积
    }

    // 辅助函数：计算柱状图中最大的矩形面积
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>(); // 单调递增栈
        int[] lefLower = new int[heights.length]; // 存储左边第一个小于当前高度的索引
        int[] rigLower = new int[heights.length]; // 存储右边第一个小于当前高度的索引
        Arrays.fill(rigLower,heights.length); // 初始化为右边界

        // 遍历每个柱子
        for(int i = 0;i < heights.length;i++) {
            // 当栈不为空且当前柱子高度小于栈顶柱子的高度
            while (!s.empty() && heights[s.peek()] > heights[i]) {
                // 更新右边第一个小于栈顶柱子高度的索引
                rigLower[s.pop()] = i;
            }
            // 更新左边第一个小于当前柱子高度的索引
            lefLower[i] = s.empty() ? -1 : s.peek();
            // 当前柱子索引入栈
            s.push(i);
        }

        int res = 0; // 最大矩形面积
        // 计算每个柱子的最大矩形面积
        for(int i = 0;i < heights.length;i++) {
            res = Math.max(res, (rigLower[i] - lefLower[i] - 1) * heights[i]);
        }
        return res; // 返回最大矩形面积
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
