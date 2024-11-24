package leetcode.editor.cn;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/11/18 下午3:19
 * @注释
 */

public class RegularExpressionMatching3 {
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching3().new Solution();
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
        public boolean isMatch(String text, String pattern) {
            // 状态定义：dp[i][j] 表示 text 的前 i 个字符和 pattern 的前 j 个字符是否匹配
            boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
            // 初始状态：空字符串是可以匹配的
            dp[0][0] = true;

            for (int j = 2; j <= pattern.length(); j++) {
                if (pattern.charAt(j - 1) == '*') {
                    dp[0][j] = dp[0][j - 2];
                }
            }

            // 状态转移方程
            for (int i = 1; i <= text.length(); i++) {
                for (int j = 1; j <= pattern.length() ; j++) {
                    if (pattern.charAt(j - 1) == '*') {
                        // 如果 pattern 的第 j 个字符是 '*'，则需要考虑 pattern 的第 j - 1 个字符 pattern[j - 1]
                        if (pattern.charAt(j - 2) == text.charAt(i - 1) || pattern.charAt(j - 2) == '.') {
                            // 如果 pattern[j - 1] 为小写字母，那么 pattern[j - 1] 可以出现 0 次或多次
                            // 如果 pattern[j - 1] 为 '.'，那么 pattern[j - 1] 可以出现 0 次或多次
                            dp[i][j] = dp[i][j - 2] || dp[i - 1][j];
                        } else {
                            // 如果 pattern[j - 1] 为 '*'，那么 pattern[j - 1] 可以出现 0 次或多次
                            dp[i][j] = dp[i][j - 2];
                        }
                    } else {
                        // 如果 pattern 的第 j 个字符不是 '*'，则需要考虑 pattern 的第 j 个字符 pattern[j - 1]
                        if (pattern.charAt(j - 1) == text.charAt(i - 1) || pattern.charAt(j - 1) == '.') {
                            // 如果 pattern[j - 1] 为小写字母，那么 text[i - 1] 和 pattern[j - 1] 必须匹配
                            // 如果 pattern[j - 1] 为 '.'，那么 text[i - 1] 和 pattern[j - 1] 必须匹配
                            dp[i][j] = dp[i - 1][j - 1];
                        }
                    }
                }
            }
            // 返回值
            return dp[text.length()][pattern.length()];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}