package leetcode.editor.cn;

// Java: 杨辉三角

import java.util.ArrayList;
import java.util.List;

public class T118_PascalsTriangle {
    public static void main(String[] args) {
        Solution solution = new T118_PascalsTriangle().new Solution();
        // TO TEST
        System.out.println(solution.generate(5));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {

            // 创建一个二维列表，用于存储杨辉三角
            List<List<Integer>> triangle = new ArrayList<>();

            //第一行固定为【1】
            for (int i = 0; i < numRows; i++) {
                List<Integer> row = new ArrayList<>();
                //每一行的第一个和最后一个元素固定为【1】
                row.add(1);

                for (int j = 1; j < i; j++) {
                    //获取上一行的元素，然后相加
                    row.add(triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j));
                }

                //除第一行外，其他行最后一个元素固定为【1】
                if (i > 0) {
                    row.add(1);
                }

                triangle.add(row);
            }

            return triangle;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
