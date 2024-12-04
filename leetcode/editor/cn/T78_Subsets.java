package leetcode.editor.cn;

// Java: 子集

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jerry
 */

public class T78_Subsets {
    public static void main(String[] args) {
        Solution solution = new T78_Subsets().new Solution();
        // TO TEST
        System.out.println(solution.subsets(new int[]{1, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> subsets(int[] nums) {

            //TODO 创建一个结果的集合，用于存放结果
            List<List<Integer>> result = new ArrayList<>();

            //TODO 创建一个当前结果的集合，用于记录当前的排列。
            backtrack(nums, 0, new ArrayList<>(), result);
            return result;
        }

        private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
            //TODO 添加当前结果
            result.add(new ArrayList<>(current));

            //TODO 遍历当前数组，从start开始
            for (int i = start; i < nums.length; i++) {
                //TODO 添加当前元素
                current.add(nums[i]);
                //TODO 递归，从当前元素开始，继续遍历
                backtrack(nums, i + 1, current, result);
                //TODO 删除当前元素
                current.remove(current.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
