<p>给定一个单链表 <code>L</code><em> </em>的头节点 <code>head</code> ，单链表 <code>L</code> 表示为：</p>

<pre>
L<sub>0</sub> → L<sub>1</sub> → … → L<sub>n - 1</sub> → L<sub>n</sub>
</pre>

<p>请将其重新排列后变为：</p>

<pre>
L<sub>0</sub> → L<sub>n</sub> → L<sub>1</sub> → L<sub>n - 1</sub> → L<sub>2</sub> → L<sub>n - 2</sub> → …</pre>

<p>不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://pic.leetcode-cn.com/1626420311-PkUiGI-image.png" style="width: 240px; " /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4]
<strong>输出：</strong>[1,4,2,3]</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://pic.leetcode-cn.com/1626420320-YUiulT-image.png" style="width: 320px; " /></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5]
<strong>输出：</strong>[1,5,2,4,3]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表的长度范围为 <code>[1, 5 * 10<sup>4</sup>]</code></li> 
 <li><code>1 &lt;= node.val &lt;= 1000</code></li> 
</ul>

<div><li>👍 1547</li><li>👎 0</li></div>

要解决这个问题，要求我们重新排列单链表，使其符合特定的顺序：即将链表重新排列成 `L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …` 的形式。这种重排操作不仅仅是调整节点的值，而是需要交换节点的位置。

### 思路：
1. **找到链表的中间节点**：
   使用快慢指针（类似判断链表是否有环的方法），通过快指针每次移动两步，慢指针每次移动一步，最终慢指针会指向链表的中间节点。

2. **将链表拆分成两部分**：
    - 第一步将链表从中间断开，得到两部分链表：前半部分和后半部分。
    - 后半部分需要反转，因为我们要从后往前进行重排。

3. **合并两个链表**：
   通过交替连接前半部分和反转后的后半部分来重新排列链表。

### 具体步骤：
1. **使用快慢指针找到中间节点**。
2. **拆分链表并反转后半部分**。
3. **交替合并两个链表**。

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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return; // 链表为空或只有一个节点，无需重新排列
        }

        // 1. 找到链表的中间节点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // 2. 拆分链表：将后半部分反转
        ListNode second = slow.next; // 后半部分的头节点
        slow.next = null; // 截断前半部分
        second = reverse(second); // 反转后半部分

        // 3. 交替合并两个链表
        ListNode first = head;
        while (second != null) {
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;

            first.next = second;  // 将第二部分节点接到第一部分
            second.next = tmp1;   // 将第一部分节点接到第二部分

            first = tmp1; // 移动指针
            second = tmp2;
        }
    }

    // 反转链表
    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
```

### 解释：
1. **快慢指针找中间节点**：
    - 我们通过快慢指针的方式，快速找到链表的中间节点，`slow` 会最终指向链表的中间位置。

2. **拆分并反转后半部分**：
    - 当快指针遍历到链表末尾时，慢指针正好位于中间，我们将链表从 `slow.next` 开始拆分，并调用 `reverse` 函数反转后半部分。

3. **交替合并两部分**：
    - 我们通过一个 `while` 循环，交替连接前半部分和反转后的后半部分。每次我们从前半部分取一个节点，然后从后半部分取一个节点，依次合并。

### 辅助函数 `reverse`：
- 这个函数的作用是反转链表，返回链表反转后的头节点。

### 时间和空间复杂度：
- **时间复杂度**：O(n)，其中 `n` 是链表的节点数。我们需要遍历整个链表两次（一次找到中间节点，一次反转后半部分），以及交替合并两部分。
- **空间复杂度**：O(1)，只使用了常量空间，除了输入链表本身。

### 示例分析：

#### 示例 1：
输入：
```java
head = [1,2,3,4]
```

链表结构：
```
1 -> 2 -> 3 -> 4
```

- 找到中间节点 `2`，将链表分成两部分：
  ```
  前半部分：1 -> 2
  后半部分：3 -> 4
  ```
- 反转后半部分得到：
  ```
  后半部分：4 -> 3
  ```
- 交替合并得到：
  ```
  1 -> 4 -> 2 -> 3
  ```

输出：
```java
[1,4,2,3]
```

#### 示例 2：
输入：
```java
head = [1,2,3,4,5]
```

链表结构：
```
1 -> 2 -> 3 -> 4 -> 5
```

- 找到中间节点 `3`，将链表分成两部分：
  ```
  前半部分：1 -> 2 -> 3
  后半部分：4 -> 5
  ```
- 反转后半部分得到：
  ```
  后半部分：5 -> 4
  ```
- 交替合并得到：
  ```
  1 -> 5 -> 2 -> 4 -> 3
  ```

输出：
```java
[1,5,2,4,3]
```

### 总结：
这个方法通过快慢指针找到链表的中间节点，并通过反转后半部分和交替合并的方式实现了链表的重新排列。时间复杂度为 O(n)，空间复杂度为 O(1)，非常高效。