<div class="title__3Vvk">
 给你一个输入字符串 (
 <code>s</code>) 和一个字符模式 (
 <code>p</code>) ，请你实现一个支持 
 <code>'?'</code> 和 
 <code>'*'</code> 匹配规则的通配符匹配：
</div>

<ul> 
 <li class="title__3Vvk"><code>'?'</code> 可以匹配任何单个字符。</li> 
 <li class="title__3Vvk"><code>'*'</code> 可以匹配任意字符序列（包括空字符序列）。</li> 
</ul>

<div class="original__bRMd"> 
 <div> 
  <p>判定匹配成功的充要条件是：字符模式必须能够 <strong>完全匹配</strong> 输入字符串（而不是部分匹配）。</p> 
 </div> 
</div> &nbsp;

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "a"
<strong>输出：</strong>false
<strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "aa", p = "*"
<strong>输出：</strong>true
<strong>解释：</strong>'*' 可以匹配任意字符串。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "cb", p = "?a"
<strong>输出：</strong>false
<strong>解释：</strong>'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= s.length, p.length &lt;= 2000</code></li> 
 <li><code>s</code> 仅由小写英文字母组成</li> 
 <li><code>p</code> 仅由小写英文字母、<code>'?'</code> 或 <code>'*'</code> 组成</li> 
</ul>

<div><li>👍 1182</li><li>👎 0</li></div>

### **代码功能分析**

这段代码是一个实现通配符匹配（Wildcard Matching）的解决方案，用于判断字符串 `s` 是否与模式 `p` 匹配，其中模式 `p` 包含两种特殊字符：
1. **`?`**：可以匹配任意一个字符。
2. **`*`**：可以匹配任意数量（包括 0 个）的字符。

---

### **核心思路**

这段代码使用了**双指针法**，通过维护两个指针：
- **`i`**：指向字符串 `s` 的当前字符位置。
- **`j`**：指向模式 `p` 的当前字符位置。

此外还维护了两个辅助变量：
- **`starPos`**：记录最后一个 `*` 在模式 `p` 中的位置。
- **`match`**：记录匹配到 `*` 的时候，字符串 `s` 的位置。

通过逐字符遍历和回溯的方式，处理 `*` 和 `?` 的匹配逻辑。

---

### **详细流程分析**

#### **初始化**
- `i = 0, j = 0`: 初始化指针，分别从 `s` 和 `p` 的开头开始匹配。
- `starPos = -1`: `*` 的位置初始为 -1，表示尚未找到。
- `match = -1`: 匹配到 `*` 时，记录字符串 `s` 的当前位置。

---

#### **主循环**
```text
while (i < s.length()) {
```
- 当指针 `i` 尚未超出字符串 `s` 的长度时，执行以下逻辑。

---

##### **Case 1：当前字符匹配成功**
```text
if (j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
    i++;
    j++;
}
```
- 如果当前字符匹配成功（`s.charAt(i) == p.charAt(j)`），或者模式字符为 `?`（可匹配任意字符），两指针均前移。

---

##### **Case 2：当前字符为 `*`**
```text
} else if (j < p.length() && p.charAt(j) == '*') {
    starPos = j;
    match = i;
    j = starPos + 1;
}
```
- 如果模式字符为 `*`：
    1. 记录 `*` 的位置 `starPos`。
    2. 记录当前字符串 `s` 的匹配位置 `match`。
    3. 指针 `j` 后移到 `*` 的下一个字符，准备继续匹配。

---

##### **Case 3：回溯（匹配失败后尝试利用 `*`）**
```text
} else if (starPos != -1) {
    match++;
    i = match;
    j = starPos + 1;
}
```
- 如果当前字符无法匹配，且之前遇到过 `*`，尝试通过 `*` 匹配当前字符：
    1. 将 `match` 增加 1，表示尝试匹配下一个字符。
    2. `i` 移动到 `match`，继续尝试匹配。
    3. `j` 回到 `*` 后的第一个字符位置，重新开始匹配。

---

##### **Case 4：完全无法匹配**
```text
} else {
    return false;
}
```
- 如果当前字符无法匹配，且模式中没有可以利用的 `*`，返回 `false`。

---

#### **处理模式中尾部的多余 `*`**
```text
while (j < p.length() && p.charAt(j) == '*') {
    j++;
}
```
- 匹配完成后，如果模式 `p` 中尾部有多余的 `*`，跳过这些字符。

---

#### **返回结果**
```text
return j == p.length();
```
- 如果指针 `j` 已经移动到模式 `p` 的末尾，说明完全匹配；否则返回 `false`。

---

### **代码执行流程示例**

#### 示例 1：
**输入：**
```text
s = "abefcd"
p = "a*cd"
```

**执行过程：**
1. `i = 0, j = 0`: 匹配 `a == a`，两指针后移。
2. `i = 1, j = 1`: `p[j] == '*'`，记录 `starPos = 1, match = 1`，`j` 后移。
3. `i = 1, j = 2`: 匹配失败，利用 `*`，回溯：`match = 2, i = 2, j = 2`。
4. `i = 2, j = 2`: 匹配失败，继续回溯：`match = 3, i = 3, j = 2`。
5. `i = 3, j = 2`: 匹配 `e == c` 失败，继续回溯：`match = 4, i = 4, j = 2`。
6. `i = 4, j = 2`: 匹配 `c == c` 成功，两指针后移。
7. `i = 5, j = 3`: 匹配 `d == d` 成功，两指针后移。
8. `i = 6, j = 4`: 匹配完成，返回 `true`。

---

### **时间和空间复杂度分析**

#### **时间复杂度**
- 每次匹配时，主循环最多遍历字符串 `s` 和模式 `p` 各一次，总复杂度为 **O(m + n)**，其中 `m` 和 `n` 分别是 `s` 和 `p` 的长度。

#### **空间复杂度**
- 仅使用了常量级额外空间，复杂度为 **O(1)**。

---

### **总结**
- **优点：** 通过双指针和回溯，能够高效处理通配符匹配问题。
- **适用场景：** 适合处理长字符串和复杂通配符的匹配问题，时间和空间效率均优。

