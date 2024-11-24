package leetcode.editor.cn;

// Java: 统计不是特殊数字的数字数量

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class T3233_FindTheCountOfNumbersWhichAreNotSpecial {
    public static void main(String[] args) {
        Solution solution = new T3233_FindTheCountOfNumbersWhichAreNotSpecial().new Solution();
        // TO TEST
        System.out.println(solution.nonSpecialCount(1, 15));
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nonSpecialCount(int l, int r) {
        //1 计算sqrt(r)
        int sqrtR = (int) Math.sqrt(r);

        //2 使用埃拉托色尼筛法找到所有质数
        boolean[] isPrime = new boolean[sqrtR + 1];

        //用来快速填充数组 将数组isPrime的所有元素设置为true
        //在埃拉托色尼筛法中，isPrime 数组用于标记每个数是否是质数：
        //初始化：默认假设所有数字都是质数，所以先全部填充为 true。
        //筛选：将不是质数的数逐步标记为 false。
        Arrays.fill(isPrime, true);

        // 0 不是质数
        isPrime[0] = false;
        // 1 不是质数
        isPrime[1] = false;

        for (int i = 2; i * i <= sqrtR; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= sqrtR; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        //3 生成特殊数字（质数的平方）
        List<Integer> specialNumbers = new ArrayList<>();

        for (int i = 2; i <= sqrtR; i++) {
            if (isPrime[i]) {
                int square = i * i;
                if (square >= l && square <= r) {
                    specialNumbers.add(square);
                }
            }
        }

        //4 统计结果 区间总数-特殊数字数量
        int totalNum = r - l + 1;
        int specialNumCount = specialNumbers.size();

        return totalNum - specialNumCount;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
