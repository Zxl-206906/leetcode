package leetcode.editor.cn;

// Java: 括号生成

import java.util.ArrayList;
import java.util.List;

public class T22_GenerateParentheses {
    public static void main(String[] args) {
        Solution solution = new T22_GenerateParentheses().new Solution();
        // TO TEST
        System.out.println(solution.generateParenthesis(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            //创建一个新的ArrayList用于存放结果集
            List<String> result = new ArrayList<>();
            backtracking(n, result, 0, 0, "");
            return result;
        }

        public void backtracking(int n, List<String> result, int left, int right, String str) {
            //如果右括号的数量大于左括号的数量 直接终止
            if (right > left) {
                return;
            }
            //如果左右括号数量都等于n，说明已经生成了一个有效括号组合，将其添加到结果集中
            if (left == n && right == n) {
                result.add(str);
                return;
            }
            //如果左括号数量小于n，可以继续添加左括号
            if (left < n) {
                backtracking(n, result, left + 1, right, str + "(");
            }
            //如果右括号数量小于左括号数量，可以继续添加右括号
            if (right < left) {
                backtracking(n, result, left, right + 1, str + ")");
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
