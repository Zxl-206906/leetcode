package leetcode.editor.cn;

// Java: 组合总和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T39_CombinationSum {
    public static void main(String[] args) {
        Solution solution = new T39_CombinationSum().new Solution();
        // TO TEST
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //1.初始化一个ArrayList列表
        List<List<Integer>> result = new ArrayList<>();
        //2.排序
        Arrays.sort(candidates);
        //3.定义一个回溯算法
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }


        private static void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
            if (target == 0) {
                // 找到一个有效组合
                result.add(new ArrayList<>(currentCombination));
                return;
            }
            for (int i = start; i < candidates.length; i++) {
                if (candidates[i] > target) break; // 剪枝
                currentCombination.add(candidates[i]); // 选择当前数字
                backtrack(candidates, target - candidates[i], i, currentCombination, result); // 递归，允许重复选择当前数字
                currentCombination.remove(currentCombination.size() - 1); // 回溯，移除最后一个数字
            }
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
