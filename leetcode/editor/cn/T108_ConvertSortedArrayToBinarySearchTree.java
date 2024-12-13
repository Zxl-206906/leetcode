package leetcode.editor.cn;

// Java: 将有序数组转换为二叉搜索树

public class T108_ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new T108_ConvertSortedArrayToBinarySearchTree().new Solution();
        // TO TEST
        System.out.println(solution.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9}));
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
        public TreeNode sortedArrayToBST(int[] nums) {
            return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
        }

        private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
            // 递归终止条件
            if (left > right) {
                return null;
            }

            // 取中间元素作为根节点
            int mid = left + (right - left) / 2;
            TreeNode root = new TreeNode(nums[mid]);

            // 递归构建左右子树
            root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
            root.right = sortedArrayToBSTHelper(nums, mid + 1, right);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
