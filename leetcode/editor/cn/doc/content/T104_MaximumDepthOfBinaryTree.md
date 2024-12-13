<p>给定一个二叉树 <code>root</code> ，返回其最大深度。</p>

<p>二叉树的 <strong>最大深度</strong> 是指从根节点到最远叶子节点的最长路径上的节点数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/11/26/tmp-tree.jpg" style="width: 400px; height: 277px;" /></p>

<p>&nbsp;</p>

<pre>
<b>输入：</b>root = [3,9,20,null,null,15,7]
<b>输出：</b>3
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>root = [1,null,2]
<b>输出：</b>2
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点的数量在&nbsp;<code>[0, 10<sup>4</sup>]</code>&nbsp;区间内。</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<div><li>👍 1904</li><li>👎 0</li></div>

可以使用递归或迭代的方法来计算二叉树的最大深度。以下是用 Java 实现的代码：

### 方法 1：递归解法
递归是一种自顶向下的方法，通过递归访问左右子树，返回最大深度。

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaxDepthBinaryTree solution = new MaxDepthBinaryTree();
        System.out.println(solution.maxDepth(root)); // 输出: 3
    }
}
```

---

### 方法 2：迭代解法（使用层序遍历）
通过广度优先搜索（BFS）进行层级遍历，统计树的层数。

```java
import java.util.LinkedList;
import java.util.Queue;

public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        MaxDepthBinaryTree solution = new MaxDepthBinaryTree();
        System.out.println(solution.maxDepth(root)); // 输出: 3
    }
}
```

---

### 输入输出说明
1. **输入：**
    - 二叉树：`[3, 9, 20, null, null, 15, 7]`
    - 结构：
      ```
            3
           / \
          9  20
             / \
            15  7
      ```
2. **输出：** `3`

---

### 复杂度分析
1. **时间复杂度：** O(n)，其中 n 是二叉树的节点数，每个节点访问一次。
2. **空间复杂度：**
    - 递归：O(h)，其中 h 是二叉树的高度（递归栈的深度）。
    - 迭代：O(w)，其中 w 是二叉树的最大宽度（队列的大小）。