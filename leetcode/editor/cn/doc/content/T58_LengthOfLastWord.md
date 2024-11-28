<p>给你一个字符串 <code>s</code>，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 <strong>最后一个</strong> 单词的长度。</p>

<p><strong>单词</strong> 是指仅由字母组成、不包含任何空格字符的最大<span data-keyword="substring-nonempty">子字符串</span>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "Hello World"
<strong>输出：</strong>5
<strong>解释：</strong>最后一个单词是“World”，长度为 5。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "   fly me   to   the moon  "
<strong>输出：</strong>4<strong>
解释：</strong>最后一个单词是“moon”，长度为 4。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "luffy is still joyboy"
<strong>输出：</strong>6
<strong>解释：</strong>最后一个单词是长度为 6 的“joyboy”。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>s</code> 仅有英文字母和空格 <code>' '</code> 组成</li> 
 <li><code>s</code> 中至少存在一个单词</li> 
</ul>

<div><li>👍 724</li><li>👎 0</li></div>


### 问题描述

给定一个字符串 `s`，该字符串由若干个单词组成，单词之间由空格分隔。我们需要返回字符串中**最后一个单词的长度**。

**单词**是指仅由字母组成且不包含空格的最大子字符串。

---

### **思路解析**

1. **去除末尾空格**：
    - 由于字符串的末尾可能包含空格，所以首先去除末尾的空格，以防止空格干扰最后一个单词的提取。

2. **从右往左查找**：
    - 从字符串的末尾开始扫描，找到第一个字母的位置，这就是最后一个单词的结束位置。
    - 然后向左扫描，找到第一个空格字符的位置，该位置的下一个位置即为最后一个单词的起始位置。

3. **计算最后一个单词的长度**：
    - 最后一个单词的长度就是从最后一个字母开始，到第一个空格或字符串开始位置之间的距离。

---

### **Java 实现**

```text
public class LengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        int length = 0;
        int n = s.length();
        
        // 从右往左查找第一个非空格字符
        int i = n - 1;
        
        // 跳过末尾的空格
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        
        // 计算最后一个单词的长度
        while (i >= 0 && s.charAt(i) != ' ') {
            length++;
            i--;
        }
        
        return length;
    }

    public static void main(String[] args) {
        // 示例 1
        System.out.println(lengthOfLastWord("Hello World"));  // 输出: 5
        
        // 示例 2
        System.out.println(lengthOfLastWord("   fly me   to   the moon  "));  // 输出: 4
        
        // 示例 3
        System.out.println(lengthOfLastWord("luffy is still joyboy"));  // 输出: 6
    }
}
```

---

### **详细步骤**

1. **跳过末尾空格**：
    - 使用 `while (i >= 0 && s.charAt(i) == ' ')` 跳过字符串末尾的所有空格。

2. **计算最后一个单词的长度**：
    - 接着遍历字符串中的字符，直到遇到空格或者遍历到字符串的开始。每遇到一个字母，计数器 `length` 加 1。

3. **返回结果**：
    - 遍历结束后，`length` 即为最后一个单词的长度。

---

### **时间复杂度**

- **时间复杂度**：`O(n)`，其中 `n` 是字符串的长度。我们最多需要遍历字符串一遍来找到最后一个单词的长度。
- **空间复杂度**：`O(1)`，我们只使用了常数级的额外空间。

---

### **示例**

#### 示例 1：

输入：
```text
s = "Hello World"
```

- 去除末尾空格后，字符串为 `"Hello World"`。
- 从右往左查找最后一个字母是 `d`，其长度为 5。

输出：
```text
5
```

#### 示例 2：

输入：
```text
s = "   fly me   to   the moon  "
```

- 去除末尾空格后，字符串为 `"   fly me   to   the moon"`。
- 最后一个单词是 `"moon"`，长度为 4。

输出：
```text
4
```

#### 示例 3：

输入：
```text
s = "luffy is still joyboy"
```

- 最后一个单词是 `"joyboy"`，长度为 6。

输出：
```text
6
```

---

### **总结**

1. **边界处理**：首先去掉末尾的空格，以免影响最后一个单词的提取。
2. **从右向左遍历**：这种方法简单且高效，避免了不必要的字符串切割操作。
3. **时间和空间复杂度**：该算法的时间复杂度为 `O(n)`，空间复杂度为 `O(1)`，适合处理较长的字符串。