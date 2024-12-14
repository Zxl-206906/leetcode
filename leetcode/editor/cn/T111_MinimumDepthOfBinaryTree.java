package leetcode.editor.cn;

// Java: 二叉树的最小深度

public class T111_MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        Solution solution = new T111_MinimumDepthOfBinaryTree().new Solution();
        // TO TEST
//        System.out.println(solution.minDepth(null));
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
        public int minDepth(TreeNode root) {
            // 如果树为空，返回0
            if (root == null) {
                return 0;
            }

            // 如果左子树为空，递归右子树
            if (root.left == null) {
                return minDepth(root.right) + 1;
            }

            // 如果右子树为空，递归左子树
            if (root.right == null) {
                return minDepth(root.left) + 1;
            }

            // 左右子树都不为空，返回左右子树中深度较小的那个，并+1
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
