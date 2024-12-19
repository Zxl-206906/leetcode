<p>给你链表的头结点&nbsp;<code>head</code>&nbsp;，请将其按 <strong>升序</strong> 排列并返回 <strong>排序后的链表</strong> 。</p>

<ul> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg" style="width: 450px;" /> 
<pre>
<b>输入：</b>head = [4,2,1,3]
<b>输出：</b>[1,2,3,4]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg" style="width: 550px;" /> 
<pre>
<b>输入：</b>head = [-1,5,3,4,0]
<b>输出：</b>[-1,0,3,4,5]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>head = []
<b>输出：</b>[]
</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul> 
 <li>链表中节点的数目在范围&nbsp;<code>[0, 5 * 10<sup>4</sup>]</code>&nbsp;内</li> 
 <li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><b>进阶：</b>你可以在&nbsp;<code>O(n&nbsp;log&nbsp;n)</code> 时间复杂度和常数级空间复杂度下，对链表进行排序吗？</p>

<div><li>👍 2413</li><li>👎 0</li></div>


以下是实现按升序对链表排序的代码，使用 **归并排序**，满足时间复杂度为 \(O(n \log n)\)，并且对空间复杂度进行优化。

---

### **代码实现**

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class SortList {
    public ListNode sortList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 使用快慢指针找到链表的中点
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // 将链表分为两部分
        prev.next = null;

        // 递归地对两部分排序
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // 合并两个已排序的链表
        return merge(left, right);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 哨兵节点
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // 处理剩余的节点
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        // 示例测试
        SortList solution = new SortList();

        // 创建链表 [4, 2, 1, 3]
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));

        // 对链表排序
        ListNode sorted = solution.sortList(head);

        // 打印排序后的链表
        printList(sorted); // 输出：[1, 2, 3, 4]
    }

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }
}
```

---

### **执行示例**

**输入：**
- 示例 1: `head = [4, 2, 1, 3]`
- 示例 2: `head = [-1, 5, 3, 4, 0]`
- 示例 3: `head = []`

**输出：**
- 示例 1: `[1, 2, 3, 4]`
- 示例 2: `[-1, 0, 3, 4, 5]`
- 示例 3: `[]`

---

### **代码解析**

1. **分割链表**：
    - 使用 **快慢指针** 找到链表的中点。
    - 快指针 (`fast`) 每次走两步，慢指针 (`slow`) 每次走一步，最终慢指针指向链表的中点。

2. **递归排序**：
    - 将链表分为两部分，通过递归对左半部分和右半部分分别排序。

3. **合并两个有序链表**：
    - 使用辅助函数 `merge`，按照归并排序的思想将两个有序链表合并为一个。

4. **哨兵节点**：
    - `dummy` 节点简化了链表操作，避免处理头节点的特殊情况。

---

### **时间复杂度**
- **递归分割链表**：每次将链表分为两部分，共 \(O(\log n)\) 次。
- **合并链表**：每次合并操作的时间复杂度为 \(O(n)\)。
- **总复杂度**：\(O(n \log n)\)。

---

### **空间复杂度**
- 递归调用栈的空间复杂度为 \(O(\log n)\)。
- 如果进一步优化，可以使用非递归归并排序，将空间复杂度降为 \(O(1)\)。

---

### **总结**
- 本实现采用归并排序对链表进行排序，满足题目对时间和空间复杂度的要求。
- **优点**：适合处理链表这种数据结构，不需要额外空间存储数组索引。
