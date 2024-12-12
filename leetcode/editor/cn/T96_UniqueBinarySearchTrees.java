package leetcode.editor.cn;

// Java: 不同的二叉搜索树

public class T96_UniqueBinarySearchTrees {
    public static void main(String[] args) {
        Solution solution = new T96_UniqueBinarySearchTrees().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        // 方法 numTrees 接收一个整数 n，并返回能够由 n 个不同节点组成的二叉搜索树的数量。
        public int numTrees(int n) {
            // 如果 n 为 0，按照定义，空树也是一种有效的二叉搜索树。
            if (n == 0) {
                return 1;
            }
            // 初始化结果变量 res，用于累加所有可能的二叉搜索树的数量。
            int res = 0;

            // 遍历每个数字 i，将其作为根节点，以计算所有可能的二叉搜索树。
            for(int i = 1; i <= n; i++) {
                // 递归计算左子树的数量，左子树包含比根节点小的 i-1 个节点。
                int left = numTrees(i - 1);
                // 递归计算右子树的数量，右子树包含比根节点大的 n-i 个节点。
                int right = numTrees(n - i);

                // 将左子树和右子树的组合数量相乘，并加到总数 res 上。
                // 这是基于卡特兰数的数学性质，每个左子树都可以与任何一个右子树组合形成唯一的二叉搜索树。
                res += left * right;
            }
            // 返回计算得出的二叉搜索树的总数量。
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
