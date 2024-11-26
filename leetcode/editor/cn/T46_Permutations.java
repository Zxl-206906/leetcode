package leetcode.editor.cn;

// Java: 全排列

import java.util.ArrayList;
import java.util.List;

public class T46_Permutations {
    public static void main(String[] args) {
        Solution solution = new T46_Permutations().new Solution();
        // TO TEST
        System.out.println(solution.permute(new int[]{1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> permute(int[] nums) {
            //创建一个result的集合，用于存放结果
            List<List<Integer>> result = new ArrayList<>();

            //初始化路径列表 path，用于记录当前的排列。
            List<Integer> path = new ArrayList<>();

            // 标记数字是否被使用
            boolean[] used = new boolean[nums.length];

            //回溯算法
            backtrack(nums, result, path, used);

            return result;
        }


        private void backtrack(int[] nums, List<List<Integer>> result, List<Integer> path, boolean[] used) {
            // 如果当前排列的长度等于 nums 的长度，则找到一个完整的排列
            if (path.size() == nums.length) {
                result.add(new ArrayList<>(path)); // 将当前排列加入结果列表
                return;
            }

            // 遍历每一个数字，尝试将其加入排列
            for (int i = 0; i < nums.length; i++) {
                // 如果该数字已经使用过，则跳过
                if (used[i]) {
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
