<p>给你一个字符串 <code>s</code> 和一个字符串列表 <code>wordDict</code> 作为字典。如果可以利用字典中出现的一个或多个单词拼接出 <code>s</code>&nbsp;则返回 <code>true</code>。</p>

<p><strong>注意：</strong>不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "leetcode", wordDict = ["leet", "code"]
<strong>输出:</strong> true
<strong>解释:</strong> 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入:</strong> s = "applepenapple", wordDict = ["apple", "pen"]
<strong>输出:</strong> true
<strong>解释:</strong> 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
&nbsp;    注意，你可以重复使用字典中的单词。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入:</strong> s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
<strong>输出:</strong> false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 300</code></li> 
 <li><code>1 &lt;= wordDict.length &lt;= 1000</code></li> 
 <li><code>1 &lt;= wordDict[i].length &lt;= 20</code></li> 
 <li><code>s</code> 和 <code>wordDict[i]</code> 仅由小写英文字母组成</li> 
 <li><code>wordDict</code> 中的所有字符串 <strong>互不相同</strong></li> 
</ul>

<div><li>👍 2633</li><li>👎 0</li></div>

这是一个典型的动态规划问题，通常称为 **单词拆分问题**（Word Break Problem）。我们的目标是判断字符串 `s` 是否可以通过字典 `wordDict` 中的单词拼接出来。我们可以用动态规划来高效解决这个问题。

### 思路：
1. **定义状态**：
    - 使用一个布尔数组 `dp`，其中 `dp[i]` 表示字符串 `s[0...i-1]` 是否可以通过字典中的单词拼接出来。
    - `dp[0]` 初始化为 `true`，表示空字符串可以被拼接出来。

2. **状态转移**：
    - 对于每一个位置 `i`，我们检查从位置 `0` 到位置 `i` 的子字符串 `s[0...i-1]` 是否可以被拆分。如果有某个位置 `j`，使得 `dp[j]` 为 `true` 且 `s[j...i-1]` 是字典中的单词，那么我们可以将 `dp[i]` 设置为 `true`。

3. **时间复杂度**：
    - 需要遍历字符串 `s` 的每个位置 `i`，并且对于每个位置 `i`，我们要检查所有可能的子串 `s[j...i-1]`，其中 `j` 从 `0` 到 `i`。因此，时间复杂度为 **O(n^2)**，其中 `n` 是字符串 `s` 的长度。
    - 对每个子串，查找是否在字典中可以通过哈希表实现，查找操作的时间复杂度为 **O(1)**，所以整体的时间复杂度为 **O(n^2)**。

4. **空间复杂度**：
    - 需要一个布尔数组 `dp`，大小为 `n+1`，所以空间复杂度为 **O(n)**。

### 代码实现：

```java
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);  // 使用哈希集来存储字典，方便快速查找
        int n = s.length();
        
        // dp[i] 表示 s[0...i-1] 是否可以拆分为字典中的单词
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;  // 空字符串是可以拆分的

        for (int i = 1; i <= n; i++) {
            // 遍历所有可能的结束位置
            for (int j = 0; j < i; j++) {
                // 如果 dp[j] 为 true 且 s[j...i-1] 在字典中，则 dp[i] 为 true
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;  // 如果 dp[i] 为 true，提前跳出循环
                }
            }
        }

        return dp[n];  // dp[n] 表示 s[0...n-1] 是否可以被拆分
    }
}
```

### 解释：
1. **初始化**：
    - 使用一个 `dp` 数组，其中 `dp[i]` 表示子串 `s[0...i-1]` 是否可以通过字典中的单词拼接。
    - `dp[0] = true` 表示空字符串是可以拆分的。

2. **动态规划过程**：
    - 外层循环 `i` 从 1 到 `n`，表示处理到字符串的每个位置。
    - 内层循环 `j` 从 0 到 `i-1`，表示检查子串 `s[j...i-1]` 是否在字典中。如果 `dp[j]` 为 `true`，且 `s[j...i-1]` 在字典中，那么 `dp[i]` 可以设置为 `true`。

3. **返回值**：
    - 最终返回 `dp[n]`，即字符串 `s[0...n-1]` 是否可以被拆分为字典中的单词。

### 示例解析：

#### 示例 1：
输入：
```java
s = "leetcode", wordDict = ["leet", "code"]
```

- 初始的 `dp` 数组为：`[true, false, false, false, false, false, false, false, false]`。
- 通过动态规划，我们逐步更新 `dp` 数组，最终 `dp[8]` 为 `true`，表示字符串 "leetcode" 可以由 "leet" 和 "code" 拼接成。

输出：
```java
true
```

#### 示例 2：
输入：
```java
s = "applepenapple", wordDict = ["apple", "pen"]
```

- 初始的 `dp` 数组为：`[true, false, false, false, false, false, false, false, false, false, false, false, false, false]`。
- 最终，`dp[13]` 为 `true`，表示字符串 "applepenapple" 可以由 "apple", "pen", "apple" 拼接成。

输出：
```java
true
```

#### 示例 3：
输入：
```java
s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
```

- 初始的 `dp` 数组为：`[true, false, false, false, false, false, false, false, false]`。
- 在动态规划过程中，无法将整个字符串 "catsandog" 拆分成字典中的单词。

输出：
```java
false
```

### 时间和空间复杂度分析：
- **时间复杂度**：
    - 外层循环遍历 `n` 次，内层循环最多遍历 `n` 次，因此总的时间复杂度是 **O(n^2)**，其中 `n` 是字符串 `s` 的长度。
    - 每次查找字典中是否包含某个单词是 **O(1)**（由于使用了哈希集）。

- **空间复杂度**：
    - 我们使用了一个大小为 `n+1` 的 `dp` 数组，因此空间复杂度为 **O(n)**。

### 总结：
- 本题通过动态规划（DP）方法解决，利用一个布尔数组 `dp` 判断是否能通过字典中的单词拼接字符串 `s`。
- 动态规划可以高效解决类似的子串拆分问题，避免了暴力法的时间复杂度过高。