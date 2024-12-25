<p>有一个&nbsp;<code>m x n</code>&nbsp;大小的矩形蛋糕，需要切成&nbsp;<code>1 x 1</code>&nbsp;的小块。</p>

<p>给你整数&nbsp;<code>m</code>&nbsp;，<code>n</code>&nbsp;和两个数组：</p>

<ul> 
 <li><code>horizontalCut</code> 的大小为&nbsp;<code>m - 1</code>&nbsp;，其中&nbsp;<code>horizontalCut[i]</code>&nbsp;表示沿着水平线 <code>i</code>&nbsp;切蛋糕的开销。</li> 
 <li><code>verticalCut</code> 的大小为&nbsp;<code>n - 1</code>&nbsp;，其中&nbsp;<code>verticalCut[j]</code>&nbsp;表示沿着垂直线&nbsp;<code>j</code>&nbsp;切蛋糕的开销。</li> 
</ul>

<p>一次操作中，你可以选择任意不是&nbsp;<code>1 x 1</code>&nbsp;大小的矩形蛋糕并执行以下操作之一：</p>

<ol> 
 <li>沿着水平线&nbsp;<code>i</code>&nbsp;切开蛋糕，开销为&nbsp;<code>horizontalCut[i]</code>&nbsp;。</li> 
 <li>沿着垂直线&nbsp;<code>j</code>&nbsp;切开蛋糕，开销为&nbsp;<code>verticalCut[j]</code>&nbsp;。</li> 
</ol>

<p>每次操作后，这块蛋糕都被切成两个独立的小蛋糕。</p>

<p>每次操作的开销都为最开始对应切割线的开销，并且不会改变。</p>

<p>请你返回将蛋糕全部切成&nbsp;<code>1 x 1</code>&nbsp;的蛋糕块的&nbsp;<strong>最小</strong>&nbsp;总开销。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>m = 3, n = 2, horizontalCut = [1,3], verticalCut = [5]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>13</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/06/04/ezgifcom-animated-gif-maker-1.gif" style="width: 280px; height: 320px;" /></p>

<ul> 
 <li>沿着垂直线 0 切开蛋糕，开销为 5 。</li> 
 <li>沿着水平线 0 切开&nbsp;<code>3 x 1</code>&nbsp;的蛋糕块，开销为 1 。</li> 
 <li>沿着水平线 0 切开 <code>3 x 1</code>&nbsp;的蛋糕块，开销为 1 。</li> 
 <li>沿着水平线 1 切开 <code>2 x 1</code>&nbsp;的蛋糕块，开销为 3 。</li> 
 <li>沿着水平线 1 切开 <code>2 x 1</code>&nbsp;的蛋糕块，开销为 3 。</li> 
</ul>

<p>总开销为&nbsp;<code>5 + 1 + 1 + 3 + 3 = 13</code>&nbsp;。</p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>m = 2, n = 2, horizontalCut = [7], verticalCut = [4]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>15</span></p>

<p><strong>解释：</strong></p>

<ul> 
 <li>沿着水平线 0 切开蛋糕，开销为 7 。</li> 
 <li>沿着垂直线 0 切开&nbsp;<code>1 x 2</code>&nbsp;的蛋糕块，开销为 4 。</li> 
 <li>沿着垂直线 0 切开&nbsp;<code>1 x 2</code>&nbsp;的蛋糕块，开销为 4 。</li> 
</ul>

<p>总开销为&nbsp;<code>7 + 4 + 4 = 15</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= m, n &lt;= 20</code></li> 
 <li><code>horizontalCut.length == m - 1</code></li> 
 <li><code>verticalCut.length == n - 1</code></li> 
 <li><code>1 &lt;= horizontalCut[i], verticalCut[i] &lt;= 10<sup>3</sup></code></li> 
</ul>

<div><li>👍 30</li><li>👎 0</li></div>

以下是用 Java 实现的解决方案：

