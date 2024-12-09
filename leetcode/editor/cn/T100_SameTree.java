package leetcode.editor.cn;

// Java: 相同的树

import javax.swing.tree.TreeNode;

public class T100_SameTree {
    public static void main(String[] args) {
        Solution solution = new T100_SameTree().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
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
        // 方法用于比较两个二叉树 p 和 q 是否完全相同
        public boolean isSameTree(TreeNode p, TreeNode q) {
            // 如果两个树都是空的，则它们相同
            if (p == null && q == null) {
                return true;
            }
            // 如果一个树为空而另一个不为空，则它们不相同
            if (p == null || q == null) {
                return false;
            }
            // 检查当前节点的值是否相等，并递归地检查左右子树
            // 只有当前节点值相等且对应的左子树和右子树也都相等时，才认为整个树相等
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

//leetcode submit region end(Prohibit modification and deletion)
}
