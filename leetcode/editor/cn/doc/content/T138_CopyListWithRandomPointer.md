<p>给你一个长度为 <code>n</code> 的链表，每个节点包含一个额外增加的随机指针 <code>random</code> ，该指针可以指向链表中的任何节点或空节点。</p>

<p>构造这个链表的&nbsp;<strong><a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank">深拷贝</a></strong>。&nbsp;深拷贝应该正好由 <code>n</code> 个 <strong>全新</strong> 节点组成，其中每个新节点的值都设为其对应的原节点的值。新节点的 <code>next</code> 指针和 <code>random</code> 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。<strong>复制链表中的指针都不应指向原链表中的节点 </strong>。</p>

<p>例如，如果原链表中有 <code>X</code> 和 <code>Y</code> 两个节点，其中 <code>X.random --&gt; Y</code> 。那么在复制链表中对应的两个节点 <code>x</code> 和 <code>y</code> ，同样有 <code>x.random --&gt; y</code> 。</p>

<p>返回复制链表的头节点。</p>

<p>用一个由&nbsp;<code>n</code>&nbsp;个节点组成的链表来表示输入/输出中的链表。每个节点用一个&nbsp;<code>[val, random_index]</code>&nbsp;表示：</p>

<ul> 
 <li><code>val</code>：一个表示&nbsp;<code>Node.val</code>&nbsp;的整数。</li> 
 <li><code>random_index</code>：随机指针指向的节点索引（范围从&nbsp;<code>0</code>&nbsp;到&nbsp;<code>n-1</code>）；如果不指向任何节点，则为&nbsp;&nbsp;<code>null</code>&nbsp;。</li> 
</ul>

<p>你的代码 <strong>只</strong> 接受原链表的头节点 <code>head</code> 作为传入参数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png" style="height: 142px; width: 700px;" /></p>

<pre>
<strong>输入：</strong>head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
<strong>输出：</strong>[[7,null],[13,0],[11,4],[10,2],[1,0]]
</pre>

<p><strong class="example">示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png" style="height: 114px; width: 700px;" /></p>

<pre>
<strong>输入：</strong>head = [[1,1],[2,1]]
<strong>输出：</strong>[[1,1],[2,1]]
</pre>

<p><strong class="example">示例 3：</strong></p>

<p><strong><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png" style="height: 122px; width: 700px;" /></strong></p>

<pre>
<strong>输入：</strong>head = [[3,null],[3,0],[3,null]]
<strong>输出：</strong>[[3,null],[3,0],[3,null]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>0 &lt;= n &lt;= 1000</code>
  <meta charset="UTF-8" /></li> 
 <li><code>-10<sup>4</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
 <li><code>Node.random</code>&nbsp;为&nbsp;<code>null</code> 或指向链表中的节点。</li> 
</ul>

<p>&nbsp;</p>

<div><li>👍 1499</li><li>👎 0</li></div>


### 问题分析

这道题目要求对带有 `random` 指针的链表进行深拷贝。链表中的每个节点有两个指针：
1. **`next`**: 指向下一个节点。
2. **`random`**: 随机指向链表中的任意节点（或 `null`）。

### 解决方案

为了创建一个新的链表，并且同时维护新链表和原链表中节点的正确关系，可以通过以下步骤完成：

#### 思路：
1. **复制节点**：
    - 对于每个节点，我们首先复制其 `val` 和 `next` 指针，将新节点插入到原链表的每个节点后面。
    - 这样我们就可以利用每个节点后面的新节点来复制其 `random` 指针。

2. **复制 `random` 指针**：
    - 在新链表节点插入原链表节点后，我们可以直接使用原链表节点的 `random` 指针来设置新节点的 `random` 指针。
    - 由于新节点紧跟在原节点后面，我们可以通过原节点来访问对应的新节点。

3. **分离链表**：
    - 复制完成后，我们需要将新链表从原链表中分离出来，返回深拷贝链表的头节点。

### 步骤：
1. **插入新节点**：遍历原链表，对于每个原节点 `node`，创建一个新节点 `newNode`，并将其插入到 `node` 和 `node.next` 之间。

2. **设置 `random` 指针**：对于每个原节点 `node`，其新节点 `newNode` 的 `random` 指针应该指向 `node.random.next`，即原节点的 `random` 对应的新节点。

3. **分离新链表**：将链表拆分成两个：一个是原链表，另一个是新链表。

### 代码实现：

```java
class Node {
    int val;
    Node next;
    Node random;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node next, Node random) {
        this.val = val;
        this.next = next;
        this.random = random;
    }
}

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // 1. 插入新节点
        Node current = head;
        while (current != null) {
            Node newNode = new Node(current.val);
            newNode.next = current.next;
            current.next = newNode;
            current = newNode.next; // 跳到原链表的下一个节点
        }

        // 2. 设置新节点的 random 指针
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next; // 跳到原链表的下一个节点
        }

        // 3. 拆分链表
        Node newHead = head.next;
        Node oldNode = head;
        Node newNode = head.next;
        while (oldNode != null) {
            oldNode.next = oldNode.next.next; // 恢复原链表
            if (newNode.next != null) {
                newNode.next = newNode.next.next; // 更新新链表
            }
            oldNode = oldNode.next;
            newNode = newNode.next;
        }

        return newHead;
    }
}
```

### 解释：
1. **插入新节点**：
    - 我们遍历原链表，并在每个原节点之后插入一个新节点。`current.next = newNode` 使新节点紧跟在原节点之后。

2. **设置 `random` 指针**：
    - 对于每个原节点 `current`，如果它的 `random` 指针不为空，则新节点的 `random` 指针应该指向原节点的 `random` 对应的新节点。`current.next.random = current.random.next` 设置了新节点的 `random` 指针。

3. **拆分链表**：
    - 完成复制后，链表中每对原节点和新节点都相邻，我们需要拆分它们，恢复原链表并将新链表独立出来。
    - 在 `oldNode.next = oldNode.next.next` 中恢复了原链表，`newNode.next = newNode.next.next` 更新了新链表。

### 时间和空间复杂度：
- **时间复杂度**：O(n)，其中 `n` 是链表的长度。我们需要遍历链表三次：一次插入节点，一次设置 `random` 指针，一次拆分链表。
- **空间复杂度**：O(n)，我们新建了 `n` 个节点，因此空间复杂度为 O(n)。

### 示例分析：

#### 示例 1：

输入：
```text
head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
```

链表结构：
```
7 -> 13 -> 11 -> 10 -> 1
   |    |    |    |
   v    v    v    v
  null  7    10   13
```

输出：
```text
[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

- 每个节点的 `random` 指针被复制到对应的新节点。

#### 示例 2：

输入：
```text
head = [[1,1],[2,1]]
```

链表结构：
```
1 -> 2
|    |
v    v
2    1
```

输出：
```text
[[1,1],[2,1]]
```

- 链表只有两个节点，且它们的 `random` 指针都指向第一个节点，因此复制后的链表也保持相同的 `random` 关系。

### 总结：
这个方法利用了每个节点紧跟着其复制节点的特点，通过分步插入、设置 `random` 指针和拆分链表，成功实现了链表的深拷贝，且空间复杂度为 O(n)，时间复杂度为 O(n)。