package leetcode.editor.cn;

//Java：整数转罗马数字

/**
 * @author Jerry
 */
public class T12_IntegerToRoman{
    public static void main(String[] args) {
        Solution solution = new T12_IntegerToRoman().new Solution();
        // TO TEST
        
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String intToRoman(int num) {
        // 定义两个数组，一个表示特定的罗马数字，一个表示对应的整数值
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        // 使用 StringBuilder 存储最终的罗马数字字符串
        StringBuilder sb = new StringBuilder();

        //遍历整数值数组
        for (int i = 0; i < values.length && num > 0; i++) {
            //当前数字还大于等于values[i]时，继续循环
            //这意味着我们可以从num中减取values[i],并添加对应的罗马数字到结果中
            while (num >= values[i]) {
                //从num中减去values[i]
                num -= values[i];
                //添加对应的罗马数字到结果字符串
                sb.append(symbols[i]);
            }
        }
        //将StringBuilder转化为字符串并返回
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
