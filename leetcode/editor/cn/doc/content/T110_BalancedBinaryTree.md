<p>给定一个二叉树，判断它是否是 <span data-keyword="height-balanced">平衡二叉树</span> &nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_1.jpg" style="width: 342px; height: 221px;" /> 
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/06/balance_2.jpg" style="width: 452px; height: 301px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,2,3,3,null,null,4,4]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>true
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中的节点数在范围 <code>[0, 5000]</code> 内</li> 
 <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 1568</li><li>👎 0</li></div>


判断一个二叉树是否是**平衡二叉树**，需要满足以下条件：
- 对于树中的每一个节点，其左右子树的高度差不超过1。
- 同时左右子树本身也必须是平衡二叉树。

可以通过递归自底向上计算树的高度，并判断每个节点的平衡性。

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

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }

    private int height(TreeNode node) {
        if (node == null) {
            return 0; // 空树高度为0
        }

        // 递归计算左子树的高度
        int leftHeight = height(node.left);
        if (leftHeight == -1) {
            return -1; // 左子树不平衡
        }

        // 递归计算右子树的高度
        int rightHeight = height(node.right);
        if (rightHeight == -1) {
            return -1; // 右子树不平衡
        }

        // 检查当前节点的平衡性
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1; // 当前节点不平衡
        }

        // 返回当前节点的高度
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        BalancedBinaryTree tree = new BalancedBinaryTree();

        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        System.out.println(tree.isBalanced(root1)); // 输出 true

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);

        System.out.println(tree.isBalanced(root2)); // 输出 false

        TreeNode root3 = null;
        System.out.println(tree.isBalanced(root3)); // 输出 true
    }
}
```

### 执行流程分析
#### 示例1：
输入：`root = [3,9,20,null,null,15,7]`
1. 递归计算左子树`[9]`和右子树`[20,15,7]`的高度。
2. 左子树高度为1，右子树高度为2，高度差为1，平衡。
3. 返回根节点高度`max(1, 2) + 1 = 3`。

输出：`true`

#### 示例2：
输入：`root = [1,2,2,3,3,null,null,4,4]`
1. 左子树高度为3，右子树高度为1，高度差为2，不平衡。
2. 提前返回`false`。

输出：`false`

#### 示例3：
输入：`root = []`
1. 空树，高度为0，默认平衡。

输出：`true`

### 时间复杂度
- **O(N)**：每个节点遍历一次。
### 空间复杂度
- **O(H)**：递归栈的空间，其中`H`为树的高度。