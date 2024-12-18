package leetcode.editor.cn;

// Java: 单词拆分

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T139_WordBreak {
    public static void main(String[] args) {
        Solution solution = new T139_WordBreak().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordSet = new HashSet<>(wordDict);  // 使用哈希集来存储字典，方便快速查找
            int n = s.length();

            // dp[i] 表示 s[0...i-1] 是否可以拆分为字典中的单词
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;  // 空字符串是可以拆分的

            for (int i = 1; i <= n; i++) {
                // 遍历所有可能的结束位置
                for (int j = 0; j < i; j++) {
                    // 如果 dp[j] 为 true 且 s[j...i-1] 在字典中，则 dp[i] 为 true
                    if (dp[j] && wordSet.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;  // 如果 dp[i] 为 true，提前跳出循环
                    }
                }
            }

            return dp[n];  // dp[n] 表示 s[0...n-1] 是否可以被拆分
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
