<p>给定一个仅包含数字&nbsp;<code>2-9</code>&nbsp;的字符串，返回所有它能表示的字母组合。答案可以按 <strong>任意顺序</strong> 返回。</p>

<p>给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。</p>

<p><img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/11/09/200px-telephone-keypad2svg.png" style="width: 200px;" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = "23"
<strong>输出：</strong>["ad","ae","af","bd","be","bf","cd","ce","cf"]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = ""
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = "2"
<strong>输出：</strong>["a","b","c"]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= digits.length &lt;= 4</code></li> 
 <li><code>digits[i]</code> 是范围 <code>['2', '9']</code> 的一个数字。</li> 
</ul>

<div><li>👍 2950</li><li>👎 0</li></div>


### 问题分析

该问题要求将数字字符串映射为所有可能的字母组合，类似电话按键上的字母表组合。

---

### 解题思路

1. **数字到字母映射**：利用题目提供的映射表建立数字到字母的映射关系。
    - `2 -> "abc"`
    - `3 -> "def"`
    - `4 -> "ghi"`
    - `5 -> "jkl"`
    - `6 -> "mno"`
    - `7 -> "pqrs"`
    - `8 -> "tuv"`
    - `9 -> "wxyz"`

2. **回溯算法**：
    - 将问题视为多层选择树，每个数字对应一个层次，层次内的字母为可能的选择。
    - 每次选择一个字母后进入下一层递归，直到完成所有数字的选择。

3. **递归终止条件**：当组合的长度与输入数字的长度一致时，保存该组合。

4. **边界情况**：
    - 如果输入为空，直接返回空列表。

---

### Java 实现

```java
import java.util.ArrayList;
import java.util.List;

public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        // 数字到字母的映射
        String[] mapping = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "pqrs", // 7
            "tuv",  // 8
            "wxyz"  // 9
        };

        // 回溯函数
        backtrack(result, digits, mapping, 0, new StringBuilder());
        return result;
    }

    private void backtrack(List<String> result, String digits, String[] mapping, int index, StringBuilder current) {
        // 递归终止条件：当组合的长度等于输入字符串的长度
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // 获取当前数字对应的字母
        int digit = digits.charAt(index) - '0'; // 将字符转换为整数
        String letters = mapping[digit];

        // 遍历当前数字对应的字母
        for (char letter : letters.toCharArray()) {
            current.append(letter); // 选择当前字母
            backtrack(result, digits, mapping, index + 1, current); // 进入下一层
            current.deleteCharAt(current.length() - 1); // 撤销选择
        }
    }

    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();

        // 测试用例
        System.out.println(solution.letterCombinations("23"));
        System.out.println(solution.letterCombinations(""));
        System.out.println(solution.letterCombinations("2"));
    }
}
```

---

### 示例执行

#### 示例 1

**输入**：`digits = "23"`

**执行流程**：
- 第一步：数字 `2` 对应 `"abc"`，选择 `a`，进入下一层。
- 第二步：数字 `3` 对应 `"def"`，选择 `d`，生成组合 `"ad"`。
- 回溯，选择 `e`，生成 `"ae"`，以此类推。
- 最终输出：`["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]`

**输出**：`["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]`

---

#### 示例 2

**输入**：`digits = ""`

**输出**：`[]`

---

#### 示例 3

**输入**：`digits = "2"`

**执行流程**：
- 第一步：数字 `2` 对应 `"abc"`，逐个生成组合。

**输出**：`["a", "b", "c"]`

---

### 复杂度分析

1. **时间复杂度**：
    - 假设输入长度为 \( n \)，每个数字最多对应 \( 4 \) 个字母。
    - 总的组合数为 \( 4^n \)，每次组合需要 \( O(n) \) 的构建时间。
    - 总时间复杂度为 \( O(4^n \times n) \)。

2. **空间复杂度**：
    - 递归深度为 \( O(n) \)。
    - 临时字符串构建 \( O(n) \)。
    - 总空间复杂度为 \( O(n) \)。

---

### 总结

- 利用回溯法可以高效地生成所有可能的字母组合。
- 此实现满足了灵活性（支持任意输入）和效率（时间复杂度最优）。