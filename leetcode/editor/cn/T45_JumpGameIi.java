package leetcode.editor.cn;

// Java: 跳跃游戏 II

public class T45_JumpGameIi {
    public static void main(String[] args) {
        Solution solution = new T45_JumpGameIi().new Solution();
        // TO TEST
        System.out.println(solution.jump(new int[]{2,3,1,1,4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int jump(int[] nums) {
        // 数组的长度
        int n = nums.length;
        // 结束位置
        int end = 0;
        // 最大位置
        int maxPosition = 0;
        // 跳跃次数
        int steps = 0;
        // 遍历数组
        for (int i = 0; i < n - 1; i++) {
            // 获取最大位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 如果当前位置等于结束位置
            if (i == end) {
                // 更新结束位置
                end = maxPosition;
                // 跳跃次数加 1
                steps++;
            }
        }
        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
