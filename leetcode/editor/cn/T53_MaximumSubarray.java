package leetcode.editor.cn;

// Java: 最大子数组和

public class T53_MaximumSubarray {
    public static void main(String[] args) {
        Solution solution = new T53_MaximumSubarray().new Solution();
        // TO TEST
        System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 动态规划解法
    public static int maxSubArray(int[] nums) {
        // 初始化当前子数组和及最大和
        int currentSum = nums[0];
        int maxSum = nums[0];

        // 从第二个元素开始遍历数组
        for (int i = 1; i < nums.length; i++) {
            // 如果当前和为负，则重新开始新的子数组
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            // 更新最大和
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
