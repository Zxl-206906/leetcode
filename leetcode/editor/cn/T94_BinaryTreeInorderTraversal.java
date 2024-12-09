package leetcode.editor.cn;

// Java: 二叉树的中序遍历

import java.util.ArrayList;
import java.util.List;

public class T94_BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new T94_BinaryTreeInorderTraversal().new Solution();
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
    //TODO 使用中序遍历实现
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> ans = new ArrayList<>();

            getAns(root, ans);

            return ans;
        }

        public void getAns(TreeNode root, List<Integer> ans) {
            if (root == null) {
                return;
            }

            getAns(root.left, ans);
            ans.add(root.val);
            getAns(root.right, ans);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
