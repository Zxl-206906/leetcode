<p>给定一个&nbsp;<strong>完美二叉树&nbsp;</strong>，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：</p>

<pre>
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}</pre>

<p>填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 <code>NULL</code>。</p>

<p>初始状态下，所有&nbsp;next 指针都被设置为 <code>NULL</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/02/14/116_sample.png" style="height: 171px; width: 500px;" /></p>

<pre>
<b>输入：</b>root = [1,2,3,4,5,6,7]
<b>输出：</b>[1,#,2,3,#,4,5,6,7,#]
<b>解释：</b>给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
</pre>

<p>
 <meta charset="UTF-8" /></p>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>root = []
<b>输出：</b>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点的数量在
  <meta charset="UTF-8" />&nbsp;<code>[0, 2<sup>12</sup>&nbsp;- 1]</code>&nbsp;范围内</li> 
 <li><code>-1000 &lt;= node.val &lt;= 1000</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul> 
 <li>你只能使用常量级额外空间。</li> 
 <li>使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。</li> 
</ul>

<div><li>👍 1161</li><li>👎 0</li></div>


### 问题解析

该问题要求对 **完美二叉树** 的每个节点，填充其 `next` 指针，使其指向其下一个右侧节点。如果不存在右侧节点，则将 `next` 指针设置为 `NULL`。

---

### 解题思路

1. **特性分析**
    - **完美二叉树** 的特性：所有叶子节点在同一层，且每个非叶子节点都有两个子节点。
    - 每层的节点按从左到右的顺序连接：
        - 如果节点 `node` 有左子节点，则 `node.left.next = node.right`。
        - 如果节点 `node` 有右子节点，且 `node.next` 不为空，则 `node.right.next = node.next.left`。

2. **层次遍历**（O(n) 时间复杂度 + O(1) 空间复杂度）
    - 使用递归或迭代的方法，在每一层逐步连接 `next` 指针。
    - 利用已经建立好的 `next` 指针，将同一层的节点串联起来。

---

### Java 实现

以下是使用递归和迭代两种方法解决问题的代码。

---

#### 方法 1：递归法

```java
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node(int val) {
        this.val = val;
    }
}

public class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // 如果左子节点存在
        if (root.left != null) {
            root.left.next = root.right;
            // 如果右侧存在下一个节点，连接右子节点和其下一个节点的左子节点
            if (root.next != null) {
                root.right.next = root.next.left;
            }
        }

        // 递归处理左子树和右子树
        connect(root.left);
        connect(root.right);

        return root;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        // 构建示例树
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // 调用函数
        solution.connect(root);
        // 输出连接情况
        printTreeWithNextPointers(root);
    }

    // 辅助方法：打印树的每一层和 next 指针
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
            levelStart = levelStart.left;
        }
    }
}
```

---

#### 方法 2：迭代法

```java
class SolutionIterative {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // 从树的最顶层开始
        Node levelStart = root;

        while (levelStart.left != null) { // 只需检查左子树是否存在即可
            Node current = levelStart;

            while (current != null) {
                // 连接左子节点和右子节点
                current.left.next = current.right;

                // 如果存在 next 节点，连接右子节点和下一个节点的左子节点
                if (current.next != null) {
                    current.right.next = current.next.left;
                }

                // 移动到当前层的下一个节点
                current = current.next;
            }

            // 移动到下一层
            levelStart = levelStart.left;
        }

        return root;
    }

    public static void main(String[] args) {
        SolutionIterative solution = new SolutionIterative();

        // 构建示例树
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        // 调用函数
        solution.connect(root);
        // 输出连接情况
        printTreeWithNextPointers(root);
    }

    // 辅助方法：打印树的每一层和 next 指针
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
            levelStart = levelStart.left;
        }
    }
}
```

---

### 执行流程详解

#### 示例输入：`root = [1, 2, 3, 4, 5, 6, 7]`

树结构：
```
        1 -> NULL
      /   \
     2  -> 3 -> NULL
    / \   / \
   4->5->6->7 -> NULL
```

**步骤分析（以迭代法为例）**：
1. **第一层**：
    - 节点 `1` 没有 `next`，`next` 设置为 `NULL`。

2. **第二层**：
    - 节点 `2` 的 `next` 设置为 `3`，`3.next = NULL`。

3. **第三层**：
    - 节点 `4.next = 5`。
    - 节点 `5.next = 6`。
    - 节点 `6.next = 7`。
    - 节点 `7.next = NULL`。

---

### 复杂度分析

1. **时间复杂度**：
    - 每个节点仅访问一次，时间复杂度为 \( O(n) \)。

2. **空间复杂度**：
    - **递归法**：递归调用栈的深度为树的高度 \( h \)，最差情况下空间复杂度为 \( O(h) \)。对于完美二叉树，\( h = \log_2(n) \)。
    - **迭代法**：只使用了常量空间，空间复杂度为 \( O(1) \)。

---

### 总结

- 递归和迭代两种方法都能很好地解决问题。
- 递归法直观易于理解，但使用了额外的栈空间。
- 迭代法通过逐层遍历，利用已有的 `next` 指针完成连接，空间复杂度更优。