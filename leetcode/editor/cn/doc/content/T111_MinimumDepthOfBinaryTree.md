<p>给定一个二叉树，找出其最小深度。</p>

<p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>

<p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg" style="width: 432px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<div><li>👍 1249</li><li>👎 0</li></div>

要找到二叉树的最小深度，可以使用递归或迭代方法。最小深度是从根节点到最近叶子节点的路径长度，其中叶子节点是指没有左右子节点的节点。

### Java代码
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) {
        this.val = val;
    }
}

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        // 如果左子树为空，递归右子树
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }

        // 如果右子树为空，递归左子树
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }

        // 左右子树都不为空时，取最小深度
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static void main(String[] args) {
        MinimumDepthOfBinaryTree solution = new MinimumDepthOfBinaryTree();

        // 示例 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(solution.minDepth(root1)); // 输出: 2

        // 示例 2
        TreeNode root2 = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.right.right = new TreeNode(4);
        root2.right.right.right = new TreeNode(5);
        root2.right.right.right.right = new TreeNode(6);
        System.out.println(solution.minDepth(root2)); // 输出: 5
    }
}
```

### 执行流程
#### 示例 1：
输入：`[3,9,20,null,null,15,7]`
1. 从根节点`3`开始，递归左子树`[9]`和右子树`[20,15,7]`。
2. 左子树`[9]`深度为1，右子树`[20,15,7]`深度为2。
3. 最小深度为`1 + 1 = 2`。

#### 示例 2：
输入：`[2,null,3,null,4,null,5,null,6]`
1. 根节点`2`只有右子树，递归计算右子树深度。
2. 最终深度为`5`，因为每层只有一个节点。

### 时间复杂度
- **O(N)**：遍历每个节点一次。

### 空间复杂度
- **O(H)**：递归调用的栈空间，`H`为树的高度。