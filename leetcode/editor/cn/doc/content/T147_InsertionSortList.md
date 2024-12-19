<p>给定单个链表的头
 <meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，使用 <strong>插入排序</strong> 对链表进行排序，并返回&nbsp;<em>排序后链表的头</em>&nbsp;。</p>

<p><strong>插入排序</strong>&nbsp;算法的步骤:</p>

<ol> 
 <li>插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。</li> 
 <li>每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。</li> 
 <li>重复直到所有输入数据插入完为止。</li> 
</ol>

<p>下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。</p>

<p>对链表进行插入排序。</p>

<p><img alt="" src="https://pic.leetcode.cn/1724130387-qxfMwx-Insertion-sort-example-300px.gif" /></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://pic.leetcode.cn/1724130414-QbPAjl-image.png" /></p>

<pre>
<strong>输入:</strong> head = [4,2,1,3]
<strong>输出:</strong> [1,2,3,4]</pre>

<p><strong>示例&nbsp;2：</strong></p>

<p><img alt="" src="https://pic.leetcode.cn/1724130432-zoOvdI-image.png" /></p>

<pre>
<strong>输入:</strong> head = [-1,5,3,4,0]
<strong>输出:</strong> [-1,0,3,4,5]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<p>
 <meta charset="UTF-8" /></p>

<ul> 
 <li>列表中的节点数在&nbsp;<code>[1, 5000]</code>范围内</li> 
 <li><code>-5000 &lt;= Node.val &lt;= 5000</code></li> 
</ul>

<div><li>👍 682</li><li>👎 0</li></div>


以下是一个完整的 **Java 实现**，用于对单链表执行插入排序：

---

### **代码实现**
```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        // 创建一个哨兵节点（dummy node）用于简化操作
        ListNode dummy = new ListNode(0); 
        ListNode current = head; // 当前需要插入的节点

        while (current != null) {
            // 保存下一节点的引用
            ListNode next = current.next;

            // 在dummy链表中找到插入位置
            ListNode prev = dummy;
            while (prev.next != null && prev.next.val < current.val) {
                prev = prev.next;
            }

            // 执行插入操作
            current.next = prev.next;
            prev.next = current;

            // 移动到下一个节点
            current = next;
        }

        return dummy.next; // 返回排序后的链表
    }

    public static void main(String[] args) {
        // 示例测试
        InsertionSortList solution = new InsertionSortList();

        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode sortedList = solution.insertionSortList(head);

        // 打印结果
        printList(sortedList); // 输出：[1, 2, 3, 4]
    }

    public static void printList(ListNode head) {
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
**输入链表**：
- 示例 1: `head = [4, 2, 1, 3]`
- 示例 2: `head = [-1, 5, 3, 4, 0]`

**输出链表**：
- 示例 1: `[1, 2, 3, 4]`
- 示例 2: `[-1, 0, 3, 4, 5]`

---

### **代码逻辑解析**
1. **哨兵节点 (`dummy node`) 的作用**：
    - 为了便于在链表头部插入节点，创建一个值为 `0` 的哨兵节点。
    - 哨兵节点的 `next` 指针始终指向排序好的链表头。

2. **逐一遍历未排序的节点**：
    - 使用 `current` 指针遍历原链表，将每个节点插入到排序链表中。

3. **找到插入位置**：
    - 使用 `prev` 指针遍历排序好的链表，找到第一个值大于或等于 `current` 的位置。

4. **插入节点**：
    - 将 `current` 插入到 `prev` 和 `prev.next` 之间。

5. **移动到下一个节点**：
    - 保存当前节点的 `next` 引用，然后更新 `current` 指针。

---

### **时间复杂度分析**
- **最坏情况**（链表逆序）：O(n²)
    - 每次插入都需要遍历排序链表。
- **最好情况**（链表已排序）：O(n)
    - 每次插入都不需要移动节点。
- **总体复杂度**：平均为 O(n²)。

---

### **空间复杂度**
- 使用了一个哨兵节点，空间复杂度为 **O(1)**（仅占用常量空间）。

---

### **算法特点**
- **稳定性**：插入排序不会改变相同元素的相对顺序，因此是稳定排序。
- **适用场景**：
    - 链表的插入排序适用于小规模数据集。
    - 对于规模较大的链表，可能需要优化或选择其他排序算法。