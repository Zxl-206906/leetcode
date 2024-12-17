<p>给你一个链表的头节点 <code>head</code> ，判断链表中是否有环。</p>

<p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（索引从 0 开始）。<strong>注意：<code>pos</code> 不作为参数进行传递&nbsp;</strong>。仅仅是为了标识链表的实际情况。</p>

<p><em>如果链表中存在环</em>&nbsp;，则返回 <code>true</code> 。 否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" /></p>

<pre>
<strong>输入：</strong>head = [3,2,0,-4], pos = 1
<strong>输出：</strong>true
<strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" /></p>

<pre>
<strong>输入：</strong>head = [1,2], pos = 0
<strong>输出：</strong>true
<strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" /></p>

<pre>
<strong>输入：</strong>head = [1], pos = -1
<strong>输出：</strong>false
<strong>解释：</strong>链表中没有环。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目范围是 <code>[0, 10<sup>4</sup>]</code></li> 
 <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li><code>pos</code> 为 <code>-1</code> 或者链表中的一个 <strong>有效索引</strong> 。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能用 <code>O(1)</code>（即，常量）内存解决此问题吗？</p>

<div><li>👍 2239</li><li>👎 0</li></div>


判断链表是否有环的常见方法是使用 **快慢指针**（又称龟兔赛跑算法）。这种方法可以在 **O(n)** 的时间复杂度下解决问题，且 **O(1)** 的空间复杂度，因此符合题目的要求。

### 快慢指针方法
1. **快慢指针**：我们使用两个指针来遍历链表，其中：
    - **慢指针**（`slow`）每次走一步。
    - **快指针**（`fast`）每次走两步。

2. **环的判断**：如果链表中有环，快指针和慢指针最终会在环中相遇。如果链表中没有环，快指针会先走到 `null`（即链表的尾部），这时链表中没有环。

3. **实现细节**：
    - 如果 `fast` 或 `fast.next` 为空，说明链表没有环，直接返回 `false`。
    - 如果 `slow` 和 `fast` 相遇，则说明链表有环，返回 `true`。

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
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false; // 空链表或者只有一个节点，肯定没有环
        }

        ListNode slow = head; // 慢指针
        ListNode fast = head; // 快指针

        while (fast != null && fast.next != null) {
            slow = slow.next;           // 慢指针走一步
            fast = fast.next.next;      // 快指针走两步

            if (slow == fast) {
                return true; // 快慢指针相遇，说明有环
            }
        }

        return false; // 快指针走到空，说明没有环
    }
}
```

### 解释：

1. **初始化**：
    - `slow` 和 `fast` 都从链表的头节点 `head` 开始。

2. **遍历链表**：
    - 每次进入 `while` 循环时，检查 `fast` 和 `fast.next` 是否为 `null`，如果是 `null` 说明链表已经没有环（快指针到了链表的末尾）。
    - 如果 `slow` 和 `fast` 相遇，说明链表中存在环。

3. **返回结果**：
    - 如果快慢指针相遇，则返回 `true`。
    - 如果 `fast` 到达 `null`，则返回 `false`，说明链表没有环。

### 时间复杂度：
- **O(n)**，其中 `n` 是链表的长度。在最坏情况下，快指针和慢指针会在环中遍历一圈。

### 空间复杂度：
- **O(1)**，我们只用了常量空间来存储两个指针，因此空间复杂度是常数级别。

### 示例分析：

#### 示例 1：

输入：
```java
head = [3, 2, 0, -4], pos = 1
```

链表结构：
```
3 -> 2 -> 0 -> -4
     ^_________|
```

- 快指针和慢指针会在节点 `2` 处相遇，因此返回 `true`，链表有环。

#### 示例 2：

输入：
```java
head = [1, 2], pos = 0
```

链表结构：
```
1 -> 2
^_____|
```

- 快指针和慢指针会在节点 `1` 处相遇，因此返回 `true`，链表有环。

#### 示例 3：

输入：
```java
head = [1], pos = -1
```

链表结构：
```
1
```

- 快指针会直接到达 `null`，因此返回 `false`，链表没有环。

### 总结：
通过使用快慢指针，我们能够高效地判断链表中是否存在环，并且能保证算法的时间复杂度是 O(n)，空间复杂度是 O(1)，符合题目的要求。