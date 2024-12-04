package leetcode.editor.cn;

// Java: 组合

import java.util.ArrayList;
import java.util.List;

public class T77_Combinations {
    public static void main(String[] args) {
        Solution solution = new T77_Combinations().new Solution();
        // TO TEST
        System.out.println(solution.combine(4, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {

        // TODO 创建一个结果的集合，用于存放结果
        List<List<Integer>> result = new ArrayList<>();

        // TODO 创建一个当前结果的集合，用于记录当前的排列。
        backtrack(n, k, 1, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int n, int k, int start, List<Integer> current, List<List<Integer>> result) {
        // TODO 如果当前结果已经包含K个数，则将当前结果加入结果集
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        // TODO 遍历从start到n的所有数字
        for (int i = start; i <= n; i++) {
            // TODO 将当前数字加入当前结果
            current.add(i);
            // TODO 递归调用，继续尝试下一个数字
            backtrack(n, k, i + 1, current, result);
            // TODO 回溯，将当前数字从当前结果中移除
            current.remove(current.size() - 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
