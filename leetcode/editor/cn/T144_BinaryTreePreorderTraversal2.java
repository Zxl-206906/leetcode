package leetcode.editor.cn;

// Java: 二叉树的前序遍历

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class T144_BinaryTreePreorderTraversal2 {
    public static void main(String[] args) {
        Solution solution = new T144_BinaryTreePreorderTraversal2().new Solution();
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
            if (root == null) {
                return result;
            }

            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);

            while (!stack.isEmpty()) {
                TreeNode node = stack.pop();
                result.add(node.val);

                // 注意顺序：右子节点先入栈，左子节点后入栈
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }

            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
