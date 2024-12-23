package leetcode.editor.cn;

// Java: 多数元素

public class T169_MajorityElement {
    public static void main(String[] args) {
        Solution solution = new T169_MajorityElement().new Solution();
        // TO TEST
        System.out.println(solution.majorityElement(new int[]{3, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];  // 初始化候选元素为第一个元素
        int count = 1;  // 初始化计数器

        // 进行摩尔投票
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                candidate = nums[i];  // 选择新的候选人
                count = 1;
            } else if (nums[i] == candidate) {
                count++;  // 当前元素和候选人相同，计数器加1
            } else {
                count--;  // 当前元素和候选人不同，计数器减1
            }
        }

        return candidate;  // 最终候选人就是多数元素
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
