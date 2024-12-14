package leetcode.editor.cn;

// Java: 二叉树展开为链表

public class T114_FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        Solution solution = new T114_FlattenBinaryTreeToLinkedList().new Solution();
        // TO TEST

//        TreeNode root = new TreeNode(1);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(5);
//        root.left.left = new TreeNode(3);
//        root.left.right = new TreeNode(4);
//        root.right.right = new TreeNode(6);
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
        public void flatten(TreeNode root) {
            if (root == null) {
                return;
            }
            // 递归展开左子树和右子树
            flatten(root.left);
            flatten(root.right);

            // 暂存右子树
            TreeNode rightSubtree = root.right;

            // 将左子树移到右子树位置
            root.right = root.left;
            root.left = null;

            // 将右子树连接到当前右子树的末尾
            TreeNode current = root;
            while (current.right != null) {
                current = current.right;
            }
            current.right = rightSubtree;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
