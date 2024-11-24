package leetcode.editor.cn;

// Java: 移除元素

public class T27_RemoveElement {
    public static void main(String[] args) {
        Solution solution = new T27_RemoveElement().new Solution();
        // TO TEST
        System.out.println(solution.removeElement(new int[]{3, 2, 2, 3}, 3));
        
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int removeElement(int[] nums, int val) {
        int j = 0; // 标记新数组的索引位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) { // 如果当前元素不等于目标值
                nums[j++] = nums[i]; // 直接覆盖原数组
            }
        }
        return j; // 返回新数组的长度
    }
}

//leetcode submit region end(Prohibit modification and deletion)

}
