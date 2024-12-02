package leetcode.editor.cn;

// Java: 搜索二维矩阵

public class T74_SearchA2dMatrix {
    public static void main(String[] args) {
        Solution solution = new T74_SearchA2dMatrix().new Solution();
        // TO TEST
        System.out.println(solution.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int lef = 0,rig = matrix.length * matrix[0].length - 1;
        while (lef <= rig) {
            int mid = lef + (rig - lef) / 2;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] == target)
                return true;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] < target)
                lef = mid + 1;
            else rig = mid - 1;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
