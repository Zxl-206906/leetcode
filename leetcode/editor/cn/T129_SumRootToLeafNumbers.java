package leetcode.editor.cn;

// Java: 求根节点到叶节点数字之和

public class T129_SumRootToLeafNumbers {
    public static void main(String[] args) {
        Solution solution = new T129_SumRootToLeafNumbers().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    /*
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
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
        public int sumNumbers(TreeNode root) {
            return dfs(root, 0);
        }

        private int dfs(TreeNode node, int currentSum) {

            //边界条件 如果节点为空 则直接返回0
            if (node == null) {
                return 0;
            }

            //使用dfs遍历时，currentSum存储当前路径形成的数
            currentSum = currentSum * 10 + node.val;

            //如果是夜子姐带你，返回当前路径形成的数字
            if (node.left == null && node.right == null) {
                return currentSum;
            }

            //否则 递归计算左子树 和 右子树的路径和
            return dfs(node.left, currentSum) + dfs(node.right, currentSum);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
