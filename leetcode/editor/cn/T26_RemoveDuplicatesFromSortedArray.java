package leetcode.editor.cn;

// Java: 删除有序数组中的重复项

public class T26_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        Solution solution = new T26_RemoveDuplicatesFromSortedArray().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int removeDuplicates(int[] nums) {
            int i = 0,j = 0;
            for(;i < nums.length;i++)
                if(nums[i] != nums[j])
                    nums[++j] = nums[i];
            return j + 1;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}
