package leetcode.editor.cn;

// Java: 颠倒二进制位

public class T190_ReverseBits {
    public static void main(String[] args) {
        Solution solution = new T190_ReverseBits().new Solution();
        // TO TEST
        System.out.println(solution.reverseBits(43261596));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int result = 0;
        // Loop through each of the 32 bits
        for (int i = 0; i < 32; i++) {
            // Shift result to the left to make room for the new bit
            result <<= 1;
            // Add the current bit of n to the result (either 0 or 1)
            result |= (n & 1);
            // Shift n to the right to process the next bit
            n >>= 1;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
