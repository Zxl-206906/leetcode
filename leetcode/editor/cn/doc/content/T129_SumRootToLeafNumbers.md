给你一个二叉树的根节点 <code>root</code> ，树中每个节点都存放有一个 <code>0</code> 到 <code>9</code> 之间的数字。

<div class="original__bRMd"> 
 <div> 
  <p>每条从根节点到叶节点的路径都代表一个数字：</p> 
 </div>
</div>

<ul> 
 <li>例如，从根节点到叶节点的路径 <code>1 -&gt; 2 -&gt; 3</code> 表示数字 <code>123</code> 。</li> 
</ul>

<p>计算从根节点到叶节点生成的 <strong>所有数字之和</strong> 。</p>

<p><strong>叶节点</strong> 是指没有子节点的节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/num1tree.jpg" style="width: 212px; height: 182px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,3]
<strong>输出：</strong>25
<strong>解释：</strong>
从根到叶子节点路径 <span><code>1-&gt;2</code></span> 代表数字 <span><code>12</code></span>
从根到叶子节点路径 <span><code>1-&gt;3</code></span> 代表数字 <span><code>13</code></span>
因此，数字总和 = 12 + 13 = <span><code>25</code></span></pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/num2tree.jpg" style="width: 292px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [4,9,0,5,1]
<strong>输出：</strong>1026
<strong>解释：</strong>
从根到叶子节点路径 <span><code>4-&gt;9-&gt;5</code></span> 代表数字 495
从根到叶子节点路径 <span><code>4-&gt;9-&gt;1</code></span> 代表数字 491
从根到叶子节点路径 <span><code>4-&gt;0</code></span> 代表数字 40
因此，数字总和 = 495 + 491 + 40 = <span><code>1026</code></span>
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点的数目在范围 <code>[1, 1000]</code> 内</li> 
 <li><code>0 &lt;= Node.val &lt;= 9</code></li> 
 <li>树的深度不超过 <code>10</code></li> 
</ul>

<div><li>👍 775</li><li>👎 0</li></div>

这个问题要求我们计算从根节点到叶节点路径形成的所有数字之和。每条从根到叶的路径代表一个数字，我们需要把这些路径转换成数字并求和。

### 思路：

1. **深度优先搜索（DFS）**：
    - 我们可以使用深度优先搜索（DFS）来遍历树，从根节点开始，逐层向下遍历每个子树，并在遍历过程中构造数字。

2. **路径数字的构造**：
    - 对于每个节点，我们将当前节点的值追加到当前路径数字的末尾。例如，如果当前路径数字为 `12`，且当前节点的值为 `3`，则路径数字变为 `123`。

3. **叶节点的判断**：
    - 叶节点是没有子节点的节点。当我们到达一个叶节点时，说明从根到这个节点的路径已经构成了一个完整的数字，我们可以将这个数字加到结果中。

4. **递归计算总和**：
    - 每次到达一个叶节点时，计算当前的路径数字并加到总和中。对于非叶节点，递归地计算其左右子树的路径数字和。

### 代码实现：

```java
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
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    // 使用 DFS 遍历树，currentSum 存储当前路径形成的数字
    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // 更新当前路径数字
        currentSum = currentSum * 10 + node.val;

        // 如果是叶节点，返回当前路径形成的数字
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // 否则递归计算左子树和右子树的路径和
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
```

### 解释：

1. **`sumNumbers`**：这是主函数，调用 `dfs` 来计算总和。初始时，`currentSum` 设置为 0。

2. **`dfs`**：这是一个递归函数，接受两个参数：
    - `node`：当前节点。
    - `currentSum`：当前路径形成的数字。

   在 `dfs` 函数中，我们：
    - 如果当前节点为空，直接返回 0。
    - 通过 `currentSum * 10 + node.val` 更新当前路径数字。
    - 如果当前节点是叶节点（没有左子树和右子树），返回 `currentSum`，即当前路径形成的数字。
    - 如果当前节点不是叶节点，递归计算左子树和右子树的路径数字和，并返回它们的和。

### 时间和空间复杂度：

- **时间复杂度**：O(n)，其中 n 是二叉树的节点数。每个节点会被访问一次，进行常数时间的操作。
- **空间复杂度**：O(h)，其中 h 是二叉树的高度。递归的深度是树的高度，因此空间复杂度为树的高度。对于一棵平衡二叉树，h 约为 log n，对于一棵不平衡的树，h 可能接近 n。

### 示例分析：

#### 示例 1：

输入：
```java
root = [1, 2, 3]
```

树结构：

```
    1
   / \
  2   3
```

- 从根节点到叶子节点的路径是 `1->2` 和 `1->3`，它们分别代表数字 `12` 和 `13`。
- 总和为 `12 + 13 = 25`。

#### 示例 2：

输入：
```java
root = [4, 9, 0, 5, 1]
```

树结构：

```
        4
       / \
      9   0
     / \  
    5   1
```

- 从根节点到叶子节点的路径是 `4->9->5`，`4->9->1`，`4->0`，它们分别代表数字 `495`，`491`，`40`。
- 总和为 `495 + 491 + 40 = 1026`。

### 总结：

通过深度优先搜索（DFS）遍历树，并逐步构造路径数字，最终我们可以计算出从根节点到所有叶节点的路径所代表的数字之和。这种方法的时间复杂度是 O(n)，适用于树节点数较大的情况。