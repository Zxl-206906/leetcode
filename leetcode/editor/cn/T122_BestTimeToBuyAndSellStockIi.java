package leetcode.editor.cn;

// Java: 买卖股票的最佳时机 II

public class T122_BestTimeToBuyAndSellStockIi {
    public static void main(String[] args) {
        Solution solution = new T122_BestTimeToBuyAndSellStockIi().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0; // 总利润
        for (int i = 1; i < prices.length; i++) {
            // 如果今天价格比昨天高，累加利润
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
