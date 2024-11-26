package leetcode.editor.cn;

// Java: 在排序数组中查找元素的第一个和最后一个位置

public class T34_FindFirstAndLastPositionOfElementInSortedArray2 {
    public static void main(String[] args) {
        Solution solution = new T34_FindFirstAndLastPositionOfElementInSortedArray2().new Solution();
        // TO TEST
        System.out.println(solution.searchRange(new int[]{5,7,7,8,8,10}, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int[] result = new int[2];
            result[0] = findFirstPosition(nums, target);
            result[1] = findLastPosition(nums, target);
            return result;
        }



        // 查找目标值的第一个位置
        private int findFirstPosition(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    // 如果 mid 是第一个元素或者前一个元素不等于 target，mid 就是第一个位置
                    if (mid == 0 || nums[mid - 1] != target) {
                        return mid;
                    }
                    // 否则在左半部分继续查找
                    right = mid - 1;
                }
            }
            return -1;
        }



        // 查找目标值的最后一个位置
        private int findLastPosition(int[] nums, int target) {
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    // 如果 mid 是最后一个元素或者后一个元素不等于 target，mid 就是最后一个位置
                    if (mid == nums.length - 1 || nums[mid + 1] != target) {
                        return mid;
                    }
                    // 否则在右半部分继续查找
                    left = mid + 1;
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
