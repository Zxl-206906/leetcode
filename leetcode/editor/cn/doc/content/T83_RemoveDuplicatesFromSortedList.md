<p>给定一个已排序的链表的头
 <meta charset="UTF-8" />&nbsp;<code>head</code>&nbsp;，&nbsp;<em>删除所有重复的元素，使每个元素只出现一次</em>&nbsp;。返回 <em>已排序的链表</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list1.jpg" style="height: 160px; width: 200px;" /> 
<pre>
<strong>输入：</strong>head = [1,1,2]
<strong>输出：</strong>[1,2]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/list2.jpg" style="height: 123px; width: 300px;" /> 
<pre>
<strong>输入：</strong>head = [1,1,2,3,3]
<strong>输出：</strong>[1,2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点数目在范围 <code>[0, 300]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li>题目数据保证链表已经按升序 <strong>排列</strong></li> 
</ul>

<div><li>👍 1173</li><li>👎 0</li></div>


下面是Java 代码的版本。这个代码是删除链表中的重复元素，确保每个元素在链表中只出现一次。

### C++ 代码解释：
- 使用两个指针 `p` 和 `q` 来遍历链表。
    - `p` 指向当前正在处理的节点。
    - `q` 用来遍历与 `p` 节点值相同的所有节点，并跳过它们。
- 只保留不重复的节点，删除重复节点。

### 转换后的 Java 代码：

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        //TODO p为初始化头结点
        ListNode p = head;

        //TODO 遍历列表 查到最后一个节点
        while (p != null && p.next != null) {
            //TODO 如果当前节点和下一个节点的值相同，则将当前节点的下一个节点指向当前节点的下下个节点，即跳过当前节点的下一个节点
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                //TODO 如果当前节点和下一个节点的值不相同，则将当前节点向后移动一位，继续遍历下一个节点
                p = p.next;
            }
        }
        return head;
    }
}
```

### 代码解释：
1. **ListNode 类**：
    - 定义了链表的节点类 `ListNode`，包含 `val`（节点的值）和 `next`（指向下一个节点的引用）。

2. **`deleteDuplicates` 方法**：
    - `p` 初始化为链表的头节点。
    - 使用 `while (p != null && p.next != null)` 遍历整个链表，检查当前节点 `p` 是否和下一个节点重复。
    - 使用内层循环 `while (q != null && q.val == p.val)` 来跳过所有值相同的节点。
    - `p.next = q` 将 `p` 的 `next` 指针指向没有重复的下一个节点。
    - `p = p.next` 使 `p` 指向下一个非重复节点，继续检查。

3. **返回结果**：
    - 返回链表的头节点 `head`，此时链表中的重复元素已经被删除。

### 时间复杂度：
- `O(n)`，其中 `n` 是链表的长度。我们遍历链表一次，操作每个节点一次。

### 空间复杂度：
- `O(1)`，没有使用额外的空间，操作是在原链表上进行的。

### 示例：

```java
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        
        // 创建测试链表: [1, 1, 2, 3, 3]
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        // 删除重复节点
        ListNode result = solution.deleteDuplicates(head);
        
        // 打印去重后的链表: [1, 2, 3]
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }
}
```

输出：
```
1 2 3
```

### 总结：
- 该 Java 代码实现了链表的去重操作，删除所有重复的元素，确保每个元素在链表中只出现一次。
