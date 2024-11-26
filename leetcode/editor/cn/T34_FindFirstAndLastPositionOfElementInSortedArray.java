package leetcode.editor.cn;

// Java: 在排序数组中查找元素的第一个和最后一个位置

public class T34_FindFirstAndLastPositionOfElementInSortedArray {
    public static void main(String[] args) {
        Solution solution = new T34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
        // TO TEST
        System.out.println(solution.searchRange(new int[]{5,7,7,8,8,10}, 8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int lef = -1,rig = -1;
        // 找第一个位置
        for(int i = 0;i < nums.length;i++){
            if(nums[i] == target){
                lef = i;
                break;
            }
        }
        // 找最后一个位置
        for(int i = nums.length - 1;i >= 0;i--){
            if(nums[i] == target){
                rig = i;
                break;
            }
        }
        return new int[]{lef,rig};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
