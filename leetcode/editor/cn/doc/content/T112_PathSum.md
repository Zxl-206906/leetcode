<p>给你二叉树的根节点&nbsp;<code>root</code> 和一个表示目标和的整数&nbsp;<code>targetSum</code> 。判断该树中是否存在 <strong>根节点到叶子节点</strong> 的路径，这条路径上所有节点值相加等于目标和&nbsp;<code>targetSum</code> 。如果存在，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum1.jpg" style="width: 500px; height: 356px;" /> 
<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
<strong>输出：</strong>true
<strong>解释：</strong>等于目标和的根节点到叶节点路径如上图所示。
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" /> 
<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>false
<strong>解释：</strong>树中存在两条根节点到叶子节点的路径：
(1 --&gt; 2): 和为 3
(1 --&gt; 3): 和为 4
不存在 sum = 5 的根节点到叶子节点的路径。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [], targetSum = 0
<strong>输出：</strong>false
<strong>解释：</strong>由于树是空的，所以不存在根节点到叶子节点的路径。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点的数目在范围 <code>[0, 5000]</code> 内</li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
 <li><code>-1000 &lt;= targetSum &lt;= 1000</code></li> 
</ul>

<div><li>👍 1406</li><li>👎 0</li></div>

判断二叉树中是否存在从根节点到叶子节点的路径，使得路径上的节点值之和等于目标和，可以使用递归或迭代的方法。以下是解决该问题的 Java 实现：

---

### 递归解法
```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class PathSum {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 如果节点为空，返回 false
        if (root == null) {
            return false;
        }
        
        // 如果是叶子节点，检查路径和是否等于目标和
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        
        // 递归检查左子树和右子树
        return hasPathSum(root.left, targetSum - root.val) || 
               hasPathSum(root.right, targetSum - root.val);
    }

    public static void main(String[] args) {
        PathSum solution = new PathSum();

        // 示例 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.right = new TreeNode(1);
        System.out.println(solution.hasPathSum(root1, 22)); // 输出: true

        // 示例 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        System.out.println(solution.hasPathSum(root2, 5)); // 输出: false

        // 示例 3
        System.out.println(solution.hasPathSum(null, 0)); // 输出: false
    }
}
```

---

### 迭代解法（基于栈的深度优先搜索）
```java
import java.util.Stack;

public class PathSumIterative {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        Stack<TreeNode> nodeStack = new Stack<>();
        Stack<Integer> sumStack = new Stack<>();
        nodeStack.push(root);
        sumStack.push(targetSum);

        while (!nodeStack.isEmpty()) {
            TreeNode node = nodeStack.pop();
            int currentSum = sumStack.pop();

            // 如果是叶子节点，检查路径和
            if (node.left == null && node.right == null && currentSum == node.val) {
                return true;
            }

            // 将右子节点加入栈
            if (node.right != null) {
                nodeStack.push(node.right);
                sumStack.push(currentSum - node.val);
            }

            // 将左子节点加入栈
            if (node.left != null) {
                nodeStack.push(node.left);
                sumStack.push(currentSum - node.val);
            }
        }
        return false;
    }
}
```

---

### 示例代码执行流程

#### 输入：`root = [5,4,8,11,null,13,4,7,2,null,null,null,1]`，`targetSum = 22`

**递归解法：**
1. 从根节点 `5` 开始，`targetSum = 22`。
2. 递归左子树，进入节点 `4`，更新 `targetSum = 22 - 5 = 17`。
3. 再递归左子树，进入节点 `11`，更新 `targetSum = 17 - 4 = 13`。
4. 进入叶子节点 `7`，更新 `targetSum = 13 - 11 = 2`，未达到目标和。
5. 返回到节点 `11`，递归右子树，进入叶子节点 `2`，更新 `targetSum = 2 - 2 = 0`，达到目标和，返回 `true`。

---

### 时间复杂度
- **O(N)**：遍历所有节点，`N` 为节点总数。

### 空间复杂度
- **递归解法**：**O(H)**，`H` 为树的高度。
- **迭代解法**：**O(N)**，最坏情况下栈存储所有节点。