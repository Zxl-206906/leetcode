package leetcode.editor.cn;

// Java: 搜索插入位置

import java.util.Arrays;

public class T35_SearchInsertPosition2 {
    public static void main(String[] args) {
        Solution solution = new T35_SearchInsertPosition2().new Solution();
        // TO TEST
        System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 4));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index >= 0 ? index : -index - 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
