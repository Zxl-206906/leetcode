package leetcode.editor.cn;

// Java: 通配符匹配

public class T44_WildcardMatching {
    public static void main(String[] args) {
        Solution solution = new T44_WildcardMatching().new Solution();
        // TO TEST
        System.out.println(solution.isMatch("aa", "*"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isMatch(String s, String p) {
        //上下两个指针
        int i = 0, j = 0;
        int starPos = -1;  //*的位置  为-1 表示刚开始并没有
        int match = -1;    //只是匹配*用的

        while (i < s.length()) {   //指针是一定不能超过长度的
            if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) { //如果开始匹配或者匹配问号 两指针后移
                i++;
                j++;
            // 如果匹配到*，则将*的位置记录下来，并且将匹配到的位置记录下来，然后指针后移
            } else if (j < p.length() && p.charAt(j) == '*') {
                starPos = j;
                match = i;
                j = starPos + 1;
            // 说明*号没有匹配成功 match继续往后面走
            } else if (starPos != -1) {
                match++;
                i = match;
                j = starPos + 1;
            } else {
                return false;
            }
            // 如果匹配到最后一个字符，并且最后一个字符是*，则返回true
            while (j < p.length() && p.charAt(j) == '*') {
                j++;
            }
        }
        return j == p.length();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
