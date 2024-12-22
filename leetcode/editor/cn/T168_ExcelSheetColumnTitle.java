package leetcode.editor.cn;

// Java: Excel 表列名称

public class T168_ExcelSheetColumnTitle {
    public static void main(String[] args) {
        Solution solution = new T168_ExcelSheetColumnTitle().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToTitle(int columnNumber) {

        StringBuilder result = new StringBuilder();

        //不断处理列编号， 知道columNumber为0
        while (columnNumber > 0) {
            //将余数对应的字母加入到结果中
            result.append((char) ('A' + (columnNumber - 1) % 26));
            //将余数除以26，得到新的余数
            columnNumber = (columnNumber - 1) / 26;
        }

        //将结果反转，得到最终的结果
        return result.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
