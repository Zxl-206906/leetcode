package leetcode.editor.cn;

// Java: 二叉树的层序遍历

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class T102_BinaryTreeLevelOrderTraversal {
    public void main(String[] args) {
        Solution solution = new T102_BinaryTreeLevelOrderTraversal().new Solution();
        // TO TEST
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        solution.levelOrder(root);
        System.out.println(solution.levelOrder(root));
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
        public List<List<Integer>> levelOrder(TreeNode root) {
            // TODO
            List<List<Integer>> result = new ArrayList<>();
            // 边界条件
            if (root == null) {
                return result;
            }

            //创建一个队列
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int levelSize = queue.size();
                List<Integer> currentLevel = new ArrayList<>();

                for (int i = 0; i < levelSize; i++) {
                    TreeNode currentNode = queue.poll();
                    currentLevel.add(currentNode.val);

                    if (currentNode.left != null) {
                        queue.offer(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.offer(currentNode.right);
                    }
                }

                result.add(currentLevel);
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
