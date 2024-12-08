<p>给定一个已排序的链表的头&nbsp;<code>head</code> ，&nbsp;<em>删除原始链表中所有重复数字的节点，只留下不同的数字</em>&nbsp;。返回 <em>已排序的链表</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/linkedlist1.jpg" style="height: 142px; width: 500px;" /> 
<pre>
<strong>输入：</strong>head = [1,2,3,3,4,4,5]
<strong>输出：</strong>[1,2,5]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/linkedlist2.jpg" style="height: 164px; width: 400px;" /> 
<pre>
<strong>输入：</strong>head = [1,1,1,2,3]
<strong>输出：</strong>[2,3]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点数目在范围 <code>[0, 300]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li>题目数据保证链表已经按升序 <strong>排列</strong></li> 
</ul>

<div><li>👍 1335</li><li>👎 0</li></div>


ava 代码，处理链表去重操作（删除重复的元素），可以按照以下步骤进行修改：

1. **Java 语法调整**：
    - 使用 Java 的 `ListNode` 类代替 C++ 的 `ListNode` 结构体。
    - Java 中使用 `new` 来创建新的节点对象，类似于 C++ 中的 `new`。

2. **逻辑保持一致**：
    - 保持 C++ 代码的核心逻辑不变，主要通过两个指针 `p` 和 `q` 来遍历链表，找到并删除重复的节点。

以下是 Java 版本的代码：

```java
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // 创建一个虚拟头节点，简化边界条件处理
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        dummy.next = head;

        while (p.next != null) {
            ListNode q = p.next;
            // 检查是否有重复元素
            while (q.next != null && q.next.val == q.val) {
                q = q.next;
            }

            // 如果没有重复，p 就可以向前移动
            if (q == p.next) {
                p = p.next;
            } else {
                // 有重复，跳过所有重复节点
                p.next = q.next;
            }
        }

        // 返回去除重复节点后的链表
        return dummy.next;
    }
}
```

### 代码解释：

1. **虚拟头节点**：  
   我们创建一个虚拟头节点 `dummy`，它的 `next` 指向原链表的 `head`。这个虚拟节点使得我们可以避免在处理链表时需要特别处理头节点的特殊情况。

2. **外层循环**：  
   使用 `p` 来遍历链表，`p` 总是指向当前无重复元素的最后一个节点。

3. **内层循环**：  
   通过 `q` 来查找链表中的连续重复节点，`q` 移动到所有重复节点的最后一个位置。

4. **判断是否有重复**：
    - 如果 `q` 和 `p.next` 是同一个节点，说明没有重复节点，`p` 向前移动。
    - 否则，跳过重复节点，直接将 `p.next` 指向 `q.next`，跳过重复的部分。

5. **返回结果**：  
   返回 `dummy.next`，即去除重复后的链表头节点。

### 时间复杂度：
- `O(n)`，其中 `n` 是链表的长度。我们遍历链表一次，并且每个节点只会被访问一次。

### 空间复杂度：
- `O(1)`，没有使用额外的空间，只是修改链表中的连接。

### 示例：

#### 示例 1：

```java
public static void main(String[] args) {
    Solution solution = new Solution();
    ListNode head = new ListNode(1);
    head.next = new ListNode(1);
    head.next.next = new ListNode(2);
    head.next.next.next = new ListNode(3);
    head.next.next.next.next = new ListNode(3);

    ListNode result = solution.deleteDuplicates(head);
    // 打印链表
    while (result != null) {
        System.out.print(result.val + " ");
        result = result.next;
    }
}
```

输出：
```
1 2 3
```