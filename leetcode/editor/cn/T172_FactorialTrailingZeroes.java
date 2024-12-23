package leetcode.editor.cn;

// Java: 阶乘后的零

public class T172_FactorialTrailingZeroes {
    public static void main(String[] args) {
        Solution solution = new T172_FactorialTrailingZeroes().new Solution();
        // TO TEST
        System.out.println(solution.trailingZeroes(3));  // 输出: 0
        System.out.println(solution.trailingZeroes(5));  // 输出: 1
        System.out.println(solution.trailingZeroes(10)); // 输出: 2
        System.out.println(solution.trailingZeroes(100)); // 输出: 24
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int trailingZeroes(int n) {
        int count = 0;

        // 每次将 n 除以 5，并累加结果
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
