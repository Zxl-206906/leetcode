package leetcode.editor.cn;

// Java: 打家劫舍

public class T198_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new T198_HouseRobber().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rob(int[] nums) {
        // 边界条件  如果没有房屋，返回 0
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 当只有一个房屋时，直接返回它的金额
        if (nums.length == 1) {
            return nums[0];
        }

        // 初始化 dp 数组
        int n = nums.length;
        int[] dp = new int[n];

        // 初始化前两个房屋的最大金额
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // 从第三个房屋开始计算 dp[i]
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        // 返回最后一个房屋的最大金额
        return dp[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
