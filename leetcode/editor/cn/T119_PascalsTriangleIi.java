package leetcode.editor.cn;

// Java: 杨辉三角 II

import java.util.ArrayList;
import java.util.List;

public class T119_PascalsTriangleIi {
    public static void main(String[] args) {
        Solution solution = new T119_PascalsTriangleIi().new Solution();
        // TO TEST
        System.out.println(solution.getRow(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> getRow(int rowIndex) {

        // 定义一个二维数组
        List<Integer> row = new ArrayList<>();

        //用于存储组合数（rowIndex,0）
        long value = 1;

        for (int i = 0; i <= rowIndex; i++) {
            // 计算组合数
            row.add((int) value);

            // 更新组合数
            value = value * (rowIndex - i) / (i + 1);
         }
        // 返回结果
        return row;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
