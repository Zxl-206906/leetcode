package leetcode.editor.cn;

// Java: 最后一个单词的长度

public class T58_LengthOfLastWord {
    public static void main(String[] args) {
        Solution solution = new T58_LengthOfLastWord().new Solution();
        // TO TEST
        System.out.println(solution.lengthOfLastWord("Hello World"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLastWord(String s) {

        // 思路：倒序遍历字符串，找到第一个非空格字符，然后计算最后一个单词的长度

        //统计单词的长度
        int length = 0;

        // 倒序遍历
        int n = s.length();

        //从右往左查找第一和非空格字符
        int i = n - 1;

        // 跳过末尾的空格
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // 计算最后一个单词的长度
        //接着遍历字符串中的字符，直到遇到空格或者遍历到字符串的开始。每遇到一个字母，计数器 length 加 1。
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }

        return length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
