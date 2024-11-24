package leetcode.editor.cn;

// Java: 找出字符串中第一个匹配项的下标

public class T28_FindTheIndexOfTheFirstOccurrenceInAString {
    public static void main(String[] args) {
        Solution solution = new T28_FindTheIndexOfTheFirstOccurrenceInAString().new Solution();
        // TO TEST
        System.out.println(solution.strStr("hello", "ll"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        // 使用 indexOf 方法查找 needle 在 haystack 中的第一次出现
        return haystack.indexOf(needle);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
