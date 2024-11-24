package leetcode.editor.cn;

// Java: 最接近的三数之和

import java.util.Arrays;

public class T16_ThreeSumClosest {
    public static void main(String[] args) {
        Solution solution = new T16_ThreeSumClosest().new Solution();
        // TO TEST
        System.out.println(solution.threeSumClosest(new int[]{2,3,8,9,10}, 16));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //首先对数组进行排序   2 3 8 9 10
        Arrays.sort(nums);
        //定义数组的长度
        int n = nums.length;
//        System.out.println(n);
        //初始化最接近的和为前三个元素的和
        int closestSum = nums[0] + nums[1] + nums[2];

        //遍历数组，除了最后两个元素（因为我们需要三个数）
        for (int i = 0; i < n - 2; i++) {
            //双指针初始化。left指向当前元素之后的元素，right指向数组的最后一个元素
            int left = i + 1, right = n - 1;
            while (left < right) {
                //计算当前三个数的和
                int sum = nums[i] + nums[left] + nums[right];
                //如果当前值和与目标值的差的绝对值小于最接近于目标值的差的绝对值
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    //更新最接近的和
                    closestSum = sum;
                }
                //根据与比较值的比较 移动指针
                if (sum > target) {
                    right--;  // 和大于目标值，移动右指针向左
                }else if (sum < target){
                    left++;   // 和小于目标值，移动左指针向右
                }else {
                    return sum; // 如果和等于目标值，直接返回当前和
                }
            }
        }
        //找到最接近的和
        return closestSum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
