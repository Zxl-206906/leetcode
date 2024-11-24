package leetcode.editor.cn;

// Java: 搜索插入位置

public class T35_SearchInsertPosition {
    public static void main(String[] args) {
        Solution solution = new T35_SearchInsertPosition().new Solution();
        // TO TEST
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
