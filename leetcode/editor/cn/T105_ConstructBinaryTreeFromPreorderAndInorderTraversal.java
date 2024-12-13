package leetcode.editor.cn;

// Java: 从前序与中序遍历序列构造二叉树

import java.util.HashMap;
import java.util.Map;

public class T105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        Solution solution = new T105_ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
        // TO TEST
        System.out.println(solution.buildTree(new int[]{3, 9, 20, 15, 7}, new int[]{9, 3, 15, 20, 7}));
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
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            // TODO 使用哈希表存储中序遍历的值和索引
            Map<Integer, Integer> inOrderMap = new HashMap<>();

            // 遍历中序遍历数组，存储每个节点的值和对应的索引
            for (int i = 0; i < inorder.length; i++) {
                inOrderMap.put(inorder[i], i);
            }

            return buildSubTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderMap);
        }

        private TreeNode buildSubTree(int[] preorder, int preStart, int preEnd,
                                      int[] inorder, int inStart, int inEnd,
                                      Map<Integer, Integer> inorderMap) {
            // 递归边界
            if (preStart > preEnd || inStart > inEnd) {
                return null;
            }

            // 根节点的值是前序遍历的第一个值
            int rootVal = preorder[preStart];
            TreeNode root = new TreeNode(rootVal);

            // 根节点在中序遍历中的位置
            int rootIndex = inorderMap.get(rootVal);

            // 左子树的节点数
            int leftSize = rootIndex - inStart;

            // 构建左子树
            root.left = buildSubTree(preorder, preStart + 1, preStart + leftSize,
                    inorder, inStart, rootIndex - 1, inorderMap);

            // 构建右子树
            root.right = buildSubTree(preorder, preStart + leftSize + 1, preEnd,
                    inorder, rootIndex + 1, inEnd, inorderMap);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
