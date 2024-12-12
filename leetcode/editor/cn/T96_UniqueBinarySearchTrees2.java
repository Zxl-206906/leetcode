package leetcode.editor.cn;

// Java: 不同的二叉搜索树

public class T96_UniqueBinarySearchTrees2 {
    public static void main(String[] args) {
        Solution solution = new T96_UniqueBinarySearchTrees2().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 缓存数组，用于存储已经计算过的结果
    public int[] nums = new int[20];

    // 主方法，计算并返回 n 个节点组成的唯一二叉搜索树的数量
    public int numTrees(int n) {
        // 基本情况：如果 n 是 0，则有一个空的二叉树
        if (n == 0) {
            return 1;
        }
        // 如果之前已经计算过这个结果，直接返回缓存中的值
        if (nums[n] != 0) {
            return nums[n];
        }

        // 初始化结果变量 res
        int res = 0;

        // 遍历每个数字 i，考虑将其作为根节点
        for(int i = 1; i <= n; i++) {
            // 尝试从缓存中获取左子树的数量
            int left = nums[i - 1];
            // 如果缓存中没有，则递归计算并更新缓存
            if (left == 0) {
                nums[i - 1] = numTrees(i - 1);
                left = nums[i - 1];
            }

            // 尝试从缓存中获取右子树的数量
            int right = nums[n - i];
            // 如果缓存中没有，则递归计算并更新缓存
            if (right == 0) {
                nums[n - i] = numTrees(n - i);
                right = nums[n - i];
            }

            // 累加左子树和右子树的组合数量
            res += left * right;
        }

        // 缓存这个结果，以便将来可以重用
        nums[n] = res;

        // 返回计算得出的二叉搜索树的总数量
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
