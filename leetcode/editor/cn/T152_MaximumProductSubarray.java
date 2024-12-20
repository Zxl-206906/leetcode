package leetcode.editor.cn;

// Java: 乘积最大子数组

public class T152_MaximumProductSubarray {
    public static void main(String[] args) {
        Solution solution = new T152_MaximumProductSubarray().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(int[] nums) {
        //初始化最大值和最小值
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = nums[0];

        //从第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            //如果当前元素为0，则最大值和最小值都为0
            if (nums[i] == 0) {
                maxSoFar = 0;
                minSoFar = 0;
                continue;
            }
            //如果当前元素为负数，则交换最大值和最小值
            if (nums[i] < 0) {
                int temp = maxSoFar;
                maxSoFar = minSoFar;
                minSoFar = temp;
            }
            //更新最大值和最小值
            maxSoFar = Math.max(nums[i], maxSoFar * nums[i]);
            minSoFar = Math.min(nums[i], minSoFar * nums[i]);
            //更新结果
            result = Math.max(result, maxSoFar);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
