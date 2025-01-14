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

public class ContainerWithMostWater2 {
    public static void main(String[] args) {
        Solution solution = new ContainerWithMostWater2().new Solution();
        System.out.println(solution.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxArea(int[] height) {
        //初始化结果变量 用来存储最大的水容量
        int res = 0;
        //外层循环遍历所有可能的左边界
        for(int i=0;i<height.length;i++){
            for(int j=i+1;j<height.length;j++){
                //计算当前两个边界能形成的容器的水容量，并且更新最大值
                //（j-i）是宽度，Math.min(height[i], height[j]) 是高度
                res = Math.max(res,Math.min(height[i],height[j])*(j-i));
            }
        }
        //返回计算得到的最大水容量
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}