package leetcode.editor.cn;

// Java: 外观数列

public class T38_CountAndSay {
    public static void main(String[] args) {
        Solution solution = new T38_CountAndSay().new Solution();
        // TO TEST
        System.out.println(solution.countAndSay(4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }

        // 获取前一个外观数列
        String prev = countAndSay(n - 1);

        //表示结果变量result
        StringBuilder result = new StringBuilder();

        int i = 0; // 指针 i 表示区间起始
        while (i < prev.length()) {
            int j = i; // 指针 j 用于遍历
            // 找到当前字符的连续区间
            while (j < prev.length() && prev.charAt(j) == prev.charAt(i)) {
                j++;
            }

            // 区间长度 = j - i，当前字符 = prev.charAt(i)
            int count = j - i;
            result.append(count).append(prev.charAt(i));

            // 更新 i 到下一个区间的起始
            i = j;
        }
        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
