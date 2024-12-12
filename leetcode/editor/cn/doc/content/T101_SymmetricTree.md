<p>给你一个二叉树的根节点 <code>root</code> ， 检查它是否轴对称。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://pic.leetcode.cn/1698026966-JDYPDU-image.png" style="width: 354px; height: 291px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,2,3,4,4,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://pic.leetcode.cn/1698027008-nPFLbM-image.png" style="width: 308px; height: 258px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,2,null,3,null,3]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目在范围 <code>[1, 1000]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以运用递归和迭代两种方法解决这个问题吗？</p>

<div><li>👍 2843</li><li>👎 0</li></div>

要检查一棵二叉树是否是轴对称的，可以通过递归和迭代两种方法实现。以下是详细的思路和 Java 实现代码。

---

### 解题思路

#### 轴对称的性质：
1. 左子树和右子树是镜像的。
2. 镜像条件是：
    - 左子树的左子节点 == 右子树的右子节点。
    - 左子树的右子节点 == 右子树的左子节点。

#### 递归方法：
利用递归检查两棵子树是否互为镜像：
- 比较根节点值是否相等。
- 左子树的左子节点与右子树的右子节点比较。
- 左子树的右子节点与右子树的左子节点比较。

#### 迭代方法：
使用队列，依次比较对应的节点是否对称：
1. 初始化队列，将左右子树的根节点入队。
2. 每次取出两个节点：
    - 如果均为空，继续。
    - 如果一个为空，另一个不为空，不对称。
    - 如果值不相等，不对称。
3. 将左右子节点按对称顺序（左子树的左、右子树的右；左子树的右、右子树的左）入队。

---

### Java 实现

#### 方法 1：递归

```java
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

---

#### 方法 2：迭代

```java
import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTreeIterative {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();

            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;

            queue.add(t1.left);
            queue.add(t2.right);
            queue.add(t1.right);
            queue.add(t2.left);
        }

        return true;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

---

### 测试代码

```java
public class TestSymmetricTree {
    public static void main(String[] args) {
        SymmetricTree.TreeNode root = new SymmetricTree.TreeNode(1);
        root.left = new SymmetricTree.TreeNode(2, new SymmetricTree.TreeNode(3), new SymmetricTree.TreeNode(4));
        root.right = new SymmetricTree.TreeNode(2, new SymmetricTree.TreeNode(4), new SymmetricTree.TreeNode(3));

        SymmetricTree solutionRecursive = new SymmetricTree();
        System.out.println("递归方法: " + solutionRecursive.isSymmetric(root)); // 输出: true

        SymmetricTreeIterative solutionIterative = new SymmetricTreeIterative();
        System.out.println("迭代方法: " + solutionIterative.isSymmetric(root)); // 输出: true
    }
}
```

---

### 复杂度分析

1. **时间复杂度：**
    - 递归：每个节点访问一次，时间复杂度为 \(O(n)\)，其中 \(n\) 是节点总数。
    - 迭代：同样每个节点访问一次，时间复杂度为 \(O(n)\)。

2. **空间复杂度：**
    - 递归：递归调用栈的深度为树的高度，最坏情况 \(O(n)\)。
    - 迭代：队列最多保存 \(2 \times h\) 个节点，最坏情况 \(O(n)\)。

通过递归和迭代两种方法，都可以高效判断树是否是轴对称。