package leetcode.editor.cn;

// Java: 最长连续序列

import java.util.HashSet;

public class T128_LongestConsecutiveSequence {
    public static void main(String[] args) {
        Solution solution = new T128_LongestConsecutiveSequence().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestConsecutive(int[] nums) {

            if (nums == null || nums.length == 0) {
                return 0;
            }

            // 使用 HashSet 存储所有元素
            HashSet<Integer> set = new HashSet<>();

            //使用for循环将所有元素进行存储里面
            for (int num : nums) {
                set.add(num);
            }

            int longest = 0;

            // 遍历每个元素，寻找它的连续序列
            for (int num : set) {
                // 如果 num - 1 不在 set 中，说明 num 是一个序列的起点
                if (!set.contains(num - 1)) {
                    int currentNum = num;
                    int currentLength = 1;

                    // 扩展序列，找到最长的连续子序列
                    while (set.contains(currentNum + 1)) {
                        currentNum++;
                        currentLength++;
                    }

                    // 更新最长序列的长度
                    longest = Math.max(longest, currentLength);
                }

            }

            return longest;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
