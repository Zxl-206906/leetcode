package leetcode.editor.cn;

// Java: 计数质数

public class T204_CountPrimes {
    public static void main(String[] args) {
        Solution solution = new T204_CountPrimes().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int countPrimes(int n) {

            // 0 和 1 不是质数，2 也是最小质数
            if (n <= 2) {
                return 0;
            }

            boolean[] isPrime = new boolean[n];
            // 默认所有数都认为是质数
            for (int i = 2; i < n; i++) {
                isPrime[i] = true;
            }

            // 从 2 开始进行筛选
            for (int i = 2; i * i < n; i++) {
                if (isPrime[i]) {
                    // 将 i 的所有倍数标记为非质数
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = false;
                    }
                }
            }

            // 统计质数的数量
            int count = 0;
            for (int i = 2; i < n; i++) {
                if (isPrime[i]) {
                    count++;
                }
            }

            return count;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
