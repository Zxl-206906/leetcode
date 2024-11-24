//给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。 
//
// 
// '.' 匹配任意单个字符 
// '*' 匹配零个或多个前面的那一个元素 
// 
//
// 所谓匹配，是要涵盖 整个 字符串 s 的，而不是部分字符串。 
//
// 示例 1： 
//
// 
//输入：s = "aa", p = "a"
//输出：false
//解释："a" 无法匹配 "aa" 整个字符串。
// 
//
// 示例 2: 
//
// 
//输入：s = "aa", p = "a*"
//输出：true
//解释：因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
// 
//
// 示例 3： 
//
// 
//输入：s = "ab", p = ".*"
//输出：true
//解释：".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 20 
// 1 <= p.length <= 20 
// s 只包含从 a-z 的小写字母。 
// p 只包含从 a-z 的小写字母，以及字符 . 和 *。 
// 保证每次出现字符 * 时，前面都匹配到有效的字符 
// 
//
// Related Topics 递归 字符串 动态规划 👍 3996 👎 0

package leetcode.editor.cn;
public class RegularExpressionMatching{
    public static void main(String[] args) {
         Solution solution = new RegularExpressionMatching().new Solution();
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
            // 基本情况：如果模式串为空，那么输入字符串也必须为空才匹配
            if (pattern.isEmpty()) {
                return text.isEmpty();
            }

            // 检查第一个字符是否匹配（考虑'.'的情况）
            boolean first_match = (!text.isEmpty() &&
                    (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

            // 如果模式的下一个字符是'*'
            if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
                // 两种情况：
                // 1. 忽略模式中的这两个字符（'x*'），因为'*'可以表示前面的字符出现0次
                // 2. 删除匹配的字符串中的一个字符（如果它能与模式中的'x'匹配），因为'*'可以表示多次
                return (isMatch(text, pattern.substring(2)) ||
                        (first_match && isMatch(text.substring(1), pattern)));
            } else {
                // 如果下一个字符不是'*'，则直接比较当前字符，并递归处理剩余部分
                return first_match && isMatch(text.substring(1), pattern.substring(1));
            }
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
