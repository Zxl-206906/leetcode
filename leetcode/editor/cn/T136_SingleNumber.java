package leetcode.editor.cn;

// Java: 只出现一次的数字

public class T136_SingleNumber {
    public static void main(String[] args) {
        Solution solution = new T136_SingleNumber().new Solution();
        // TO TEST
        System.out.println(solution.singleNumber(new int[]{2, 2, 1}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber(int[] nums) {
        // 初始化结果为 0
        int result = 0;

        // 遍历数组中的每个元素
        for (int num : nums) {
            // 利用异或操作消除成对出现的数字
            result ^= num;
        }

        // 返回只出现一次的元素
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
