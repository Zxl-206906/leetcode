package leetcode.editor.cn;

// Java: x 的平方根 

public class T69_Sqrtx {
    public static void main(String[] args) {
        Solution solution = new T69_Sqrtx().new Solution();
        // TO TEST
        System.out.println(solution.mySqrt(8));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int mySqrt(int x){
        if(x == 0 || x == 1)
            return x;
        double res = x;
        double lastVal = 0;
        do{
            lastVal = res;
            res = res / 2.0 + x / 2.0 / res;
        }while(Math.abs(res - lastVal) > 1e-7);
        return (int)res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
