package leetcode.editor.cn;

//Java：罗马数字转整数

import java.util.HashMap;
import java.util.Map;

public class T13_RomanToInteger {
    public static void main(String[] args) {
        Solution solution = new T13_RomanToInteger().new Solution();
        // TO TEST
        System.out.println(solution.romanToInt("III"));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int romanToInt(String s) {
            // 创建一个哈希表存储罗马数字和对应的整数值
            Map<Character, Integer> romanMap = new HashMap<>();
            romanMap.put('I', 1);
            romanMap.put('V', 5);
            romanMap.put('X', 10);
            romanMap.put('L', 50);
            romanMap.put('C', 100);
            romanMap.put('D', 500);
            romanMap.put('M', 1000);
            int sum = 0;
            int prevValue = 0;
            // 从后向前遍历罗马数字字符串
            for (int i = s.length() - 1; i >= 0; i--) {
                int currValue = romanMap.get(s.charAt(i));
                // 如果当前值小于之前的值，则减去当前值；否则，加上当前值
                if (currValue < prevValue) {
                    sum -= currValue;
                } else {
                    sum += currValue;
                }
                prevValue = currValue;
            }
            return sum;
        }
    }
    /* leetcode submit region end(Prohibit modification and deletion) */

}
