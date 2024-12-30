package leetcode.editor.cn;

// Java: 位1的个数

public class T191_NumberOf1Bits {
    public static void main(String[] args) {
        Solution solution = new T191_NumberOf1Bits().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
