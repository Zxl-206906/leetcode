package leetcode.editor.cn;

// Java: 不同的二叉搜索树 II

import java.util.LinkedList;
import java.util.List;

public class T95_UniqueBinarySearchTreesIi {
    public static void main(String[] args) {
        Solution solution = new T95_UniqueBinarySearchTreesIi().new Solution();
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

        // 主方法，接收一个整数n作为参数
        public List<TreeNode> generateTrees(int n) {
            // 当n为0时，没有可生成的树，直接返回一个空的列表
            if (n == 0) {
                return new LinkedList<TreeNode>();
            }
            // 调用辅助方法generateTrees，生成从1到n的所有可能的二叉搜索树
            return generateTrees(1, n);
        }

        // 辅助方法，接收两个整数作为参数，表示生成树的值的范围
        public List<TreeNode> generateTrees(int start, int end) {
            List<TreeNode> trees = new LinkedList<TreeNode>(); // 用于存储生成的所有树的列表

            // 当开始值大于结束值时，表示该区间无有效数字，返回一个包含null的列表
            if (start > end) {
                trees.add(null);
                return trees;
            }

            // 遍历从start到end的所有数字，将每一个数字作为根节点
            for (int i = start; i <= end; i++) {
                // 生成所有可能的左子树
                List<TreeNode> leftTrees = generateTrees(start, i - 1);
                // 生成所有可能的右子树
                List<TreeNode> rightTrees = generateTrees(i + 1, end);

                // 对于每一颗左子树和右子树的组合，创建一个新的树
                for (TreeNode left : leftTrees) {
                    for (TreeNode right : rightTrees) {
                        TreeNode newTree = new TreeNode(i); // 创建一个新的树的根节点
                        newTree.left = left;  // 将当前左子树连接到新树的左侧
                        newTree.right = right; // 将当前右子树连接到新树的右侧
                        trees.add(newTree); // 将新生成的树添加到trees列表中
                    }
                }
            }
            return trees; // 返回生成的所有树的列表
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
