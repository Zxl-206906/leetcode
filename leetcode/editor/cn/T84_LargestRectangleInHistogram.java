package leetcode.editor.cn;

// Java: 柱状图中最大的矩形

import java.util.Arrays;
import java.util.Stack;

public class T84_LargestRectangleInHistogram {
    public static void main(String[] args) {
        Solution solution = new T84_LargestRectangleInHistogram().new Solution();
        // TO TEST
        System.out.println(solution.largestRectangleArea(new int[]{2,4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        // 声明一个栈，用来保存柱子的索引
        Stack<Integer> s = new Stack<>();

        // 分别存储每个柱子左边和右边第一个小于它的柱子的位置
        int[] lefLower = new int[heights.length];
        int[] rigLower = new int[heights.length];

        // 初始化，默认左边界为-1，右边界为heights.length
        Arrays.fill(lefLower,-1);
        Arrays.fill(rigLower,heights.length);

        // 找每个柱子的右边界
        for(int i = 0; i < heights.length; i++) {
            while (!s.empty() && heights[s.peek()] > heights[i]) {
                rigLower[s.pop()] = i;
            }
            s.push(i);
        }

        // 清空栈，为找左边界做准备
        s = new Stack<>();

        // 找每个柱子的左边界
        for (int i = heights.length - 1; i >= 0; i--) {
            while(!s.empty() && heights[s.peek()] > heights[i]) {
                lefLower[s.pop()] = i;
            }
            s.push(i);
        }

        // 对于每一个柱子，求其能够扩展的最大矩形面积
        int res = 0;
        for(int i = 0; i < heights.length; i++) {
            res = Math.max(res, (rigLower[i] - lefLower[i] - 1) * heights[i]);
        }

        // 返回结果
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
