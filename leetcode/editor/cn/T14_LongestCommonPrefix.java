package leetcode.editor.cn;

//Java：最长公共前缀

class T14_LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new T14_LongestCommonPrefix().new Solution();
        // TO TEST
        
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //以第一个字符串作为初始的最长公共前缀
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.length() == 0) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
