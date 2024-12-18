<p>给你二叉树的根节点 <code>root</code> ，返回它节点值的&nbsp;<strong>前序</strong><em>&nbsp;</em>遍历。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">root = [1,null,2,3]</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">[1,2,3]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/screenshot-2024-08-29-202743.png" style="width: 200px; height: 264px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1,2,3,4,5,null,8,null,null,6,7,9]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[1,2,4,5,6,7,3,8,9]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/tree_2.png" style="width: 350px; height: 286px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = []</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[]</span></p>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">root = [1]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[1]</span></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目在范围 <code>[0, 100]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>

<div><li>👍 1301</li><li>👎 0</li></div>

要解决二叉树的前序遍历问题，前序遍历的顺序是：**根节点 -> 左子树 -> 右子树**。我们需要返回树的节点值按照前序遍历的顺序排列。

### 思路：
1. **递归解法**：
    - 递归解法非常直观。我们首先访问根节点，然后递归地遍历左子树和右子树。

2. **迭代解法**：
    - 迭代解法使用栈来模拟递归过程。根节点先入栈，遍历时每次弹出栈顶节点，访问其值，然后将右子树和左子树分别推入栈中（先右后左，因为栈是后进先出）。

### 1. 递归解法：
递归解法非常直接，我们可以使用一个辅助函数来完成。

```java
import java.util.*;

class TreeNode {
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

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }

    private void preorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        result.add(node.val);  // 先访问根节点
        preorderHelper(node.left, result);  // 遍历左子树
        preorderHelper(node.right, result); // 遍历右子树
    }
}
```

### 2. 迭代解法：
我们可以使用栈来模拟递归的过程，先将根节点入栈，每次弹出栈顶节点，访问其值，然后将右子节点和左子节点依次入栈。

```java
import java.util.*;

class TreeNode {
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

public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);

            // 注意顺序：右子节点先入栈，左子节点后入栈
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;
    }
}
```

### 解释：
1. **递归解法**：
    - 首先访问根节点，然后递归地访问左子树和右子树。
    - 递归的终止条件是当前节点为空。
    - 通过 `preorderHelper` 函数递归遍历每个节点，记录访问到的节点值。

2. **迭代解法**：
    - 我们使用栈来模拟递归，首先将根节点入栈。
    - 每次从栈中弹出一个节点，访问其值，然后将其右子节点和左子节点依次入栈（确保左子节点在右子节点之前入栈，以便后续栈顶元素是左子节点）。
    - 继续循环，直到栈为空。

### 时间复杂度：
- **递归解法**和**迭代解法**的时间复杂度都是 O(n)，其中 n 是二叉树的节点数。我们需要访问每个节点一次。

### 空间复杂度：
- **递归解法**的空间复杂度是 O(h)，其中 h 是树的高度。最坏情况下，树是链状的，递归栈的深度为树的高度。
- **迭代解法**的空间复杂度是 O(h)，其中 h 是树的高度。栈的最大大小是树的高度。

### 示例：

#### 示例 1：
输入：
```java
root = [1,null,2,3]
```

树结构：
```
    1
     \
      2
     /
    3
```

- 前序遍历的结果是：`[1, 2, 3]`

输出：
```java
[1, 2, 3]
```

#### 示例 2：
输入：
```java
root = [1,2,3,4,5,null,8,null,null,6,7,9]
```

树结构：
```
        1
       / \
      2   3
     / \    \
    4   5    8
   / \       / 
  6   7     9
```

- 前序遍历的结果是：`[1, 2, 4, 6, 7, 5, 3, 8, 9]`

输出：
```java
[1, 2, 4, 6, 7, 5, 3, 8, 9]
```

#### 示例 3：
输入：
```java
root = []
```

- 链表为空，前序遍历的结果是：`[]`

输出：
```java
[]
```

#### 示例 4：
输入：
```java
root = [1]
```

树结构：
```
1
```

- 前序遍历的结果是：`[1]`

输出：
```java
[1]
```

### 总结：
- 递归解法简单易懂，适用于大多数情况，适合学习理解树的遍历方式。
- 迭代解法通过栈模拟递归的过程，避免了递归调用的栈溢出问题，适合处理大规模树结构。