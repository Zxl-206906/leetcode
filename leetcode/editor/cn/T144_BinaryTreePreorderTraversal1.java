package leetcode.editor.cn;

// Java: 二叉树的前序遍历

import java.util.ArrayList;
import java.util.List;

public class T144_BinaryTreePreorderTraversal1 {
    public static void main(String[] args) {
        Solution solution = new T144_BinaryTreePreorderTraversal1().new Solution();
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
        public List<Integer> preorderTraversal(TreeNode root) {
            // 创建一个存储结果的链表
            List<Integer> result = new ArrayList<>();
            preorderHelper(root, result);
            return result;
        }

        private void preorderHelper(TreeNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            result.add(node.val);  // 先访问根节点
            preorderHelper(node.left, result);  // 遍历左子树
            preorderHelper(node.right, result); // 遍历右子树
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
