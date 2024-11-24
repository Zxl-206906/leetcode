package leetcode.editor.cn;

// Java: 两数相除

public class T29_DivideTwoIntegers2 {
    public static void main(String[] args) {
        Solution solution = new T29_DivideTwoIntegers2().new Solution();
        // TO TEST
        System.out.println(solution.divide(10, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int dividend, int divisor) {
        // 处理特殊情况
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend < 0) ^ (divisor < 0);
        long ldividend = Math.abs((long) dividend);
        long ldivisor = Math.abs((long) divisor);
        long result = 0;

        while (ldividend >= ldivisor) {
            long tempDivisor = ldivisor, multiple = 1;
            while (ldividend >= tempDivisor + tempDivisor) {
                tempDivisor += tempDivisor; // 加倍除数
                multiple += multiple; // 记录加倍次数
            }
            ldividend -= tempDivisor; // 减去加倍后的除数
            result += multiple; // 累加倍数
        }

        return negative ? (int)-result : (int)result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
