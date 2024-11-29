package leetcode.editor.cn;

// Java: 爬楼梯

public class T70_ClimbingStairs2 {
    public static void main(String[] args) {
        Solution solution = new T70_ClimbingStairs2().new Solution();
        // TO TEST
        System.out.println(solution.climbStairs(2));
    }
//当然递归可以解决，我们可以直接迭代，省去递归压栈的过程。初始值 f ( 1 ) 和 f ( 2 )，
// 然后可以求出 f ( 3 )，然后求出 f ( 4 ) ... 直到 f ( n )，一个循环就够了。其实就是动态规划的思想了。
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
