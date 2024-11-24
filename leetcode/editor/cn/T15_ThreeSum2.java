package leetcode.editor.cn;

// Java: 三数之和

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T15_ThreeSum2 {
    public static void main(String[] args) {
        Solution solution = new T15_ThreeSum2().new Solution();
        // TO TEST
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            //初始化一个ArrayList列表
            List<List<Integer>> rst = new ArrayList<>();
            //从而对数组进行排序
            Arrays.sort(nums);
            //判断数组的边界条件 数组不为空并且nums的长度不能小于3否则直接返回数组
            if (nums == null || nums.length < 3) {
                return rst;
            }
            //然后让i从数组的索引0开始循环，一直循环到数组末尾
            for (int i = 0; i < nums.length; i++) {
                //定义结果sum初始化为0
                int sum = 0;
                //判断如果sum为0并且i跟i-1的索引一样，则直接continue
                if (i > 0 && nums[i] == nums[i - 1]) {
                    continue;
                }
                //如果nums[i]的索引下标大于0 则直接返回数组
                if (nums[i] > 0) {
                    return rst;
                }
                //定义一个新的ArrayList数组
                List<Integer> oneRst = new ArrayList<>();
                //L为i的后一个元素
                int L = i + 1;
                //R为数组的最后一个元素
                int R = nums.length - 1;
                //循环的边界跳出条件为L<R
                while (L < R) {
                    //总和为sum 三者元素相加 i + L+ R
                    sum = nums[i] + nums[L] + nums[R];
                    //sum的总和小于0 数组是进行排序过的，然后让L指针向右侧移动
                    if (sum < 0) {
                        L++;
                        //如果sum总和是大于0的，然后让R指针向做移动
                    } else if (sum > 0) {
                        R--;
                        //否则就是刚好为0的情况，把元素统统塞进ArrayList里面
                    } else {
                        oneRst.add(nums[i]);
                        oneRst.add(nums[L]);
                        oneRst.add(nums[R]);
                        rst.add(oneRst);
                        oneRst = new ArrayList<>();
                        while (L < R && nums[L] == nums[L + 1]) {
                            L++;
                        }
                        while (L < R && nums[L] == nums[R - 1]) {
                            R--;
                        }
                        L++;
                        R--;
                    }
                }
            }
            return rst;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
