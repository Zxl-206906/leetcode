package leetcode.editor.cn;

// Java: 寻找旋转排序数组中的最小值

public class T153_FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new T153_FindMinimumInRotatedSortedArray().new Solution();
        // TO TEST
        System.out.println(solution.findMin(new int[]{3,4,5,1,2}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findMin(int[] nums) {

            int left = 0, right = nums.length - 1;

            while (left < right) {
                int mid = left + (right - left) / 2;

                // 判断中间值与右边界值的关系
                if (nums[mid] > nums[right]) {
                    // 最小值一定在右半部分
                    left = mid + 1;
                } else {
                    // 最小值在左半部分或就是 mid
                    right = mid;
                }
            }

            // 循环结束时，left == right
            return nums[left];

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
