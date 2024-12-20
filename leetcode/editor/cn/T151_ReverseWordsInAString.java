package leetcode.editor.cn;

// Java: 反转字符串中的单词

public class T151_ReverseWordsInAString {
    public static void main(String[] args) {
        Solution solution = new T151_ReverseWordsInAString().new Solution();
        // TO TEST
        System.out.println(solution.reverseWords("  hello world!  "));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String reverseWords(String s) {
            //第一步 先去除空格
            String[] words = s.trim().split(" ");
            //第二步 定义一个存储结果的数组
            StringBuilder sb = new StringBuilder();
            //从最后一个单词开始倒叙
            for (int i = words.length - 1; i >= 0; i--) {
                if (words[i].equals("")) {
                    continue;
                }
                sb.append(words[i]).append(" ");
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 1);
            }
            return sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
