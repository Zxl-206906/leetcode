package leetcode.editor.cn;

// Java: 四数之和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T18_FourSum {
    public static void main(String[] args) {
        Solution solution = new T18_FourSum().new Solution();
        // TO TEST
        System.out.println(solution.fourSum(new int[]{2,2,2,2,2}, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            // 先排序
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            // 确定第一个数
            for (int i = 0; i < nums.length - 3; i++) {
                // 跳过重复的数字Q
                if (i > 0 && nums[i] == nums[i - 1]) continue;
                // 确定第二个数
                for (int j = i + 1; j < nums.length - 2; j++) {
                    // 同样跳过重复的数字
                    if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                    // 双指针找出剩下的两个数
                    int left = j + 1, right = nums.length - 1;
                    while (left < right) {
                        // 四数之和
                        long sum = (long)(nums[i]) + nums[j] + nums[left] + nums[right];
                        // 找到了
                        if (sum == target) {
                            // 添加到结果集中
                            result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                            // 跳过重复的数字
                            while (left < right && nums[left] == nums[left + 1]) left++;
                            while (left < right && nums[right] == nums[right - 1]) right--;
                            left++;
                            right--;
                        } else if (sum < target) {
                            left++;// 和小于 target，左指针右移，变大点
                        } else {
                            right--;// 和大于 target，右指针左移，变小点
                        }
                    }
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
