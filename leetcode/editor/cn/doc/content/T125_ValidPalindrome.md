<p>如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 <strong>回文串</strong> 。</p>

<p>字母和数字都属于字母数字字符。</p>

<p>给你一个字符串 <code>s</code>，如果它是 <strong>回文串</strong> ，返回 <code>true</code><em> </em>；否则，返回<em> </em><code>false</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong> s = "A man, a plan, a canal: Panama"
<strong>输出：</strong>true
<strong>解释：</strong>"amanaplanacanalpanama" 是回文串。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "race a car"
<strong>输出：</strong>false
<strong>解释：</strong>"raceacar" 不是回文串。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = " "
<strong>输出：</strong>true
<strong>解释：</strong>在移除非字母数字字符之后，s 是一个空字符串 "" 。
由于空字符串正着反着读都一样，所以是回文串。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 2 * 10<sup>5</sup></code></li> 
 <li><code>s</code> 仅由可打印的 ASCII 字符组成</li> 
</ul>

<div><li>👍 785</li><li>👎 0</li></div>


这道题目要求判断一个字符串在去除非字母数字字符、将大写字符转换为小写后，是否是回文串。回文串的特点是正着读和反着读相同。

---

### **解法：双指针**

使用双指针方法，从字符串的首尾向中间检查：
1. **过滤字符：** 通过检查字符是否是字母或数字，跳过不符合的字符。
2. **忽略大小写：** 在比较时将所有字母转换为小写。
3. **双指针比较：** 如果两端的字符相等，则继续比较，否则返回 `false`。

---

### **Java 实现代码**
```java
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        
        while (left < right) {
            // 跳过左边非字母数字字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            // 跳过右边非字母数字字符
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            // 比较当前字符是否相等（忽略大小写）
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
}
```

---

### **运行示例**

#### 示例 1
- 输入：`s = "A man, a plan, a canal: Panama"`
- 过程：
    1. 转换后：`"amanaplanacanalpanama"`
    2. 判断正反一致，返回 `true`。
- 输出：`true`

#### 示例 2
- 输入：`s = "race a car"`
- 过程：
    1. 转换后：`"raceacar"`
    2. 正反不一致，返回 `false`。
- 输出：`false`

#### 示例 3
- 输入：`s = " "`
- 过程：
    1. 空字符串视为回文，返回 `true`。
- 输出：`true`

---

### **复杂度分析**
- **时间复杂度：** O(n)，只需遍历字符串一次。
- **空间复杂度：** O(1)，只使用常量额外空间。

---

### **扩展**
- 如果字符串包含 Unicode 字符，可以使用 `Character.isLetterOrDigit` 来检查字符是否合法。