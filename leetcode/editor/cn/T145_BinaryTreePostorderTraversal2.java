package leetcode.editor.cn;

// Java: 二叉树的后序遍历

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class T145_BinaryTreePostorderTraversal2 {
    public static void main(String[] args) {
        Solution solution = new T145_BinaryTreePostorderTraversal2().new Solution();
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
            if (root == null) {
                return result;
            }

            Stack<TreeNode> stack1 = new Stack<>();
            Stack<TreeNode> stack2 = new Stack<>();
            stack1.push(root);

            while (!stack1.isEmpty()) {
                TreeNode node = stack1.pop();
                stack2.push(node);

                // 将左右子节点压入stack1
                if (node.left != null) {
                    stack1.push(node.left);
                }
                if (node.right != null) {
                    stack1.push(node.right);
                }
            }

            // stack2中保存的是后序遍历的结果，按顺序弹出
            while (!stack2.isEmpty()) {
                result.add(stack2.pop().val);
            }

            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
