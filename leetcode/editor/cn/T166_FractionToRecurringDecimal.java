package leetcode.editor.cn;

// Java: 分数到小数

import java.util.HashMap;
import java.util.Map;

public class T166_FractionToRecurringDecimal {
    public static void main(String[] args) {
        Solution solution = new T166_FractionToRecurringDecimal().new Solution();
        // TO TEST
        System.out.println(solution.fractionToDecimal(1, 2));
        System.out.println(solution.fractionToDecimal(2, 1));
        System.out.println(solution.fractionToDecimal(4, 333));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {

        //如果分母为0 直接返回0
        if(denominator == 0 || numerator == 0) {
            return "0";
        }

        //判断结果是否为负数
        StringBuilder result = new StringBuilder();

        if (numerator < 0 ^ denominator < 0){
            result.append("-");
        }

        long num = Math.abs((long)numerator);
        long denom = Math.abs((long)denominator);

        result.append(num / denom);

        //计算余数部分
        long remainder = num % denom;
        if (remainder == 0) {
            return result.toString();
        }

        result.append(".");

        // 记录余数及其对应的小数位置
        HashMap<Long, Integer> map = new HashMap<>();
        if (remainder != 0){
            while (remainder != 0){
                //如果余数已经出现过，说明存在循环节
                if (map.containsKey(remainder)){
                    result.insert(map.get(remainder), "(");
                    result.append(")");
                    break;
                }
                //记录余数出现的位置
                map.put(remainder, result.length());

                //计算余数
                remainder *= 10;
                result.append(remainder / denom);
                remainder %= denom;
            }
        }
        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
