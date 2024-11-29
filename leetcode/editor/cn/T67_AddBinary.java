package leetcode.editor.cn;

// Java: 二进制求和

public class T67_AddBinary {
    public static void main(String[] args) {
        Solution solution = new T67_AddBinary().new Solution();
        // TO TEST
        System.out.println(solution.addBinary("11","1"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();
        int upper = 0;
        for(int i = a.length() - 1,j = b.length() - 1;i >= 0 || j >= 0;i--,j--){
            int nowPos = upper;
            nowPos += i >= 0 ? a.charAt(i) - '0' : 0;
            nowPos += j >= 0 ? b.charAt(j) - '0' : 0;
            upper = nowPos / 2;
            nowPos %= 2;
            res.append(nowPos);
        }
        if(upper != 0)
            res.append(upper);
        res.reverse();
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
