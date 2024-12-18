<p>给定一个链表的头节点 &nbsp;<code>head</code>&nbsp;，返回链表开始入环的第一个节点。&nbsp;<em>如果链表无环，则返回&nbsp;<code>null</code>。</em></p>

<p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（<strong>索引从 0 开始</strong>）。如果 <code>pos</code> 是 <code>-1</code>，则在该链表中没有环。<strong>注意：<code>pos</code> 不作为参数进行传递</strong>，仅仅是为了标识链表的实际情况。</p>

<p><strong>不允许修改 </strong>链表。</p>

<ul> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2018/12/07/circularlinkedlist.png" /></p>

<pre>
<strong>输入：</strong>head = [3,2,0,-4], pos = 1
<strong>输出：</strong>返回索引为 1 的链表节点
<strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" /></p>

<pre>
<strong>输入：</strong>head = [1,2], pos = 0
<strong>输出：</strong>返回索引为 0 的链表节点
<strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" /></p>

<pre>
<strong>输入：</strong>head = [1], pos = -1
<strong>输出：</strong>返回 null
<strong>解释：</strong>链表中没有环。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目范围在范围 <code>[0, 10<sup>4</sup>]</code> 内</li> 
 <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li><code>pos</code> 的值为 <code>-1</code> 或者链表中的一个有效索引</li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你是否可以使用 <code>O(1)</code> 空间解决此题？</p>

<div><li>👍 2678</li><li>👎 0</li></div>

要解决这个问题，我们可以使用 **快慢指针**（又叫龟兔赛跑算法）来检测链表中的环，并找出入环的第一个节点。具体步骤如下：

### 思路

1. **使用快慢指针判断环的存在**：
    - 使用两个指针：一个是慢指针 `slow`，每次移动一步；一个是快指针 `fast`，每次移动两步。
    - 如果链表存在环，快指针和慢指针最终会相遇。如果快指针到达链表末尾（`fast == null` 或 `fast.next == null`），则说明链表没有环。

2. **找到入环的第一个节点**：
    - 一旦检测到环存在（即快慢指针相遇），我们将慢指针移动到链表头，然后同时移动快慢指针，每次移动一步。当快慢指针相遇时，这时相遇的节点就是链表入环的第一个节点。

### 详细步骤：
1. **快慢指针判断是否有环**：通过快慢指针，如果快慢指针相遇，则证明链表有环。
2. **定位入环节点**：让慢指针重新指向链表头部，继续移动，快慢指针每次都移动一步，当它们相遇时，相遇的节点就是环的入口。

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
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null; // 链表为空或者只有一个节点，直接没有环
        }

        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;

        // 1. 快慢指针相遇判断链表是否有环
        while (fast != null && fast.next != null) {
            slow = slow.next;           // 慢指针走一步
            fast = fast.next.next;      // 快指针走两步

            if (slow == fast) {
                // 2. 如果有环，找到环的入口节点
                ListNode entry = head;
                while (entry != slow) {
                    entry = entry.next;  // entry 从头开始走
                    slow = slow.next;     // slow 从相遇点开始走
                }
                return entry; // 返回入环节点
            }
        }

        return null; // 如果没有环，返回 null
    }
}
```

### 解释：
1. **初始化**：
    - `slow` 和 `fast` 都指向链表的头节点 `head`。

2. **判断链表是否有环**：
    - 使用快慢指针，如果 `slow` 和 `fast` 在某一时刻相遇，则说明链表中存在环。

3. **找到入环节点**：
    - 一旦检测到环存在（即快慢指针相遇），让一个指针 `entry` 从链表头部开始，另一个指针 `slow` 保持在环中相遇的节点位置。然后两个指针都每次走一步，当它们相遇时，相遇的节点就是链表的入环节点。

4. **返回结果**：
    - 如果找到了入环节点，返回该节点。
    - 如果链表中没有环，返回 `null`。

### 时间复杂度：
- **O(n)**，其中 `n` 是链表的节点数。我们最多需要遍历两次链表：第一次是判断是否有环，第二次是找到入环节点。

### 空间复杂度：
- **O(1)**，我们只使用了常数的额外空间，除了输入链表本身。

### 示例分析：

#### 示例 1：
输入：
```java
head = [3,2,0,-4], pos = 1
```

链表结构：
```
3 -> 2 -> 0 -> -4
     ^__________|
```

- 快慢指针相遇后，慢指针重新指向链表头部，通过同时移动慢指针和快指针可以找到入环的第一个节点，即索引为 1 的节点（节点 `2`）。

#### 示例 2：
输入：
```java
head = [1,2], pos = 0
```

链表结构：
```
1 -> 2
^_____|
```

- 快慢指针相遇后，慢指针重新指向链表头部，通过同时移动慢指针和快指针可以找到入环的第一个节点，即索引为 0 的节点（节点 `1`）。

#### 示例 3：
输入：
```java
head = [1], pos = -1
```

链表结构：
```
1
```

- 快指针直接到达 `null`，说明链表没有环，返回 `null`。

### 总结：
使用快慢指针检测环并找入环的节点是一种高效的解决方案，时间复杂度为 O(n)，空间复杂度为 O(1)，能满足大多数链表环问题的需求。