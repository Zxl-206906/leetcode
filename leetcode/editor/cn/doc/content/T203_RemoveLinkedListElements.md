给你一个链表的头节点 <code>head</code> 和一个整数 <code>val</code> ，请你删除链表中所有满足 <code>Node.val == val</code> 的节点，并返回 <strong>新的头节点</strong> 。

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/03/06/removelinked-list.jpg" style="width: 500px; height: 142px;" /> 
<pre>
<strong>输入：</strong>head = [1,2,6,3,4,5,6], val = 6
<strong>输出：</strong>[1,2,3,4,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [], val = 1
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [7,7,7,7], val = 7
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>列表中的节点数目在范围 <code>[0, 10<sup>4</sup>]</code> 内</li> 
 <li><code>1 &lt;= Node.val &lt;= 50</code></li> 
 <li><code>0 &lt;= val &lt;= 50</code></li> 
</ul>

<div><li>👍 1496</li><li>👎 0</li></div>

好的，下面是用 Java 完成删除链表中所有满足 `Node.val == val` 的节点的代码。

### 代码实现：

```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // 创建虚拟头节点
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        // 当前指针
        ListNode current = dummy;
        
        // 遍历链表
        while (current.next != null) {
            if (current.next.val == val) {
                // 删除当前节点
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        
        // 返回新的头节点
        return dummy.next;
    }
}
```

### 解析：
1. **ListNode 类**：定义链表节点的类，包含一个整数值 `val` 和指向下一个节点的指针 `next`。
2. **removeElements 方法**：接收链表头节点 `head` 和待删除的值 `val`，返回删除指定值后的新链表头。
    - **虚拟头节点**：用一个虚拟的头节点 `dummy` 来简化删除头节点的情况。
    - **遍历链表**：通过 `current` 指针遍历链表，如果当前节点的下一个节点值等于 `val`，则删除该节点。
    - **返回结果**：最终返回 `dummy.next` 作为新的链表头。

### 示例：
#### 示例 1：
输入：
```java
ListNode head = new ListNode(1, new ListNode(2, new ListNode(6, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6)))))));
Solution solution = new Solution();
ListNode result = solution.removeElements(head, 6);
```
输出：
```java
// 新链表的输出应为 [1, 2, 3, 4, 5]
```

#### 示例 2：
输入：
```java
ListNode head = null;
Solution solution = new Solution();
ListNode result = solution.removeElements(head, 1);
```
输出：
```java
// 结果是 null
```

#### 示例 3：
输入：
```java
ListNode head = new ListNode(7, new ListNode(7, new ListNode(7, new ListNode(7))));
Solution solution = new Solution();
ListNode result = solution.removeElements(head, 7);
```
输出：
```java
// 结果是 null
```

### 时间复杂度：
- **O(n)**，其中 `n` 是链表的长度。我们只需遍历一次链表。

### 空间复杂度：
- **O(1)**，只用了常数的额外空间。

