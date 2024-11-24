package leetcode.editor.cn;

// Java: 两数相除

public class T29_DivideTwoIntegers {
    public static void main(String[] args) {
        Solution solution = new T29_DivideTwoIntegers().new Solution();
        // TO TEST
        System.out.println(solution.divide(10, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int dividend, int divisor) {
        //偏要使用除法
        long result = (long) dividend / divisor;

        //处理溢出情况
        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return (int) result;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
