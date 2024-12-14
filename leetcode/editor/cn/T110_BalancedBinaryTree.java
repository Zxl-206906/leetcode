package leetcode.editor.cn;

// Java: 平衡二叉树

public class T110_BalancedBinaryTree {
    public static void main(String[] args) {
        Solution solution = new T110_BalancedBinaryTree().new Solution();
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
        public boolean isBalanced(TreeNode root) {
            // 递归
            return height(root) != -1;
        }

        private int height(TreeNode root) {
            // 递归 边界条件
            if (root == null) {
                return 0;
            }

            //递归计算左子树的高度
            int leftHeight = height(root.left);

            // 左子树不平衡
            if (leftHeight == -1) {
                return -1;
            }

            int rightHeight = height(root.right);

            // 右子树不平衡
            if (rightHeight == -1) {
                return -1;
            }

            //检查当前节点的平衡性
            if (Math.abs(leftHeight - rightHeight) > 1) {
                return -1;
            }

            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
