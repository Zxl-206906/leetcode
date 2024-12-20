<p>给你一个字符串 <code>s</code> ，请你反转字符串中 <strong>单词</strong> 的顺序。</p>

<p><strong>单词</strong> 是由非空格字符组成的字符串。<code>s</code> 中使用至少一个空格将字符串中的 <strong>单词</strong> 分隔开。</p>

<p>返回 <strong>单词</strong> 顺序颠倒且 <strong>单词</strong> 之间用单个空格连接的结果字符串。</p>

<p><strong>注意：</strong>输入字符串 <code>s</code>中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "<span><code>the sky is blue</code></span>"
<strong>输出：</strong>"<span><code>blue is sky the</code></span>"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = " &nbsp;hello world &nbsp;"
<strong>输出：</strong>"world hello"
<strong>解释：</strong>反转后的字符串中不能存在前导空格和尾随空格。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "a good &nbsp; example"
<strong>输出：</strong>"example good a"
<strong>解释：</strong>如果两个单词间有多余的空格，反转后的字符串需要将单词间的空格减少到仅有一个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>s</code> 包含英文大小写字母、数字和空格 <code>' '</code></li> 
 <li><code>s</code> 中 <strong>至少存在一个</strong> 单词</li> 
</ul>

<ul> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果字符串在你使用的编程语言中是一种可变数据类型，请尝试使用&nbsp;<code>O(1)</code> 额外空间复杂度的 <strong>原地</strong> 解法。</p>

<div><li>👍 1241</li><li>👎 0</li></div>

可以通过以下步骤来反转字符串中的单词顺序：

1. **去除多余空格**：首先，我们需要处理字符串中的多余空格。包括前导空格、尾随空格以及单词之间的多个空格。可以通过先分割字符串，再去除每个单词的空格来实现。
2. **反转单词顺序**：将处理后的单词顺序反转。
3. **连接单词**：将反转后的单词用单个空格连接起来。

### 代码实现（Java）：
```java
public class Solution {
    public String reverseWords(String s) {
        // 去除前后空格，并按空格分割单词
        String[] words = s.trim().split("\\s+");
        
        // 使用StringBuilder来构建反转后的字符串
        StringBuilder result = new StringBuilder();
        
        // 反转单词并连接到StringBuilder中
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            // 添加空格（不在最后一个单词后面添加空格）
            if (i > 0) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
}
```

### 解释：
1. **去除前后空格**：`s.trim()` 可以去除字符串的前导和尾随空格。
2. **按空格分割字符串**：`split("\\s+")` 使用正则表达式 `\\s+` 将字符串按照一个或多个空格进行分割，这样可以处理单词之间的多个空格。
3. **反转单词顺序**：使用 `for` 循环从数组的最后一个元素开始，逐个添加单词到结果字符串中。
4. **拼接结果**：通过 `StringBuilder` 来构建反转后的字符串，避免在字符串拼接时创建过多的中间对象。

### 示例：
对于输入 `"  hello world  "`：
1. `trim()` 去除前后空格，得到 `"hello world"`.
2. 使用 `split("\\s+")` 分割成 `["hello", "world"]`.
3. 反转并拼接得到 `"world hello"`。

### 复杂度分析：
- 时间复杂度：O(n)，其中 `n` 是字符串的长度。我们遍历字符串并分割，反转数组。
- 空间复杂度：O(n)，我们使用了额外的 `StringBuilder` 和分割后的数组存储单词。