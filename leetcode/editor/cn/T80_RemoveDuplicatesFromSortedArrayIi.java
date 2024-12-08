package leetcode.editor.cn;

// Java: 删除有序数组中的重复项 II

public class T80_RemoveDuplicatesFromSortedArrayIi {
    public static void main(String[] args) {
        Solution solution = new T80_RemoveDuplicatesFromSortedArrayIi().new Solution();
        // TO TEST
        System.out.println(solution.removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int removeDuplicates(int[] nums) {
            // 如果数组长度小于等于2，则直接返回长度
            if(nums.length <= 2)
                return nums.length;

            // checked 表示当前检查到的位置，也是下一个要放入的位置
            // checking 表示当前正在检查的位置
            int checked = 2, checking = 2;

            // 遍历数组
            while(checking < nums.length){
                // 如果当前检查的数字和 checked 位置的前两个数字不同，
                // 这意味着我们可以把这个数字放到 checked 位置
                if(nums[checked - 2] != nums[checking]){
                    nums[checked] = nums[checking];
                    checked++;
                }
                // 无论是否复制数字，都向前移动 checking 指针
                checking++;
            }

            // 返回新的数组长度
            return checked;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
