package leetcode.editor.cn;

// Java: 电话号码的字母组合

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T17_LetterCombinationsOfAPhoneNumber3 {
    public static void main(String[] args) {
        Solution solution = new T17_LetterCombinationsOfAPhoneNumber3().new Solution();
        // TO TEST
        System.out.println(solution.letterCombinations("23"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //回溯法
    class Solution {
        // 数字到字母的映射
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // 存储结果
        List<String> result = new ArrayList<>();

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.isEmpty()) {
                return result;
            }
            // 回溯
            backtrack(digits, 0, new StringBuilder());
            return result;
        }

        /**
         * 回溯算法
         *
         * @param digits 输入的数字字符串
         * @param index  当前处理的数字索引
         * @param sb     当前的组合
         */
        private void backtrack(String digits, int index, StringBuilder sb) {
            // 如果当前处理的数字索引等于数字字符串的长度，说明已经处理完了，直接将当前的组合加入到结果中
            if (index == digits.length()) {
                result.add(sb.toString());
                return;
            }
            // 获取当前数字字符
            char digit = digits.charAt(index);
            // 获取当前数字字符对应的字母
            String letters = mapping[digit - '0'];
            // 遍历当前数字字符对应的所有字母
            for (char letter : letters.toCharArray()) {
                sb.append(letter); // 将当前字母加入到组合中
                backtrack(digits, index + 1, sb); // 处理下一个数字字符
                sb.deleteCharAt(sb.length() - 1); // 回溯，将当前字母从组合中删除
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
