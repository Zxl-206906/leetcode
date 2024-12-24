package leetcode.editor.cn;

// Java: 吃苹果的最大数目

import java.util.PriorityQueue;

public class T1705_MaximumNumberOfEatenApples {
    public static void main(String[] args) {
        Solution solution = new T1705_MaximumNumberOfEatenApples().new Solution();
        // TO TEST
        // 示例 1
        int[] apples1 = {1, 2, 3, 5, 2};
        int[] days1 = {3, 2, 1, 4, 2};
        // 输出 7
        System.out.println(solution.eatenApples(apples1, days1));

        // 示例 2
        int[] apples2 = {3, 0, 0, 0, 0, 2};
        int[] days2 = {3, 0, 0, 0, 0, 2};
        // 输出 5
        System.out.println(solution.eatenApples(apples2, days2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int eatenApples(int[] apples, int[] days) {
            int n = apples.length;
            int result = 0;  // 最终吃掉的苹果数量
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 小根堆，用于存储苹果的腐烂日期

            for (int i = 0; i < n || !pq.isEmpty(); i++) {
                // 每天检查是否有新的苹果
                if (i < n && apples[i] > 0) {
                    pq.offer(new int[]{i + days[i], apples[i]}); // 保存苹果的腐烂日期和数量
                }

                // 如果堆中有苹果且堆顶的苹果已腐烂，移除它
                while (!pq.isEmpty() && pq.peek()[0] <= i) {
                    pq.poll();
                }

                // 如果堆中有可食用的苹果，吃掉一个
                if (!pq.isEmpty()) {
                    int[] top = pq.poll(); // 获取堆顶的苹果
                    result++; // 吃掉一个苹果
                    top[1]--; // 该苹果数量减少
                    if (top[1] > 0) {
                        pq.offer(top); // 如果还有剩余的苹果，重新加入堆
                    }
                }
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
