package leetcode.editor.cn;

// Java: Excel 表列序号

public class T171_ExcelSheetColumnNumber {
    public static void main(String[] args) {
        Solution solution = new T171_ExcelSheetColumnNumber().new Solution();
        // TO TEST
        // 测试示例
        System.out.println(solution.titleToNumber("A"));  // 输出: 1
        System.out.println(solution.titleToNumber("AB")); // 输出: 28
        System.out.println(solution.titleToNumber("ZY")); // 输出: 701
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;

        for (int i = 0; i < columnTitle.length(); i++) {
            // 获取当前字符的数字值（'A' -> 1, 'B' -> 2, ..., 'Z' -> 26）
            int currentValue = columnTitle.charAt(i) - 'A' + 1;

            // 更新结果：乘以 26 是因为每一位的权重是 26 的幂次
            result = result * 26 + currentValue;
        }

        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
