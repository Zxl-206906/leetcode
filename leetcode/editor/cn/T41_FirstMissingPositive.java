package leetcode.editor.cn;

// Java: 缺失的第一个正数

public class T41_FirstMissingPositive {
    public static void main(String[] args) {
        Solution solution = new T41_FirstMissingPositive().new Solution();
        // TO TEST
        System.out.println(solution.firstMissingPositive(new int[]{1, 2, 0}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // 第一步：将数字放置到正确的位置
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        // 第二步：查找第一个不在正确位置的数字
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1; // 返回缺失的正整数
            }
        }

        // 如果所有数字都在正确位置，返回 n + 1
        return n + 1;
    }

    // 交换两个索引的元素
    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
