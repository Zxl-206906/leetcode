<p>给定整数 <code>n</code> ，返回 <em>所有小于非负整数&nbsp;<code>n</code>&nbsp;的质数的数量</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 10
<strong>输出：</strong>4
<strong>解释：</strong>小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 0
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出</strong>：0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= n &lt;= 5 * 10<sup>6</sup></code></li> 
</ul>

<div><li>👍 1199</li><li>👎 0</li></div>

这个问题要求我们返回所有小于给定整数 `n` 的质数数量。质数是大于 1 且只能被 1 和其本身整除的数。为了高效地找到所有小于 `n` 的质数，我们可以使用 **埃拉托斯特尼筛法**（Sieve of Eratosthenes），这是一种经典的用于找出所有小于 `n` 的质数的高效算法。

### 埃拉托斯特尼筛法：
- 通过标记出所有非质数的数，可以高效地筛选出质数。
- 从 2 开始，标记所有 2 的倍数为非质数，再标记所有 3 的倍数为非质数，依此类推。
- 最终未被标记的数就是质数。

### 代码实现（Java）：

```java
public class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0; // 0 和 1 不是质数，2 也是最小质数

        boolean[] isPrime = new boolean[n];
        // 默认所有数都认为是质数
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
        // 从 2 开始进行筛选
        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                // 将 i 的所有倍数标记为非质数
                for (int j = i * i; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        
        // 统计质数的数量
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        
        return count;
    }
}
```

### 解析：
1. **初始化**：创建一个布尔数组 `isPrime`，其中 `isPrime[i]` 表示 `i` 是否是质数。初始时，假设所有数字都是质数。
2. **筛选过程**：从 2 开始，对于每个 `i`，如果 `i` 是质数（即 `isPrime[i] == true`），那么将所有 `i` 的倍数标记为非质数（`isPrime[j] = false`），从 `i*i` 开始，因为 `i*i` 之前的倍数已经被其他小于 `i` 的数处理过。
3. **统计质数数量**：遍历 `isPrime` 数组，统计所有 `isPrime[i] == true` 的位置，计数即为质数的数量。

### 示例：
#### 示例 1：
输入：
```java
int n = 10;
Solution solution = new Solution();
int result = solution.countPrimes(n);
```
输出：
```java
4 // 质数是 2, 3, 5, 7
```

#### 示例 2：
输入：
```java
int n = 0;
Solution solution = new Solution();
int result = solution.countPrimes(n);
```
输出：
```java
0 // 小于 0 没有质数
```

#### 示例 3：
输入：
```java
int n = 1;
Solution solution = new Solution();
int result = solution.countPrimes(n);
```
输出：
```java
0 // 小于 1 没有质数
```

### 时间复杂度：
- **O(n log log n)**：埃拉托斯特尼筛法的时间复杂度是 `O(n log log n)`，比暴力的方法（直接检查每个数是否是质数）要高效得多。

### 空间复杂度：
- **O(n)**：需要一个大小为 `n` 的布尔数组来存储每个数是否为质数。