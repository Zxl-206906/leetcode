package leetcode.editor.cn;

// Java: 移除元素

public class T27_RemoveElement2 {
    public static void main(String[] args) {
        Solution solution = new T27_RemoveElement2().new Solution();
        // TO TEST
        System.out.println(solution.removeElement(new int[]{3, 2, 2, 3}, 3));
        
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeElement(int[] nums, int val) {
        //如果数组为空 直接返回 0
        if (nums == null || nums.length == 0) return 0;
        //定义一个慢指针
        int i = 0;
        //定义一个快指针 用来遍历数组
        for (int j = 0; j < nums.length; j++) {
            //如果当前元素不等于目标值，则将当前元素赋值给慢指针的位置，并且慢指针向后移动一位
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        //返回慢指针的位置，即数组中不等于目标值的元素的个数
        return i;
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}
