package leetcode.editor.cn;

// Java: 验证二叉搜索树

public class T98_ValidateBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new T98_ValidateBinarySearchTree().new Solution();
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
        // 主方法调用辅助方法并以最大范围作为开始
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        // 辅助递归方法
        public boolean isValidBST(TreeNode root, long lower, long upper) {
            // 如果到达叶子节点下的空位置，则此路径上的所有节点都满足BST条件
            if (root == null) {
                return true;
            }
            // 如果当前节点的值不在(lower, upper)的范围内，则不是BST
            if (root.val <= lower || root.val >= upper) {
                return false;
            }
            // 对左子树进行检查，更新上限为当前节点的值
            // 对右子树进行检查，更新下限为当前节点的值
            // 只有左右子树都是BST时，整棵树才是BST
            return isValidBST(root.left, lower, root.val) && isValidBST(root.right, root.val, upper);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
