package leetcode.editor.cn;

// Java: 寻找旋转排序数组中的最小值 II

public class T154_FindMinimumInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new T154_FindMinimumInRotatedSortedArrayIi().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {
            int left = 0, right = nums.length - 1;

            while (left < right) {
                int mid = (left + right) / 2;
                if (nums[mid] > nums[right]) {
                    left = mid + 1;
                } else if (nums[mid] < nums[right]) {
                    right = mid;
                } else {
                    right--;
                }
            }
            return nums[left];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
