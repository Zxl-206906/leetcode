
> Problem: [45. 跳跃游戏 II](https://leetcode.cn/problems/jump-game-ii/description/)

### 题意

给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 `nums[0]`。



每个元素 `nums[i]` 表示从索引 i 向前跳转的最大长度。换句话说，如果你在 `nums[i]` 处，你可以跳转到任意 `nums[i + j]` 处：



+ `0 <= j <= nums[i]`
+ `i + j < n`



返回到达 `nums[n - 1]` 的最小跳跃次数。生成的测试用例可以到达 `nums[n - 1]`。

### 原题意

给你一个非负整数数组 nums，你最初位于数组的第一个位置。



数组中的每个元素代表你在该位置可以跳跃的最大长度。



你的目标是使用最少的跳跃次数到达数组的最后一个位置。



假设你总是可以到达数组的最后一个位置。

> 我只能说原题意比现在的题意更容易懂，不知道 LeetCode 为什么要改。😄

### 难度

中等

### 示例

**示例 1:**

```plain
输入: nums = [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

**示例 2:**

```plain
输入: nums = [2,3,0,1,4]
输出: 2
```

提示：

```plain
1 <= nums.length <= 104
0 <= nums[i] <= 1000
```

### 分析

这道题的关键在于理解跳跃次数，和每次的跳跃步长。



假如你在第 0 个位置，第 0 个位置的数是 2（`num[0]=2`），那就意味着你可以跳到 1 的位置，也可以跳到 2 的位置。最长是 2，端是 1，对吧？

![image.png](https://pic.leetcode.cn/1732607521-illrPq-image.png)

由于 `num[1]=3`，`num[2]=1`，所以我们可以选择跳到 1 的位置，因为下一次我们可以跳的更远，比如说 `num[1+3]`，也就是 4 的位置，直接就跳到末尾了。

![image.png](https://pic.leetcode.cn/1732607566-iJEdjr-image.png)
![image.png](https://pic.leetcode.cn/1732607577-ZsAgEz-image.png)

但如果我们选择跳到 2 的位置，虽然这次跳的远，但下一次只能跳到 `num[2+1]`，也就是 3 的位置。还需要再跳一步才能到达末尾。



换句话说，我们每次跳的时候，不一定非要跳最远，但需要跳到下一次可以跳最远的地方。



能明白吧？



那针对这道题，我们可以直接使用贪心算法来完成，贪心的目的就是下一次跳的更远。

# Code
```Java []
class Solution {
    public int jump(int[] nums) {
        // 数组的长度
        int n = nums.length;
        // 结束位置
        int end = 0;
        // 最大位置
        int maxPosition = 0;
        // 跳跃次数
        int steps = 0;
        // 遍历数组
        for (int i = 0; i < n - 1; i++) {
            // 获取最大位置
            maxPosition = Math.max(maxPosition, i + nums[i]);
            // 如果当前位置等于结束位置
            if (i == end) {
                // 更新结束位置
                end = maxPosition;
                // 跳跃次数加 1
                steps++;
            }
        }
        return steps;
    }
}
```
  
