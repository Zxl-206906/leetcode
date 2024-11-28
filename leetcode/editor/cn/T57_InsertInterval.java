package leetcode.editor.cn;

// Java: 插入区间

import java.util.ArrayList;
import java.util.List;

public class T57_InsertInterval {
    public static void main(String[] args) {
        Solution solution = new T57_InsertInterval().new Solution();
        // TO TEST
        System.out.println(solution.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static int[][] insert(int[][] intervals, int[] newInterval) {
        // 初始化结果列表
        List<int[]> result = new ArrayList<>();

        // 初始化指针 i 和数组长度 n
        int i = 0, n = intervals.length;

        // 1. 将所有在 newInterval 前面的区间添加到结果中
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2. 合并所有与 newInterval 重叠的区间
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // 将合并后的 newInterval 加入结果
        result.add(newInterval);

        // 3. 将所有在 newInterval 后面的区间添加到结果中
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        // 转换成二维数组并返回
        return result.toArray(new int[result.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
