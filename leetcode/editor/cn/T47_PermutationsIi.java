package leetcode.editor.cn;

// Java: 全排列 II

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T47_PermutationsIi {
    public static void main(String[] args) {
        Solution solution = new T47_PermutationsIi().new Solution();
        // TO TEST
        System.out.println(solution.permuteUnique(new int[]{1, 1, 2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            boolean[] used = new boolean[nums.length];

            // 排序数组，以便剪枝
            Arrays.sort(nums);

            backtrack(nums, result, path, used);
            return result;
        }


        // 回溯函数
        private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] used) {
            // 如果当前排列的长度等于 nums 的长度，说明找到一个完整的排列
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path));
                return;
            }

            // 遍历每一个数字，尝试将其加入排列
            for (int i = 0; i < nums.length; i++) {
                // 剪枝条件：如果当前数字已经被使用，或者与前一个数字相同且前一个数字还未被使用
                if (used[i] || (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])) {
                    continue;
                }

                // 做选择：选择当前数字
                path.add(nums[i]);
                used[i] = true;

                // 继续递归处理剩余数字的排列
                backtrack(nums, result, path, used);

                // 撤销选择：回溯
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
