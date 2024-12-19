<p>给你一个字符串数组 <code>tokens</code> ，表示一个根据&nbsp;<a href="https://baike.baidu.com/item/%E9%80%86%E6%B3%A2%E5%85%B0%E5%BC%8F/128437" target="_blank">逆波兰表示法</a> 表示的算术表达式。</p>

<p>请你计算该表达式。返回一个表示表达式值的整数。</p>

<p><strong>注意：</strong></p>

<ul> 
 <li>有效的算符为 <code>'+'</code>、<code>'-'</code>、<code>'*'</code> 和 <code>'/'</code> 。</li> 
 <li>每个操作数（运算对象）都可以是一个整数或者另一个表达式。</li> 
 <li>两个整数之间的除法总是 <strong>向零截断</strong> 。</li> 
 <li>表达式中不含除零运算。</li> 
 <li>输入是一个根据逆波兰表示法表示的算术表达式。</li> 
 <li>答案及所有中间计算结果可以用 <strong>32 位</strong> 整数表示。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>tokens = ["2","1","+","3","*"]
<strong>输出：</strong>9
<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入：</strong>tokens = ["4","13","5","/","+"]
<strong>输出：</strong>6
<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
<strong>输出：</strong>22
<strong>解释：</strong>该算式转化为常见的中缀算术表达式为：
  ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
= ((10 * (6 / (12 * -11))) + 17) + 5
= ((10 * (6 / -132)) + 17) + 5
= ((10 * 0) + 17) + 5
= (0 + 17) + 5
= 17 + 5
= 22</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= tokens.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>tokens[i]</code>&nbsp;是一个算符（<code>"+"</code>、<code>"-"</code>、<code>"*"</code> 或 <code>"/"</code>），或是在范围 <code>[-200, 200]</code> 内的一个整数</li> 
</ul>

<p>&nbsp;</p>

<p><strong>逆波兰表达式：</strong></p>

<p>逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。</p>

<ul> 
 <li>平常使用的算式则是一种中缀表达式，如 <code>( 1 + 2 ) * ( 3 + 4 )</code> 。</li> 
 <li>该算式的逆波兰表达式写法为 <code>( ( 1 2 + ) ( 3 4 + ) * )</code> 。</li> 
</ul>

<p>逆波兰表达式主要有以下两个优点：</p>

<ul> 
 <li>去掉括号后表达式无歧义，上式即便写成 <code>1 2 + 3 4 + * </code>也可以依据次序计算出正确结果。</li> 
 <li>适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中</li> 
</ul>

<div><li>👍 967</li><li>👎 0</li></div>


**解决逆波兰表达式的问题**，需要用栈操作来逐步处理表达式中的数字和运算符。以下是一个完整的解决方案及其代码实现：

---

### **思路**
1. 使用栈 `stack` 来保存数字。
2. 遍历数组 `tokens`：
    - 如果是数字，则将其压入栈中。
    - 如果是运算符，弹出栈顶的两个数字进行相应运算（注意顺序），然后将结果压入栈中。
3. 最后栈中只剩下一个元素，即为计算结果。

---

### **代码实现**
以下是使用 Java 实现的代码：

```java
import java.util.Stack;

public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        
        for (String token : tokens) {
            // 检查是否是运算符
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int b = stack.pop(); // 栈顶是第二个操作数
                int a = stack.pop(); // 第二个栈顶是第一个操作数
                
                // 根据运算符计算结果
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b); // 向零截断
                        break;
                }
            } else {
                // 如果是数字，直接压入栈
                stack.push(Integer.parseInt(token));
            }
        }
        
        // 栈中最后剩下的就是结果
        return stack.pop();
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation solution = new EvaluateReversePolishNotation();
        // 示例测试
        System.out.println(solution.evalRPN(new String[]{"2", "1", "+", "3", "*"})); // 输出 9
        System.out.println(solution.evalRPN(new String[]{"4", "13", "5", "/", "+"})); // 输出 6
        System.out.println(solution.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"})); // 输出 22
    }
}
```

---

### **代码说明**
1. **栈的使用：**
    - 每次遇到数字时将其压入栈。
    - 每次遇到运算符时，弹出栈顶两个元素进行运算，将结果压回栈中。

2. **运算符的处理：**
    - 注意 `/` 运算是向零截断的整数除法。
    - 操作数的顺序很重要：对于减法和除法，第二个弹出的数是被减数或被除数。

3. **时间复杂度和空间复杂度：**
    - 时间复杂度：`O(n)`，其中 `n` 是 `tokens` 的长度，每个数字和运算符只被遍历一次。
    - 空间复杂度：`O(n)`，最坏情况下栈的深度可能是 `n`。

---

### **执行流程**
以 `tokens = ["2", "1", "+", "3", "*"]` 为例，执行流程如下：
1. 初始栈：`[]`
2. 遇到数字 `2`，入栈：`[2]`
3. 遇到数字 `1`，入栈：`[2, 1]`
4. 遇到运算符 `+`，弹出 `1` 和 `2`，计算 `2 + 1 = 3`，结果入栈：`[3]`
5. 遇到数字 `3`，入栈：`[3, 3]`
6. 遇到运算符 `*`，弹出 `3` 和 `3`，计算 `3 * 3 = 9`，结果入栈：`[9]`
7. 最终栈中只有一个元素 `9`，即为计算结果。

---

### **扩展测试用例**
- 输入：`tokens = ["4", "13", "5", "/", "+"]`
    - 解析过程：`(4 + (13 / 5)) = 6`
    - 输出：`6`

- 输入：`tokens = ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]`
    - 解析过程：
      ```
      ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
      = ((10 * (6 / (12 * -11))) + 17) + 5
      = ((10 * (6 / -132)) + 17) + 5
      = ((10 * 0) + 17) + 5
      = 17 + 5
      = 22
      ```
    - 输出：`22`