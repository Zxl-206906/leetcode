<p>设计一个支持 <code>push</code> ，<code>pop</code> ，<code>top</code> 操作，并能在常数时间内检索到最小元素的栈。</p>

<p>实现 <code>MinStack</code> 类:</p>

<ul> 
 <li><code>MinStack()</code> 初始化堆栈对象。</li> 
 <li><code>void push(int val)</code> 将元素val推入堆栈。</li> 
 <li><code>void pop()</code> 删除堆栈顶部的元素。</li> 
 <li><code>int top()</code> 获取堆栈顶部的元素。</li> 
 <li><code>int getMin()</code> 获取堆栈中的最小元素。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入：</strong>
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

<strong>输出：</strong>
[null,null,null,null,-3,null,0,-2]

<strong>解释：</strong>
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --&gt; 返回 -3.
minStack.pop();
minStack.top();      --&gt; 返回 0.
minStack.getMin();   --&gt; 返回 -2.
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>-2<sup>31</sup>&nbsp;&lt;= val &lt;= 2<sup>31</sup>&nbsp;- 1</code></li> 
 <li><code>pop</code>、<code>top</code> 和 <code>getMin</code> 操作总是在 <strong>非空栈</strong> 上调用</li> 
 <li><code>push</code>,&nbsp;<code>pop</code>,&nbsp;<code>top</code>, and&nbsp;<code>getMin</code>最多被调用&nbsp;<code>3 * 10<sup>4</sup></code>&nbsp;次</li> 
</ul>

<div><li>👍 1859</li><li>👎 0</li></div>

### 题目解析

我们需要实现一个支持以下功能的栈，并且要求 **`getMin()`** 操作的时间复杂度为 \(O(1)\):

1. **`push(val)`**: 将元素 `val` 推入栈中。
2. **`pop()`**: 移除栈顶元素。
3. **`top()`**: 获取栈顶元素。
4. **`getMin()`**: 获取栈中最小的元素。

为了保证 `getMin()` 的时间复杂度为 \(O(1)\)，我们需要额外的数据结构来记录栈中元素的最小值。

---

### 解题思路

使用两个栈来实现：
1. **主栈**: 存储正常的栈元素。
2. **辅助栈**: 存储当前栈中最小值。
    - 每次 `push` 时，如果新元素小于等于辅助栈栈顶的值，就将新元素也推入辅助栈。
    - 每次 `pop` 时，如果主栈栈顶元素等于辅助栈栈顶元素，就同步弹出辅助栈的栈顶。

这样，辅助栈的栈顶始终是当前主栈中的最小值，`getMin()` 操作可以直接返回辅助栈的栈顶，时间复杂度为 \(O(1)\)。

---

### Java 实现

以下是完整的实现代码：

```java
import java.util.Stack;

public class MinStack {

    private Stack<Integer> stack;    // 主栈
    private Stack<Integer> minStack; // 辅助栈

    // 初始化栈对象
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    // 将元素推入栈中
    public void push(int val) {
        stack.push(val);
        // 如果辅助栈为空，或者新元素小于等于辅助栈栈顶，推入辅助栈
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }

    // 移除栈顶元素
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        // 如果主栈栈顶等于辅助栈栈顶，辅助栈也需要弹出
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }

    // 获取栈顶元素
    public int top() {
        return stack.peek();
    }

    // 获取栈中最小元素
    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("当前最小值: " + minStack.getMin()); // 输出: -3
        minStack.pop();
        System.out.println("当前栈顶值: " + minStack.top());   // 输出: 0
        System.out.println("当前最小值: " + minStack.getMin()); // 输出: -2
    }
}
```

---

### 示例运行分析

#### 输入：
```java
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]
```

#### 执行过程：
1. 初始化 MinStack，主栈和辅助栈均为空。
2. `push(-2)`:
    - 主栈: `[-2]`
    - 辅助栈: `[-2]`
3. `push(0)`:
    - 主栈: `[-2, 0]`
    - 辅助栈: `[-2]`
4. `push(-3)`:
    - 主栈: `[-2, 0, -3]`
    - 辅助栈: `[-2, -3]`
5. `getMin()`:
    - 辅助栈栈顶: `-3`
    - 输出: `-3`
6. `pop()`:
    - 主栈: `[-2, 0]`
    - 辅助栈: `[-2]`
7. `top()`:
    - 主栈栈顶: `0`
    - 输出: `0`
8. `getMin()`:
    - 辅助栈栈顶: `-2`
    - 输出: `-2`

---

### 复杂度分析

1. **时间复杂度**:
    - `push`、`pop`、`top` 和 `getMin` 操作的时间复杂度均为 \(O(1)\)。
        - 辅助栈操作与主栈操作同步，且每个元素只会进出栈一次。

2. **空间复杂度**:
    - 最坏情况下，所有元素均相同，此时辅助栈需要与主栈一样大，空间复杂度为 \(O(n)\)。

---

### 总结

通过使用主栈和辅助栈的组合，我们能够高效地实现最小值检索操作，同时保持其他栈操作的时间复杂度为 \(O(1)\)。这是一个典型的栈与辅助结构结合的设计问题。



