package leetcode.editor.cn;

// Java: 柱状图中最大的矩形

import java.util.Arrays;
import java.util.Stack;

public class T84_LargestRectangleInHistogram2 {
    public static void main(String[] args) {
        Solution solution = new T84_LargestRectangleInHistogram2().new Solution();
        // TO TEST
        System.out.println(solution.largestRectangleArea(new int[]{2, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestRectangleArea(int[] heights) {
            // 定义单调栈，栈内元素是柱子的索引，栈内索引对应的高度是单调递增的
            Stack<Integer> s = new Stack<>();
            // 初始化lefLower数组存储每根柱子向左遇到的第一个高度小于它的柱子的索引
            int[] lefLower = new int[heights.length];
            // 初始化rigLower数组存储每根柱子向右遇到的第一个高度小于它的柱子的索引
            int[] rigLower = new int[heights.length];
            // 默认将rigLower中的所有值设为heights的长度，这表示默认情况下每根柱子向右可以扩展到数组的最右边
            Arrays.fill(rigLower, heights.length);

            for (int i = 0; i < heights.length; i++) {
                // 如果栈不为空，且当前柱子的高度小于栈顶柱子的高度，则表示当前柱子是栈顶柱子向右的第一个低柱
                while (!s.empty() && heights[s.peek()] > heights[i]) {
                    rigLower[s.pop()] = i;
                }
                // 当前柱子向左的第一个低柱的索引是栈顶的索引。如果栈为空，则表示没有这样的柱子，所以值为-1
                lefLower[i] = s.empty() ? -1 : s.peek();
                // 将当前柱子的索引入栈
                s.push(i);
            }

            int res = 0;
            for (int i = 0; i < heights.length; i++) {
                // 对于每根柱子，其对应的最大矩形面积是 (rigLower[i] - lefLower[i] - 1) * heights[i]
                res = Math.max(res, (rigLower[i] - lefLower[i] - 1) * heights[i]);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
