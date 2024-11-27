package leetcode.editor.cn;

// Java: Pow(x, n)

public class T50_PowxN {
    public static void main(String[] args) {
        Solution solution = new T50_PowxN().new Solution();
        // TO TEST
        System.out.println(solution.myPow(2.00000, 10));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double myPow(double x, int n) {
        // 计算 x 的 n 次幂
        if (n == 0) {
            return 1;  // 任何数的零次幂为 1
        }

        // 处理负幂的情况
        long N = n;  // 使 n 成为 long 类型，避免 n = Integer.MIN_VALUE 时溢出
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        while (N > 0) {
            if (N % 2 == 1) {
                result *= x;  // 如果是奇数次幂，则乘上当前的 x
            }
            x *= x;  // 将 x 的次方每次翻倍
            N /= 2;  // 每次将 n 减半
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
