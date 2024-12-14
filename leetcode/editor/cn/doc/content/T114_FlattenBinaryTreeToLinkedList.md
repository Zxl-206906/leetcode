<p>给你二叉树的根结点 <code>root</code> ，请你将它展开为一个单链表：</p>

<ul> 
 <li>展开后的单链表应该同样使用 <code>TreeNode</code> ，其中 <code>right</code> 子指针指向链表中下一个结点，而左子指针始终为 <code>null</code> 。</li> 
 <li>展开后的单链表应该与二叉树 <a href="https://baike.baidu.com/item/%E5%85%88%E5%BA%8F%E9%81%8D%E5%8E%86/6442839?fr=aladdin" target="_blank"><strong>先序遍历</strong></a> 顺序相同。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/14/flaten.jpg" style="width: 500px; height: 226px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,5,3,4,null,6]
<strong>输出：</strong>[1,null,2,null,3,null,4,null,5,null,6]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [0]
<strong>输出：</strong>[0]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中结点数在范围 <code>[0, 2000]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以使用原地算法（<code>O(1)</code> 额外空间）展开这棵树吗？</p>

<div><li>👍 1759</li><li>👎 0</li></div>


将二叉树展开为单链表的问题可以通过递归或迭代的方法解决，遵循二叉树的前序遍历顺序。下面给出代码和解析：

### 方法 1：递归法
通过递归展开二叉树的左右子树，并重新链接节点。

```java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        // 递归展开左子树和右子树
        flatten(root.left);
        flatten(root.right);

        // 暂存右子树
        TreeNode rightSubtree = root.right;

        // 将左子树移到右子树位置
        root.right = root.left;
        root.left = null;

        // 将右子树连接到当前右子树的末尾
        TreeNode current = root;
        while (current.right != null) {
            current = current.right;
        }
        current.right = rightSubtree;
    }
}
```

### 方法 2：迭代法（Morris 遍历）
通过迭代实现前序遍历，并将左子树转移到右侧，效率更高。

```java
class Solution {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while (current != null) {
            if (current.left != null) {
                // 找到左子树的最右节点
                TreeNode rightmost = current.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }
                // 将原右子树连接到左子树的最右节点
                rightmost.right = current.right;

                // 将左子树转移到右子树位置
                current.right = current.left;
                current.left = null;
            }
            // 移动到右节点
            current = current.right;
        }
    }
}
```

### 示例解析
#### 输入：
```
    1
   / \
  2   5
 / \    \
3   4    6
```

#### 输出：
```
1 -> 2 -> 3 -> 4 -> 5 -> 6
```

**过程：**
1. 先递归或迭代到节点 `1` 的左子树，处理节点 `2`。
2. 节点 `2` 的左子树移到右子树，同时原右子树链接到 `4`。
3. 按照此过程逐步处理，最终生成单链表。

### 时间和空间复杂度
- **时间复杂度：** O(N)，每个节点访问一次。
- **空间复杂度：**
    - 递归法：O(H)，递归栈的深度，`H` 是树的高度。
    - 迭代法：O(1)，不使用额外的栈空间。

### 使用示例
```java
public static void main(String[] args) {
    TreeNode root = new TreeNode(1);
    root.left = new TreeNode(2);
    root.right = new TreeNode(5);
    root.left.left = new TreeNode(3);
    root.left.right = new TreeNode(4);
    root.right.right = new TreeNode(6);

    Solution solution = new Solution();
    solution.flatten(root);

    // 打印单链表
    TreeNode current = root;
    while (current != null) {
        System.out.print(current.val + " -> ");
        current = current.right;
    }
}
```

### 输出：
```
1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 
```