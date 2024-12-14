package leetcode.editor.cn;

// Java: 路径总和

public class T112_PathSum {
    public static void main(String[] args) {
        Solution solution = new T112_PathSum().new Solution();
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
        public boolean hasPathSum(TreeNode root, int targetSum) {
            // 如果节点为空，返回 false
            if (root == null) {
                return false;
            }

            // 如果是叶子节点，检查路径和是否等于目标和
            if (root.left == null && root.right == null) {
                return targetSum == root.val;
            }

            // 递归检查左子树和右子树
            return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