### Java 实现：

```java
import java.util.Arrays;

public class CakeCutting {
    public int minCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        // 对水平和垂直切割线进行排序，从大到小
        Arrays.sort(horizontalCut);
        Arrays.sort(verticalCut);
        
        // 当前的行数和列数
        int horizontalCount = 1;
        int verticalCount = 1;

        // 总开销
        int totalCost = 0;

        // 双指针分别指向水平切割线和垂直切割线
        int i = horizontalCut.length - 1;
        int j = verticalCut.length - 1;

        // 贪心选择切割线
        while (i >= 0 && j >= 0) {
            // 如果水平切割线的开销大于垂直切割线，则选择水平切割
            if (horizontalCut[i] > verticalCut[j]) {
                totalCost += horizontalCut[i] * verticalCount; // 当前剩余的列数
                horizontalCount++; // 增加一行
                i--;
            } else {
                totalCost += verticalCut[j] * horizontalCount; // 当前剩余的行数
                verticalCount++; // 增加一列
                j--;
            }
        }

        // 如果还有剩余的水平切割线
        while (i >= 0) {
            totalCost += horizontalCut[i] * verticalCount;
            horizontalCount++;
            i--;
        }

        // 如果还有剩余的垂直切割线
        while (j >= 0) {
            totalCost += verticalCut[j] * horizontalCount;
            verticalCount++;
            j--;
        }

        return totalCost;
    }

    public static void main(String[] args) {
        CakeCutting cakeCutting = new CakeCutting();

        // 示例 1
        int m1 = 3, n1 = 2;
        int[] horizontalCut1 = {1, 3};
        int[] verticalCut1 = {5};
        System.out.println(cakeCutting.minCost(m1, n1, horizontalCut1, verticalCut1));  // 输出 13

        // 示例 2
        int m2 = 2, n2 = 2;
        int[] horizontalCut2 = {7};
        int[] verticalCut2 = {4};
        System.out.println(cakeCutting.minCost(m2, n2, horizontalCut2, verticalCut2));  // 输出 15
    }
}
```

### 解释：

1. **排序**：首先将水平切割线 `horizontalCut` 和垂直切割线 `verticalCut` 从大到小排序。这样我们可以优先选择开销大的切割线进行切割，从而使得总开销最小。
2. **贪心策略**：使用两个指针 `i` 和 `j`，分别指向水平和垂直切割线的末尾，选择开销更大的切割线进行切割。
    - 如果水平切割线的开销更大，则切割水平线，并增加行数。
    - 如果垂直切割线的开销更大，则切割垂直线，并增加列数。
3. **更新开销**：每次选择一个切割线时，开销为当前的行数和列数的乘积（行数和列数决定了当前切割的区域大小）。
4. **处理剩余的切割线**：当其中一个切割线数组被完全处理后，继续处理另一个数组中的剩余切割线。

### 时间复杂度：
- 排序操作的时间复杂度是 `O(m log m + n log n)`，其中 `m` 和 `n` 分别是水平和垂直切割线的数量。
- 遍历切割线的时间复杂度是 `O(m + n)`。
- 总时间复杂度为 `O(m log m + n log n)`，适合在 `m, n <= 20` 的约束下运行。

### 示例：

#### 示例 1：
输入：
```java
int m1 = 3, n1 = 2;
int[] horizontalCut1 = {1, 3};
int[] verticalCut1 = {5};
System.out.println(cakeCutting.minCost(m1, n1, horizontalCut1, verticalCut1));  // 输出 13
```
输出：
```
13
```

#### 示例 2：
输入：
```java
int m2 = 2, n2 = 2;
int[] horizontalCut2 = {7};
int[] verticalCut2 = {4};
System.out.println(cakeCutting.minCost(m2, n2, horizontalCut2, verticalCut2));  // 输出 15
```
输出：
```
15
```

该 Java 实现能够解决问题并返回最小的总切割开销。