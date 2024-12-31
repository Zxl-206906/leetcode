<p>你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，<strong>如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警</strong>。</p>

<p>给定一个代表每个房屋存放金额的非负整数数组，计算你<strong> 不触动警报装置的情况下 </strong>，一夜之内能够偷窃到的最高金额。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>[1,2,3,1]
<strong>输出：</strong>4
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
&nbsp;    偷窃到的最高金额 = 1 + 3 = 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>[2,7,9,3,1]
<strong>输出：</strong>12
<strong>解释：</strong>偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
&nbsp;    偷窃到的最高金额 = 2 + 9 + 1 = 12 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 100</code></li> 
 <li><code>0 &lt;= nums[i] &lt;= 400</code></li> 
</ul>

<div><li>👍 3147</li><li>👎 0</li></div>

这个问题实际上是一个动态规划问题，可以通过建立一个状态转移方程来解决。我们定义一个 `dp` 数组，其中 `dp[i]` 表示偷窃到第 `i` 间房屋时，能够获得的最大金额。

### 关键思路：
1. 如果我们偷窃第 `i` 间房屋，那么我们就不能偷窃第 `i-1` 间房屋，必须加上第 `i-2` 间房屋的最大金额。
2. 如果我们不偷窃第 `i` 间房屋，那么我们可以偷窃第 `i-1` 间房屋的最大金额。
3. 因此，状态转移方程为：
   \[
   dp[i] = \max(dp[i-1], dp[i-2] + nums[i])
   \]
   其中，`nums[i]` 表示第 `i` 间房屋内的金额。

### 初始化：
- `dp[0] = nums[0]`，偷窃第一间房屋时，最大金额就是第一间房屋的金额。
- `dp[1] = max(nums[0], nums[1])`，偷窃第一间或第二间房屋时，最大金额是其中较大的一个。

### 边界条件：
- 如果房屋数组为空，则偷窃金额为 0。
- 如果只有一间房屋，则最大金额就是该房屋的金额。

### Java 实现：

```java
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // 当只有一个房屋时，直接返回它的金额
        if (nums.length == 1) {
            return nums[0];
        }
        
        // 初始化 dp 数组
        int n = nums.length;
        int[] dp = new int[n];
        
        // 初始化前两个房屋的最大金额
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        
        // 从第三个房屋开始计算 dp[i]
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        
        // 返回最后一个房屋的最大金额
        return dp[n - 1];
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        
        // 示例 1
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(robber.rob(nums1)); // 输出 4
        
        // 示例 2
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(robber.rob(nums2)); // 输出 12
    }
}
```

### 解释：
1. **`rob(int[] nums)`**: 这个方法接受一个数组 `nums`，其中每个元素表示每间房屋的现金金额。
2. **`dp[i]`**: `dp[i]` 表示偷窃到第 `i` 间房屋时可以获得的最大金额。
3. **`dp[0]`**: 第一间房屋只能偷窃一次，最大金额为 `nums[0]`。
4. **`dp[1]`**: 如果只有两间房屋，那么只能选择偷窃金额较高的房屋，最大金额为 `Math.max(nums[0], nums[1])`。
5. **`dp[i]` (i >= 2)**: 如果我们决定偷窃第 `i` 间房屋，则我们不能偷窃第 `i-1` 间房屋，因此最大金额为 `dp[i-2] + nums[i]`。如果不偷窃第 `i` 间房屋，则最大金额为 `dp[i-1]`。所以状态转移方程为：
   \[
   dp[i] = \max(dp[i-1], dp[i-2] + nums[i])
   \]
6. **`dp[n-1]`**: 返回最后一间房屋的最大偷窃金额。

### 时间和空间复杂度：
- **时间复杂度**: O(n)，需要遍历 `nums` 数组一次。
- **空间复杂度**: O(n)，我们使用了一个额外的 `dp` 数组来存储每间房屋的最大偷窃金额。

### 优化空间复杂度：
由于每次计算只依赖于前两项的值，我们可以将 `dp` 数组优化为两个变量，进一步将空间复杂度降低到 O(1)。

```java
public class HouseRobber {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }

        int prev2 = nums[0]; // dp[i-2]
        int prev1 = Math.max(nums[0], nums[1]); // dp[i-1]

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(robber.rob(nums1)); // 输出 4
        
        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(robber.rob(nums2)); // 输出 12
    }
}
```

这种优化版的时间复杂度仍然是 O(n)，但空间复杂度降低到了 O(1)，只用了两个变量来存储前两项的结果。