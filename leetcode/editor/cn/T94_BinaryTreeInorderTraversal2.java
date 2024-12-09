package leetcode.editor.cn;

// Java: 二叉树的中序遍历

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class T94_BinaryTreeInorderTraversal2 {
    public static void main(String[] args) {
        Solution solution = new T94_BinaryTreeInorderTraversal2().new Solution();
        // TO TEST
//        System.out.println();
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
    //TODO 使用栈实现
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode cur = root;
            while (cur != null || !stack.isEmpty()) {
                //节点不为空一直压栈
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left; //考虑左子树
                }
                //节点为空，就出栈
                cur = stack.pop();
                //当前值加入
                ans.add(cur.val);
                //考虑右子树
                cur = cur.right;
            }
            return ans;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
