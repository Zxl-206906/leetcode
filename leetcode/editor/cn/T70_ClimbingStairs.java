package leetcode.editor.cn;

// Java: 爬楼梯

public class T70_ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new T70_ClimbingStairs().new Solution();
        // TO TEST
        System.out.println(solution.climbStairs(2));
    }

    //用递归的思路想一下，要求 n 层的台阶的走法，由于一次走 1 或 2 个台阶，所以上到第 n 个台阶之前，一定是停留在第 n - 1 个台阶上，或者 n - 2 个台阶上。所以如果用 f ( n ) 代表 n 个台阶的走法。那么，
    //
    //
    //
    //f ( n ) = f ( n - 1) + f ( n - 2 )。
    //
    //
    //
    //f ( 1 ) = 1，f ( 2 )  = 2 。
    //
    //
    //
    //发现个神奇的事情，这就是斐波那契数列（Fibonacci sequence）。
    //
    //
    //
    //直接暴力一点，利用递归写出来。
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        return climbStairsN(n);
    }

    private int climbStairsN(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairsN(n - 1) + climbStairsN(n - 2);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
