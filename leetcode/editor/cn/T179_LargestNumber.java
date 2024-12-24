package leetcode.editor.cn;

// Java: 最大数

import java.util.Arrays;

public class T179_LargestNumber {
    public static void main(String[] args) {
        Solution solution = new T179_LargestNumber().new Solution();

        // 测试用例 1
        int[] nums1 = {10, 2};
        // 输出 "210"
        System.out.println(solution.largestNumber(nums1));

        // 测试用例 2
        int[] nums2 = {3, 30, 34, 5, 9};
        // 输出 "9534330"
        System.out.println(solution.largestNumber(nums2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String largestNumber(int[] nums) {
            // 将整数数组转为字符串数组
            String[] strs = new String[nums.length];
            for (int i = 0; i < nums.length; i++) {
                strs[i] = String.valueOf(nums[i]);
            }

            // 使用自定义比较器进行排序
            Arrays.sort(strs, (a, b) -> (b + a).compareTo(a + b));

            // 如果排序后的第一个元素是 "0"，则所有元素都是零，直接返回 "0"
            if (strs[0].equals("0")) {
                return "0";
            }

            // 拼接排序后的结果
            StringBuilder sb = new StringBuilder();
            for (String str : strs) {
                sb.append(str);
            }
            return sb.toString();
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)
}