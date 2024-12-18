<p>给你一棵二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>后序遍历 </strong>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1,null,2,3]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[3,2,1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/screenshot-2024-08-29-202743.png" style="width: 200px; height: 264px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1,2,3,4,5,null,8,null,null,6,7,9]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[4,6,7,5,2,9,8,3,1]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/29/tree_2.png" style="width: 350px; height: 286px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = []</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[]</span></p>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[1]</span></p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点的数目在范围 <code>[0, 100]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>递归算法很简单，你可以通过迭代算法完成吗？</p>

<div><li>👍 1214</li><li>👎 0</li></div>


后序遍历是二叉树遍历的一种方式，它的遍历顺序是：**左子树 -> 右子树 -> 根节点**。我们需要返回树的节点值按照后序遍历的顺序排列。

### 思路：
1. **递归解法**：
    - 递归解法的思路与前序遍历相似。我们先递归遍历左子树，再递归遍历右子树，最后访问根节点。

2. **迭代解法**：
    - 迭代解法通常通过栈来模拟递归过程。我们利用栈来存储节点，同时使用一个变量来标记是否已经访问过左右子树。通过栈的出栈顺序和节点的访问顺序来模拟后序遍历。

### 1. 递归解法：
递归解法非常直观，我们可以使用一个辅助函数来完成。

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }

    private void postorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        postorderHelper(node.left, result);  // 递归遍历左子树
        postorderHelper(node.right, result); // 递归遍历右子树
        result.add(node.val);  // 访问根节点
    }
}
```

### 2. 迭代解法：
迭代解法使用两个栈，模拟递归的过程。我们先将根节点压入栈中，每次弹出栈顶节点，访问其值，并将其左右子节点按顺序压入栈中。

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            // 将左右子节点压入stack1
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // stack2中保存的是后序遍历的结果，按顺序弹出
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }
}
```

### 解释：
1. **递归解法**：
    - 在递归解法中，首先递归遍历左子树，再递归遍历右子树，最后访问根节点。递归调用的终止条件是当前节点为空。

2. **迭代解法**：
    - 使用两个栈来模拟后序遍历的过程。首先将根节点压入第一个栈 `stack1` 中。
    - 然后弹出栈顶节点并将其压入第二个栈 `stack2`。接着，将节点的左右子节点按顺序压入 `stack1`。
    - 最后，通过弹出 `stack2` 中的元素，得到的顺序即为后序遍历的结果。

### 时间和空间复杂度：
- **时间复杂度**：
    - **递归解法**：O(n)，每个节点都访问一次。
    - **迭代解法**：O(n)，每个节点也会被访问一次，栈的操作是常数时间的。

- **空间复杂度**：
    - **递归解法**：O(h)，其中 h 是树的高度。最坏情况下，树是链状的，递归栈的深度为树的高度。
    - **迭代解法**：O(n)，在最坏情况下，两个栈可能保存所有节点（如树的高度较小，或者树接近完全二叉树）。

### 示例分析：

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

- 后序遍历的顺序是：`[3, 2, 1]`

输出：
```java
[3, 2, 1]
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

- 后序遍历的顺序是：`[4, 6, 7, 5, 2, 9, 8, 3, 1]`

输出：
```java
[4, 6, 7, 5, 2, 9, 8, 3, 1]
```

#### 示例 3：
输入：
```java
root = []
```

- 链表为空，后序遍历的结果是：`[]`

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

- 后序遍历的顺序是：`[1]`

输出：
```java
[1]
```

### 总结：
- **递归解法**简单直观，适用于大多数情况。
- **迭代解法**通过使用栈来模拟递归过程，避免了递归调用的栈溢出问题，适合处理大规模树结构。