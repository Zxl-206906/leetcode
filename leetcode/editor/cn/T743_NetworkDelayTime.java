package leetcode.editor.cn;

// Java: 网络延迟时间
// https://leetcode.cn/problems/network-delay-time/solutions/2999783/743wang-luo-yan-chi-shi-jian-dijkstratu-005z7/

import java.util.*;

public class T743_NetworkDelayTime {
    public static void main(String[] args) {
        Solution solution = new T743_NetworkDelayTime().new Solution();
        // TO TEST
        System.out.println(solution.networkDelayTime(new int[][]{{2, 1, 1}, {2, 3, 1}, {3, 4, 1}}, 4, 2));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: 构建邻接表
        Map<Integer, List<int[]>> graph = new HashMap<>();
        //使用for循环进行遍历times数组，构建邻接表
        for(int[] time : times){
            graph.computeIfAbsent(time[0], l -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }

        // Step 2: 初始化距离数组和优先队列
        //节点编号从1开始
        int[] dist = new int[n + 1];
        // 初始距离设为无穷大
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 起点距离设为 0
        dist[k] = 0;


        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        heap.offer(new int[]{0, k}); // [当前距离, 当前节点]

        // Step 3: Dijkstra 算法核心逻辑
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            int currentDist = current[0]; // 当前路径长度
            int currentNode = current[1]; // 当前节点

            // 如果当前路径已经不是最短，跳过
            if (currentDist > dist[currentNode]) continue;

            // 遍历当前节点的所有邻居
            if (graph.containsKey(currentNode)) {
                for (int[] neighbor : graph.get(currentNode)) {
                    int nextNode = neighbor[0]; // 邻居节点
                    int time = neighbor[1]; // 到邻居的权重

                    // 如果找到更短路径，更新距离并加入堆
                    if (dist[currentNode] + time < dist[nextNode]) {
                        dist[nextNode] = dist[currentNode] + time;
                        heap.offer(new int[]{dist[nextNode], nextNode});
                    }
                }
            }
        }



        // Step 4: 检查结果
        int maxDelay = Arrays.stream(dist).skip(1).max().getAsInt(); // 找到最大延迟时间
        return maxDelay == Integer.MAX_VALUE ? -1 : maxDelay;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
