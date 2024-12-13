package leetcode.editor.cn;

// Java: 二叉树的层序遍历 II

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class T107_BinaryTreeLevelOrderTraversalIi {
    public static void main(String[] args) {
        Solution solution = new T107_BinaryTreeLevelOrderTraversalIi().new Solution();
        // TO TEST

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

    public class TreeNode {
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
        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            // 如果根节点为空 直接返回空列表
            if(root == null){
                return new ArrayList<>();
            }

            // 存储层序遍历的结果
            List<List<Integer>> result = new ArrayList<>();

            // 使用队列来进行广度优先遍历
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> currentLevel = new ArrayList<>();

                // 遍历当前层的所有节点
                for (int i = 0; i < levelSize; i++) {
                    TreeNode currentNode = queue.poll();
                    currentLevel.add(currentNode.val);

                    // 如果当前节点有左子节点，加入队列
                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    // 如果当前节点有右子节点，加入队列
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }
                // 当前层遍历完后，将这一层的节点值插入到结果的最前面（实现自底向上）
                result.add(0, currentLevel);
            }

            return result;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
