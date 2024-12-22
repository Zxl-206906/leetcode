package leetcode.editor.cn;

// Java: 最大间距

import java.util.Arrays;

public class T164_MaximumGap {
    public static void main(String[] args) {
        Solution solution = new T164_MaximumGap().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumGap(int[] nums) {
        //如果数组长度小于2 则直接返回0
        if (nums == null || nums.length < 2 ){
            return 0;
        }

        int n = nums.length;
        int minNum = Arrays.stream(nums).min().getAsInt();
        int maxNum = Arrays.stream(nums).max().getAsInt();

        //如果数组中华的所有元素相等 则直接返回0
        if (minNum == maxNum){
            return 0;
        }

        //计算桶的大小和数量
        int bucketSize = Math.max(1, (int) Math.ceil((maxNum - minNum) / (n - 1)));
        int bucketCount = (maxNum - minNum) / bucketSize + 1;

        // 初始化桶，每个桶存储两个值：最小值和最大值
        int[] bucketMin = new int[bucketCount];
        int[] bucketMax = new int[bucketCount];
        Arrays.fill(bucketMin, Integer.MAX_VALUE);
        Arrays.fill(bucketMax, Integer.MIN_VALUE);

        // 将每个元素放入对应的桶中
        for (int num : nums) {
            int bucketIndex = (num - minNum) / bucketSize;
            bucketMin[bucketIndex] = Math.min(bucketMin[bucketIndex], num);
            bucketMax[bucketIndex] = Math.max(bucketMax[bucketIndex], num);
        }

        // 遍历桶，计算最大间距
        int maxGap = 0;
        int previousMax = minNum; // 上一个非空桶的最大值
        for (int i = 0; i < bucketCount; i++) {
            // 跳过空桶
            if (bucketMin[i] == Integer.MAX_VALUE && bucketMax[i] == Integer.MIN_VALUE) {
                continue;
            }
            // 当前桶的最小值与前一个非空桶的最大值之间的差值
            maxGap = Math.max(maxGap, bucketMin[i] - previousMax);
            // 更新前一个非空桶的最大值
            previousMax = bucketMax[i];
        }

        return maxGap;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
