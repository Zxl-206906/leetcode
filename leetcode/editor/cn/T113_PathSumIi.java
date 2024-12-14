package leetcode.editor.cn;

// Java: 路径总和 II

import java.util.ArrayList;
import java.util.List;

public class T113_PathSumIi {
    public static void main(String[] args) {
        Solution solution = new T113_PathSumIi().new Solution();
        // TO TEST

//        TreeNode root1 = new TreeNode(5);
//        root1.left = new TreeNode(4);
//        root1.right = new TreeNode(8);
//        root1.left.left = new TreeNode(11);
//        root1.left.left.left = new TreeNode(7);
//        root1.left.left.right = new TreeNode(2);
//        root1.right.left = new TreeNode(13);
//        root1.right.right = new TreeNode(4);
//        root1.right.right.left = new TreeNode(5);
//        root1.right.right.right = new TreeNode(1);
//        System.out.println(solution.pathSum(root1, 22));

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
        public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
            //创建一个存储路径的集合（结果）
            List<List<Integer>> result = new ArrayList<>();
            //创建一个存储当前路径的集合
            List<Integer> currentPath = new ArrayList<>();
            dfs(root, targetSum, currentPath, result);
            return result;
        }


        private void dfs(TreeNode root, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
            //递归终止条件
            if (root == null) {
                return;
            }

            // 将当前节点的值加入到当前路径中
            currentPath.add(root.val);

            // 如果当前节点是叶子节点且路径中的节点值之和等于目标值，则将当前路径加入到结果中
            if (root.left == null && root.right == null && targetSum == root.val) {
                result.add(new ArrayList<>(currentPath));
            }
            // 递归处理左子树和右子树
            dfs(root.left, targetSum - root.val, currentPath, result);
            dfs(root.right, targetSum - root.val, currentPath, result);
            // 回溯，将当前节点从当前路径中移除
            currentPath.remove(currentPath.size() - 1);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
