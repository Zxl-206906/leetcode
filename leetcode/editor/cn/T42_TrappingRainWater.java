package leetcode.editor.cn;

// Java: 接雨水

public class T42_TrappingRainWater {
    public static void main(String[] args) {
        Solution solution = new T42_TrappingRainWater().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static int trap(int[] height) {
        if (height == null || height.length < 2) {
            return 0; // 少于两个柱子无法存储水
        }

        // 初始化双指针
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;

        // 双指针从两端向中间遍历
        while (left < right) {
            if (height[left] < height[right]) {
                // 检查左边的高度
                if (height[left] >= leftMax) {
                    leftMax = height[left]; // 更新左侧最高高度
                } else {
                    water += leftMax - height[left]; // 计算当前列的水量
                }
                left++; // 左指针右移
            } else {
                // 检查右边的高度
                if (height[right] >= rightMax) {
                    rightMax = height[right]; // 更新右侧最高高度
                } else {
                    water += rightMax - height[right]; // 计算当前列的水量
                }
                right--; // 右指针左移
            }
        }

        return water; // 返回总的雨水量
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
