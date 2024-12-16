package leetcode.editor.cn;

// Java: 只出现一次的数字 II

public class T137_SingleNumberIi {
    public static void main(String[] args) {
        Solution solution = new T137_SingleNumberIi().new Solution();
        // TO TEST
        // 示例 1
        int[] nums1 = {2, 2, 3, 2};
        System.out.println("只出现一次的数字是: " + solution.singleNumber(nums1));

        // 示例 2
        int[] nums2 = {0, 1, 0, 1, 0, 1, 99};
        System.out.println("只出现一次的数字是: " + solution.singleNumber(nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        // ones 和 twos 用于记录当前位出现 1 次和 2 次的状态
        int ones = 0, twos = 0;

        for (int num : nums) {
            // 更新 ones 和 twos
            ones = (ones ^ num) & ~twos;
            twos = (twos ^ num) & ~ones;
        }

        // 最终 ones 保存了只出现一次的数字
        return ones;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
