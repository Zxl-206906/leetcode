package leetcode.editor.cn;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/11/18 下午3:19
 * @注释
 */

public class RegularExpressionMatching2{
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching2().new Solution();
        // 测试案例
        System.out.println(solution.isMatch("aa", "a"));        // false
        System.out.println(solution.isMatch("aa", "a*"));       // true
        System.out.println(solution.isMatch("ab", ".*"));       // true
        System.out.println(solution.isMatch("aab", "c*a*b"));   // true
        System.out.println(solution.isMatch("mississippi", "mis*is*p*.")); // false
        System.out.println(solution.isMatch("mississippi", "mis*is*ip*.")); // true
        System.out.println(solution.isMatch("", "c*"));         // true
        System.out.println(solution.isMatch("abc", "abc.*"));   // false
        System.out.println(solution.isMatch("abc", ".*c"));     // true
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 记忆化存储，用于缓存之前已经计算过的结果
        Boolean[][] memo;

        public boolean isMatch(String text, String pattern) {
            // 初始化记忆矩阵，未计算的值为null
            memo = new Boolean[text.length() + 1][pattern.length() + 1];
            // 从字符串和模式的开头开始递归匹配
            return dp(0, 0, text, pattern);
        }

        public boolean dp(int i, int j, String text, String pattern) {
            // 如果这个子问题已经计算过，则直接返回结果
            if (memo[i][j] != null) {
                return memo[i][j];
            }
            boolean ans;
            // 如果模式已经走到了末尾，返回text是否也走到了末尾
            if (j == pattern.length()) {
                ans = i == text.length();
            } else {
                // 检查text当前字符和pattern当前字符是否匹配
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));

                // 如果模式中下一个字符是'*'
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    // '*'匹配0次的情况 || '*'匹配1次或多次的情况
                    ans = (dp(i, j + 2, text, pattern) ||
                            first_match && dp(i + 1, j, text, pattern));
                } else {
                    // 如果下一个字符不是'*'，则继续递归检查下一个字符
                    ans = first_match && dp(i + 1, j + 1, text, pattern);
                }
            }
            // 计算完毕，存储结果
            memo[i][j] = ans;
            return ans;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}