package leetcode.editor.cn;

// Java: 爬楼梯

public class T70_ClimbingStairs {
    public static void main(String[] args) {
        Solution solution = new T70_ClimbingStairs().new Solution();
        // TO TEST
        System.out.println(solution.climbStairs(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        int n1 = 1;
        int n2 = 2;
        if (n == 1) {
            return n1;
        }
        if (n == 2) {
            return n2;
        }
        //n1、n2 都后移一个位置
        for (int i = 3; i <= n; i++) {
            int temp = n2;
            n2 = n1 + n2;
            n1 = temp;
        }
        return n2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
