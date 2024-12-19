package leetcode.editor.cn;

// Java: 加油站

public class T134_GasStation {
    public static void main(String[] args) {
        Solution solution = new T134_GasStation().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0; // 总的油量差
        int currentTank = 0; // 当前从起点到某一站的油量差
        int startingStation = 0; // 起始加油站

        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            totalTank += netGas;
            currentTank += netGas;

            // 如果当前油箱的油量小于 0，说明无法到达下一个加油站
            if (currentTank < 0) {
                startingStation = i + 1; // 更新起始加油站
                currentTank = 0; // 重置当前油量
            }
        }

        // 如果总油量差小于 0，说明不可能完成环路
        return totalTank >= 0 ? startingStation : -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
