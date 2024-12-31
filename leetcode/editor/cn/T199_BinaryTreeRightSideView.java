package leetcode.editor.cn;

// Java: 二叉树的右视图

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class T199_BinaryTreeRightSideView {
    public static void main(String[] args) {
        Solution solution = new T199_BinaryTreeRightSideView().new Solution();
        // TO TEST

    }

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
    class Solution {
        public List<Integer> rightSideView(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) {
                return result;
            }

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();

                // 遍历当前层的所有节点
                for (int i = 0; i < size; i++) {
                    TreeNode current = queue.poll();

                    // 如果是当前层的最后一个节点，记录下来
                    if (i == size - 1) {
                        result.add(current.val);
                    }

                    // 先加入右子树，再加入左子树
                    if (current.right != null) {
                        queue.offer(current.right);
                    }
                    if (current.left != null) {
                        queue.offer(current.left);
                    }
                }
            }

            return result;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
