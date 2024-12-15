package leetcode.editor.cn;

// Java: 买卖股票的最佳时机

public class T121_BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        Solution solution = new T121_BestTimeToBuyAndSellStock().new Solution();
        // TO TEST
        System.out.println(solution.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 初始化为最大值
        int maxProfit = 0; // 初始化为0

        for (int price : prices) {
            if (price < minPrice) {
                // 更新最低价格
                minPrice = price;
            } else {
                // 计算当前价格卖出的利润并更新最大利润
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
