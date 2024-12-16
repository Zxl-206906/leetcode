<p>给定一个二叉树：</p>

<pre>
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}</pre>

<p>填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 <code>NULL</code> 。</p>

<p>初始状态下，所有&nbsp;next 指针都被设置为 <code>NULL</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2019/02/15/117_sample.png" style="width: 500px; height: 171px;" /> 
<pre>
<strong>输入</strong>：root = [1,2,3,4,5,null,7]
<strong>输出：</strong>[1,#,2,3,#,4,5,7,#]
<strong>解释：</strong>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化输出按层序遍历顺序（由 next 指针连接），'#' 表示每层的末尾。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中的节点数在范围 <code>[0, 6000]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p><strong>进阶：</strong></p>

<ul> 
 <li>你只能使用常量级额外空间。</li> 
 <li>使用递归解题也符合要求，本题中递归程序的隐式栈空间不计入额外空间复杂度。</li> 
</ul>

<ul> 
</ul>

<div><li>👍 889</li><li>👎 0</li></div>

### 问题分析

要求对普通二叉树（不一定是完美二叉树）的每个节点填充 `next` 指针，使其指向同一层的右侧节点，如果不存在右侧节点，则将 `next` 指针设置为 `NULL`。

---

### 解题思路

由于二叉树可能不是完美的，我们不能直接利用子节点的固定结构进行连接。因此需要对每层的节点显式遍历来建立连接。

#### 关键点：
1. **层级遍历**：通过 `next` 指针完成每层的遍历，同时构建下一层的连接。
2. **常量额外空间要求**：不能使用额外的数据结构（如队列）。需要利用 `next` 指针完成逐层遍历。

---

### 方法：迭代法（常量额外空间）

我们按以下方式处理：
1. 使用一个指针 `current` 处理当前层的节点；
2. 在每一层，我们维护一个虚拟节点 `dummy`，用来指向下一层的起始节点；
3. 遍历当前层时，将当前节点的子节点逐一连接到 `dummy` 所指的链表上；
4. 完成当前层后，移动到下一层。

---

### Java 实现

```java
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, Node left, Node right, Node next) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.next = next;
    }
}

public class Solution {
    public Node connect(Node root) {
        if (root == null) return null;

        Node current = root; // 当前层的起始节点

        while (current != null) {
            Node dummy = new Node(0); // 虚拟节点，用于构建下一层链表
            Node tail = dummy; // 下一层链表的尾指针

            // 遍历当前层
            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left; // 连接左子节点
                    tail = tail.next;
                }
                if (current.right != null) {
                    tail.next = current.right; // 连接右子节点
                    tail = tail.next;
                }
                current = current.next; // 移动到当前层的下一个节点
            }

            // 移动到下一层
            current = dummy.next;
        }

        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 构建示例二叉树
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        // 调用方法
        solution.connect(root);

        // 打印连接后的树
        printTreeWithNextPointers(root);
    }

    // 辅助方法：按层打印二叉树的 next 指针
    private static void printTreeWithNextPointers(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            Node current = levelStart;
            while (current != null) {
                System.out.print(current.val + " -> ");
                if (current.next != null) {
                    System.out.print(current.next.val + " ");
                } else {
                    System.out.print("NULL ");
                }
                current = current.next;
            }
            System.out.println();
            levelStart = levelStart.left != null ? levelStart.left : levelStart.right;
        }
    }
}
```

---

### 示例执行

#### 输入树：
```
        1
      /   \
     2     3
    / \      \
   4   5      7
```

#### 输出（连接后的树）：
```
        1 -> NULL
      /   \
     2  -> 3 -> NULL
    / \      \
   4->5  ->   7 -> NULL
```

**逐层解析**：
1. **第一层**：节点 `1` 没有 `next`，连接完成，`current` 移动到 `2`。
2. **第二层**：`2.next = 3`，`3.next = NULL`，`current` 移动到 `4`。
3. **第三层**：`4.next = 5`，`5.next = 7`，`7.next = NULL`。

---

### 复杂度分析

1. **时间复杂度**：\( O(n) \)，每个节点只访问一次。
2. **空间复杂度**：\( O(1) \)，只使用了常量额外空间（`dummy` 节点和指针变量）。

---

### 总结

- 通过迭代法，我们巧妙地使用虚拟节点 `dummy` 和指针完成了二叉树的逐层遍历，符合题目要求的时间和空间复杂度。
- 方法适用于任意二叉树（无需满足完美二叉树的条件）。