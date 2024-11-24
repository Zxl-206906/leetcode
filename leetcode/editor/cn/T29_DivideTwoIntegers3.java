package leetcode.editor.cn;

// Java: 两数相除

public class T29_DivideTwoIntegers3 {
    public static void main(String[] args) {
        Solution solution = new T29_DivideTwoIntegers3().new Solution();
        // TO TEST
        System.out.println(solution.divide(10, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int divide(int dividend, int divisor) {
        // 处理特殊情况：Integer.MIN_VALUE除以-1会溢出
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        // 使用异或运算确定结果的正负性，不同号为负
        boolean negative = (dividend < 0) ^ (divisor < 0);
        // 将被除数和除数都转换为正数，使用long类型避免溢出
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);

        // 初始化结果变量
        long result = 0;
        // 从最高位开始检查，对32位整数而言，这是31位
        for (int i = 31; i >= 0; i--) {
            // 检查ldividend右移i位后是否仍然大于等于ldivisor
            if ((ldividend >> i) >= ldivisor) {
                // 如果是，说明在2^i的位置上有一个ldivisor，将2^i累加到结果中
                result += 1L << i;
                // 同时，从ldividend中减去ldivisor左移i位的值，即减去了ldivisor的2^i倍
                ldividend -= ldivisor << i;
            }
        }

        // 根据之前确定的正负性，返回正确的结果
        return negative ? (int)-result : (int)result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
