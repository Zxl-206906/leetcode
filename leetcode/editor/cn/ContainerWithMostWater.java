//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。 
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 返回容器可以储存的最大水量。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 双指针 👍 5177 👎 0

 
package leetcode.editor.cn;

public class ContainerWithMostWater{
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater().new Solution();
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        //定义左右挡板的距离 i和j
        int i = 0;
        int j = height.length - 1;
        //定义一个最大的容量为max，初始值为1
        int max = 0;
        int area = 0;
        //while循环 当i和j没有碰头的的时候一直进行循环
        while (i < j){
           //接下来我们就看挡板高度然后看应该移动谁
            if (height[i] < height[j]){
                //我们还需要计算出来容器的最大容量   距离为j-i 高度取决于height[i] 和 height[j]更短的一个挡板
                area = height[i] * (j - i);
                max = Math.max(max, area);
                i++;
            }else{
                area = (j - i) * height[j];
                max = Math.max(max, area);
                j--;
            }
        }
        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}