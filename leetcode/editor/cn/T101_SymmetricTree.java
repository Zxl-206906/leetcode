package leetcode.editor.cn;

// Java: 对称二叉树

public class T101_SymmetricTree {
    public static void main(String[] args) {
        Solution solution = new T101_SymmetricTree().new Solution();
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
        public boolean isSymmetric(TreeNode root) {
            if (root == null) {
                return true;
            }
            return isMirror(root.left, root.right);
        }


        private boolean isMirror(TreeNode t1, TreeNode t2){
            // 如果两个节点都为空，则返回true
            if (t1 == null && t2 == null) {
                return true;
            }
            // 如果一个节点为空而另一个不为空，则返回false
            if (t1 == null || t2 == null) {
                return false;
            }
            return (t1.val == t2.val) && isMirror(t1.right, t2.left) && isMirror(t1.left, t2.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
