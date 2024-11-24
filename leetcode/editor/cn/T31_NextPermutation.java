package leetcode.editor.cn;

// Java: 下一个排列

public class T31_NextPermutation {
    public static void main(String[] args) {
        Solution solution = new T31_NextPermutation().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        //步骤一 从右边向左查找第一个升序的相邻数字对（i,i + 1） 满足num[i] < num[i+1]
        int i = nums.length - 2;
        //步骤二 若不存在，则说明nums已经是最大的排列，直接返回
        while (i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        if(i >= 0){
            //步骤三 从右边向左查找第一个比nums[i]大的数字，记作nums[j]
            int j = nums.length - 1;
            //步骤四 将nums[j]赋值给nums[i]
            while (j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            //步骤四 将nums[i]和nums[j]交换
            swap(nums,i,j);
        }
        reverse(nums,i+1);
    }


    // 用于交换数组中两个元素的位置
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 用于将数组的一部分翻转，即将nums[start:]变为升序
    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
