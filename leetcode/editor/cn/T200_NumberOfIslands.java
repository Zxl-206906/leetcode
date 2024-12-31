package leetcode.editor.cn;

// Java: 岛屿数量

public class T200_NumberOfIslands {
    public static void main(String[] args) {
        Solution solution = new T200_NumberOfIslands().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;
            int islandCount = 0;

            // 遍历整个网格
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 如果当前是陆地，说明找到了一个岛屿
                    if (grid[i][j] == '1') {
                        // 使用 DFS 标记这个岛屿
                        dfs(grid, i, j, m, n);
                        islandCount++; // 找到一个岛屿，计数加 1
                    }
                }
            }

            return islandCount;
        }

        // DFS 遍历函数，将与当前陆地相连的部分标记为 '0'
        private void dfs(char[][] grid, int i, int j, int m, int n) {
            // 边界条件检查
            if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == '0') {
                return;
            }

            // 标记当前陆地为水（已访问）
            grid[i][j] = '0';

            // 递归调用四个方向
            dfs(grid, i + 1, j, m, n); // 下
            dfs(grid, i - 1, j, m, n); // 上
            dfs(grid, i, j + 1, m, n); // 右
            dfs(grid, i, j - 1, m, n); // 左
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
