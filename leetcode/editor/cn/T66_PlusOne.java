package leetcode.editor.cn;

// Java: 加一

public class T66_PlusOne {
    public static void main(String[] args) {
        Solution solution = new T66_PlusOne().new Solution();
        // TO TEST
        System.out.println(solution.plusOne(new int[]{1,2,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        for(int i = digits.length - 1;i >= 0;i--){
            digits[i] ++;
            digits[i] %= 10;
            if(digits[i] != 0)//不用再进位了
                return digits;
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
