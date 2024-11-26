package leetcode.editor.cn;

// Java: 搜索旋转排序数组

public class T33_SearchInRotatedSortedArray {
    public static void main(String[] args) {
        Solution solution = new T33_SearchInRotatedSortedArray().new Solution();
        // TO TEST
        System.out.println(solution.search(new int[]{4,5,6,7,0,1,2}, 0));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int search(int[] nums, int target) {
        int siz = nums.length; // 数组长度
        int lef = 0, rig = siz - 1; // 初始化左右指针
        while (lef <= rig) { // 当左指针小于等于右指针时进行循环
            int mid = (lef + rig) >> 1; // 计算中间索引，使用右移运算符相当于除以 2
            if (nums[mid] == target) // 如果中间元素等于目标值，返回中间索引
                return mid;
            if (nums[0] <= nums[mid]) { // 如果左半部分有序
                if (nums[0] <= target && target < nums[mid]) // 如果目标值在左半部分范围内
                    rig = mid - 1; // 移动右指针
                else
                    lef = mid + 1; // 否则移动左指针
            } else { // 右半部分有序
                if (nums[mid] < target && target <= nums[siz - 1]) // 如果目标值在右半部分范围内
                    lef = mid + 1; // 移动左指针
                else
                    rig = mid - 1; // 否则移动右指针
            }
        }
        return -1; // 如果未找到目标值，返回 -1
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
