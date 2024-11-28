package leetcode.editor.cn;

// Java: 合并区间

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class T56_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new T56_MergeIntervals().new Solution();
        // TO TEST
        System.out.println(solution.merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }

        // 1. 按照区间的起始位置排序  升序排列
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // 2. 创建一个链表来存储合并后的区间
        List<int[]> merged = new LinkedList<>();

        // 3. 遍历所有区间
        for (int[] interval : intervals) {
            // 如果 merged 为空或者当前区间与最后一个合并的区间不重叠，直接加入 merged
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            } else {
                // 如果重叠，合并区间，更新最后一个区间的 end
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }

        // 4. 将链表转回数组
        return merged.toArray(new int[merged.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
