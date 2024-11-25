package leetcode.editor.cn;

// Java: 组合总和 II

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T40_CombinationSumIi {
    public static void main(String[] args) {
        Solution solution = new T40_CombinationSumIi().new Solution();
        // TO TEST
        System.out.println(solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序，便于去重和剪枝
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            // 找到一个有效组合，加入结果集
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 去重：跳过同层中相同的数字
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 剪枝：若当前数字大于目标值，直接退出循环
            if (candidates[i] > target) {
                break;
            }

            // 选择当前数字，递归到下一层
            currentCombination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, currentCombination, result);

            // 回溯：移除最后一个数字
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
