package leetcode.editor.cn;

// Java: 搜索旋转排序数组 II

public class T81_SearchInRotatedSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new T81_SearchInRotatedSortedArrayIi().new Solution();
        // TO TEST
        System.out.println(solution.search(new int[]{2, 5, 6, 0, 0, 1, 2}, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean search(int[] nums, int target) {
            int l = 0, r = nums.length - 1;

            // 预处理，把右侧重复数字删去
            while (l < r && nums[l] == nums[r]) r--;

            // 二分找到数组旋转点
            while (l < r) {
                int mid = l + (r - l) / 2 + 1;
                if (nums[mid] >= nums[0]) l = mid;
                else r = mid - 1;
            }

            // 确定 target 在哪个区间，重新设置左右指针
            if (target >= nums[0]) {
                l = 0;
            } else {
                l = r + 1;
                r = nums.length - 1;
            }


            // 在子区间内二分查找
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (nums[mid] >= target) r = mid;
                else l = mid + 1;
            }

            // 注意：此时 l 可能越界，返回时用 r 判断
            return nums[r] == target;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
