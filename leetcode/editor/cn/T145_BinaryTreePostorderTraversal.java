package leetcode.editor.cn;

// Java: 二叉树的后序遍历

import java.util.ArrayList;
import java.util.List;

public class T145_BinaryTreePostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new T145_BinaryTreePostorderTraversal().new Solution();
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
        public List<Integer> postorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            postorderHelper(root, result);
            return result;
        }

        private void postorderHelper(TreeNode node, List<Integer> result) {
            if (node == null) {
                return;
            }
            postorderHelper(node.left, result);  // 递归遍历左子树
            postorderHelper(node.right, result); // 递归遍历右子树
            result.add(node.val);  // 访问根节点
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
