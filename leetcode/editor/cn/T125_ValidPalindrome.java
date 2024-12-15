package leetcode.editor.cn;

// Java: 验证回文串

public class T125_ValidPalindrome {
    public static void main(String[] args) {
        Solution solution = new T125_ValidPalindrome().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            // 跳过左边非字母数字字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 跳过右边非字母数字字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 比较当前字符是否相等（忽略大小写）
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
