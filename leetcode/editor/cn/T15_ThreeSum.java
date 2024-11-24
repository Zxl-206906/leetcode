package leetcode.editor.cn;

// Java: 三数之和

import java.util.*;

public class T15_ThreeSum {
    public static void main(String[] args) {
        Solution solution = new T15_ThreeSum().new Solution();
        // TO TEST
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            Set<List<Integer>> res = new HashSet<>();
            // 遍历数组中的每个元素
            for (int i = 0; i < nums.length - 1; i++) {
                // 创建一个集合存储已经遍历过的数字，避免重复使用
                Map<Integer, Integer> map = new HashMap<>();
                for (int j = i + 1; j < nums.length; j++) {
                    int complement = -nums[i] - nums[j];
                    // 如果 complement 在集合中，说明找到了一组解
                    if (map.containsKey(complement)) {
                        // 将结果添加到集合中，这里使用数组并排序是为了避免重复
                        Integer[] temp = new Integer[]{nums[i], nums[j], complement};
                        Arrays.sort(temp);
                        res.add(Arrays.asList(temp));
                    }
                    // 将当前数字添加到集合中
                    map.put(nums[j], j);
                }
            }
            return new ArrayList<>(res);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
