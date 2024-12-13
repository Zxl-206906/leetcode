package leetcode.editor.cn;

// Java: 二叉树的最大深度

import java.util.LinkedList;
import java.util.Queue;

public class T104_MaximumDepthOfBinaryTree2 {
    public static void main(String[] args) {
        Solution solution = new T104_MaximumDepthOfBinaryTree2().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        // 输出: 3
        System.out.println(solution.maxDepth(root));
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int maxDepth(TreeNode root) {

            // 递归边界条件
            if (root == null) {
                return 0;
            }

            // 广度优先搜索
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            int depth = 0;

            // 层序遍历
            while (!queue.isEmpty()) {
                // 获取当前层节点数
                int levelSize = queue.size();
                depth++;
                // 遍历当前层
                for (int i = 0; i < levelSize; i++) {
                    // 出队
                    TreeNode currentNode = queue.poll();
                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }
            }
            return depth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
