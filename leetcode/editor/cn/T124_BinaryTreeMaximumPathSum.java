package leetcode.editor.cn;

// Java: 二叉树中的最大路径和

public class T124_BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        Solution solution = new T124_BinaryTreeMaximumPathSum().new Solution();
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
        int maxSum = Integer.MIN_VALUE; // 全局变量，存储最大路径和
        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }


        // 计算节点的最大贡献值
        public int maxGain(TreeNode node) {

            if (node == null) {
                return 0; // 空节点贡献值为 0
            }

            // 递归计算左子树和右子树的最大贡献值
            int leftGain = Math.max(maxGain(node.left), 0);
            int rightGain = Math.max(maxGain(node.right), 0);

            // 当前节点的最大路径和
            int currentPathSum = node.val + leftGain + rightGain;

            // 更新全局最大路径和
            maxSum = Math.max(maxSum, currentPathSum);

            // 返回节点的最大贡献值
            return node.val + Math.max(leftGain, rightGain);
        }
//leetcode submit region end(Prohibit modification and deletion)
    }
}
