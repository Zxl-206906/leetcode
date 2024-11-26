package leetcode.editor.cn;

// Java: 字符串相乘

import java.math.BigInteger;

public class T43_MultiplyStrings {
    public static void main(String[] args) {
        Solution solution = new T43_MultiplyStrings().new Solution();
        // TO TEST
        System.out.println(solution.multiply("123", "456"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String multiply(String num1, String num2) {
        BigInteger n1 = new BigInteger(num1);
        BigInteger n2 = new BigInteger(num2);
        return n1.multiply(n2).toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
