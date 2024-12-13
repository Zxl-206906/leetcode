package leetcode.editor.cn;

// Java: 从中序与后序遍历序列构造二叉树

import java.util.HashMap;
import java.util.Map;

public class T106_ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
        Solution solution = new T106_ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
        // TO TEST
        System.out.println(solution.buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{9, 15, 7, 20, 3}));
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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            // TODO 构造哈希表，快速定位中序遍历中的根节点位置
            // 构造哈希表，快速定位中序遍历中的根节点位置
            Map<Integer, Integer> inorderMap = new HashMap<>();
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }
            return buildSubTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inorderMap);
        }

        private TreeNode buildSubTree(int[] inorder, int inStart, int inEnd,
                                      int[] postorder, int postStart, int postEnd,
                                      Map<Integer, Integer> inorderMap) {
            if (inStart > inEnd || postStart > postEnd) {
                return null;
            }

            // 获取根节点值（后序遍历的最后一个值）
            int rootVal = postorder[postEnd];
            TreeNode root = new TreeNode(rootVal);

            // 找到根节点在中序遍历中的位置
            int rootIndex = inorderMap.get(rootVal);

            // 计算左子树的节点数量
            int leftSize = rootIndex - inStart;

            // 递归构造左子树
            root.left = buildSubTree(inorder, inStart, rootIndex - 1,
                    postorder, postStart, postStart + leftSize - 1, inorderMap);

            // 递归构造右子树
            root.right = buildSubTree(inorder, rootIndex + 1, inEnd,
                    postorder, postStart + leftSize, postEnd - 1, inorderMap);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
