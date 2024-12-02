<p>给你两个单词&nbsp;<code>word1</code> 和&nbsp;<code>word2</code>， <em>请返回将&nbsp;<code>word1</code>&nbsp;转换成&nbsp;<code>word2</code> 所使用的最少操作数</em> &nbsp;。</p>

<p>你可以对一个单词进行如下三种操作：</p>

<ul> 
 <li>插入一个字符</li> 
 <li>删除一个字符</li> 
 <li>替换一个字符</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>word1 = "horse", word2 = "ros"
<strong>输出：</strong>3
<strong>解释：</strong>
horse -&gt; rorse (将 'h' 替换为 'r')
rorse -&gt; rose (删除 'r')
rose -&gt; ros (删除 'e')
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>word1 = "intention", word2 = "execution"
<strong>输出：</strong>5
<strong>解释：</strong>
intention -&gt; inention (删除 't')
inention -&gt; enention (将 'i' 替换为 'e')
enention -&gt; exention (将 'n' 替换为 'x')
exention -&gt; exection (将 'n' 替换为 'c')
exection -&gt; execution (插入 'u')
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= word1.length, word2.length &lt;= 500</code></li> 
 <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li> 
</ul>

<div><li>👍 3540</li><li>👎 0</li></div>


思路
编辑距离终于来了，这道题目如果大家没有了解动态规划的话，会感觉超级复杂。

编辑距离是用动规来解决的经典题目，这道题目看上去好像很复杂，但用动规可以很巧妙的算出最少编辑距离。

接下来我依然使用动规五部曲，对本题做一个详细的分析：

# 1. 确定dp数组（dp table）以及下标的含义
dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。

有同学问了，为啥要表示下标i-1为结尾的字符串呢，为啥不表示下标i为结尾的字符串呢？

为什么这么定义我在 718. 最长重复子数组 (opens new window)中做了详细的讲解。

其实用i来表示也可以！ 用i-1就是为了方便后面dp数组初始化的。

# 2. 确定递推公式
在确定递推公式的时候，首先要考虑清楚编辑的几种操作，整理如下：

if (word1[i - 1] == word2[j - 1])
    不操作 
if (word1[i - 1] != word2[j - 1])
    增
    删
    换
也就是如上4种情况。

if (word1[i - 1] == word2[j - 1]) 那么说明不用任何编辑，dp[i][j] 就应该是 dp[i - 1][j - 1]，即dp[i][j] = dp[i - 1][j - 1];

此时可能有同学有点不明白，为啥要即dp[i][j] = dp[i - 1][j - 1]呢？

那么就在回顾上面讲过的dp[i][j]的定义，word1[i - 1] 与 word2[j - 1]相等了，那么就不用编辑了，以下标i-2为结尾的字符串word1和以下标j-2为结尾的字符串word2的最近编辑距离dp[i - 1][j - 1]就是 dp[i][j]了。

在下面的讲解中，如果哪里看不懂，就回想一下dp[i][j]的定义，就明白了。

**在整个动规的过程中，最为关键就是正确理解dp[i][j]的定义！**

if (word1[i - 1] != word2[j - 1])，此时就需要编辑了，如何编辑呢？

* 操作一：word1删除一个元素，那么就是以下标i - 2为结尾的word1 与 j-1为结尾的word2的最近编辑距离 再加上一个操作。
* 即 dp[i][j] = dp[i - 1][j] + 1;

* 操作二：word2删除一个元素，那么就是以下标i - 1为结尾的word1 与 j-2为结尾的word2的最近编辑距离 再加上一个操作。
* 即 dp[i][j] = dp[i][j - 1] + 1;

这里有同学发现了，怎么都是删除元素，添加元素去哪了。

word2添加一个元素，相当于word1删除一个元素，例如 word1 = "ad" ，word2 = "a"，word1删除元素'd' 和 word2添加一个元素'd'，变成word1="a", word2="ad"， 最终的操作数是一样！ dp数组如下图所示意的：

            a                         a     d
    +-----+-----+             +-----+-----+-----+
    |  0  |  1  |             |  0  |  1  |  2  |
    +-----+-----+   ===>      +-----+-----+-----+
    a |  1  |  0  |           a |  1  |  0  |  1  |
    +-----+-----+             +-----+-----+-----+
    d |  2  |  1  |
    +-----+-----+
操作三：替换元素，word1替换word1[i - 1]，使其与word2[j - 1]相同，此时不用增删加元素。

可以回顾一下，if (word1[i - 1] == word2[j - 1])的时候我们的操作 是 dp[i][j] = dp[i - 1][j - 1] 对吧。

那么只需要一次替换的操作，就可以让 word1[i - 1] 和 word2[j - 1] 相同。

所以 dp[i][j] = dp[i - 1][j - 1] + 1;

综上，当 if (word1[i - 1] != word2[j - 1]) 时取最小的，即：dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;

递归公式代码如下：
```text
    if (word1[i - 1] == word2[j - 1]) {
    dp[i][j] = dp[i - 1][j - 1];
    }
    else {
    dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
    }
```
# 3. dp数组如何初始化
再回顾一下dp[i][j]的定义：

dp[i][j] 表示以下标i-1为结尾的字符串word1，和以下标j-1为结尾的字符串word2，最近编辑距离为dp[i][j]。

那么dp[i][0] 和 dp[0][j] 表示什么呢？

dp[i][0] ：以下标i-1为结尾的字符串word1，和空字符串word2，最近编辑距离为dp[i][0]。

那么dp[i][0]就应该是i，对word1里的元素全部做删除操作，即：dp[i][0] = i;

同理dp[0][j] = j;

所以C++代码如下：

for (int i = 0; i <= word1.size(); i++) dp[i][0] = i;
for (int j = 0; j <= word2.size(); j++) dp[0][j] = j;
#4. 确定遍历顺序
从如下四个递推公式：

dp[i][j] = dp[i - 1][j - 1]
dp[i][j] = dp[i - 1][j - 1] + 1
dp[i][j] = dp[i][j - 1] + 1
dp[i][j] = dp[i - 1][j] + 1
可以看出dp[i][j]是依赖左方，上方和左上方元素的，如图：

![img.png](../img/img20.png)

72.编辑距离

所以在dp矩阵中一定是从左到右从上到下去遍历。

代码如下：
```text
for (int i = 1; i <= word1.size(); i++) {
    for (int j = 1; j <= word2.size(); j++) {
        if (word1[i - 1] == word2[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1];
        }
        else {
            dp[i][j] = min({dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]}) + 1;
        }
    }
}
```
#5. 举例推导dp数组
以示例1为例，输入：word1 = "horse", word2 = "ros"为例，dp矩阵状态图如下：

![img.png](../img/img19.png)
