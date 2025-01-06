package leetcode.editor.cn;

// Java: 快乐数

import java.util.HashSet;

public class T202_HappyNumber {
    public static void main(String[] args) {
        Solution solution = new T202_HappyNumber().new Solution();
        // TO TEST
        // 输出 true
        System.out.println(solution.isHappy(19));
        // 输出 false
        System.out.println(solution.isHappy(2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isHappy(int n) {
            //创建一个来记录HashSet出现过的数字
            HashSet<Integer> seen = new HashSet<>();

            //不断进行平方和转换
            while (n != 1) {
                //如果已经出现过，则返回false
                if (seen.contains(n)) {
                    return false;
                }
                //如果没出现过，则添加到seen中
                seen.add(n);
                //进行平方和转换
                n = getSumOfSquares(n);
            }
            // 如果最终结果是1，返回true
            return true;
        }

        // 计算每个数字的平方和
        private int getSumOfSquares(int n) {
            int sum = 0;
            while (n > 0) {
                int digit = n % 10;
                sum += digit * digit;
                n /= 10;
            }
            return sum;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
