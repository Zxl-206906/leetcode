package leetcode.editor.cn;

// Java: 比较版本号

public class T165_CompareVersionNumbers {
    public static void main(String[] args) {
        Solution solution = new T165_CompareVersionNumbers().new Solution();
        // TO TEST
        // 输出: -1
        System.out.println(solution.compareVersion("1.2", "1.10"));
        // 输出: 0
        System.out.println(solution.compareVersion("1.01", "1.001"));
        // 输出: 0
        System.out.println(solution.compareVersion("1.0", "1.0.0.0"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int compareVersion(String version1, String version2) {
        // 使用 . 分割版本号
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");

        // 计算最大长度，方便补充缺失的修订号
        int maxLength = Math.max(v1.length, v2.length);

        for (int i = 0; i < maxLength; i++) {
            // 如果 v1 的修订号超出范围，用 0 填充
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            // 如果 v2 的修订号超出范围，用 0 填充
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;

            // 比较两个修订号
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        // 所有修订号都相同
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
