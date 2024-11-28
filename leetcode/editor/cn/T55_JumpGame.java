package leetcode.editor.cn;

// Java: 跳跃游戏

public class T55_JumpGame {
    public static void main(String[] args) {
        Solution solution = new T55_JumpGame().new Solution();
        // TO TEST
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        // 判断是否能够到达最后一个下标
        public static boolean canJump(int[] nums) {
            int maxReach = 0;  // 初始化能到达的最远位置

            // 遍历每个下标
            for (int i = 0; i < nums.length; i++) {
                // 如果当前位置无法到达，返回 false
                if (i > maxReach) {
                    return false;
                }
                // 更新最远可以到达的位置
                maxReach = Math.max(maxReach, i + nums[i]);

                // 如果最远可达位置已经覆盖到最后一个下标，返回 true
                if (maxReach >= nums.length - 1) {
                    return true;
                }
            }

            return false;  // 如果循环结束，无法到达最后一个下标
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
