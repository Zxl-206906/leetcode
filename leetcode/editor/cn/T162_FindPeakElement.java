package leetcode.editor.cn;

// Java: 寻找峰值

public class T162_FindPeakElement {
    public static void main(String[] args) {
        Solution solution = new T162_FindPeakElement().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 比较 mid 和 mid+1 的值
            if (nums[mid] > nums[mid + 1]) {
                // 峰值在左侧（包含 mid）
                right = mid;
            } else {
                // 峰值在右侧
                left = mid + 1;
            }
        }

        // 最终 left == right，返回任意一个都可以
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
