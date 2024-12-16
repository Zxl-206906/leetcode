<p>二叉树中的<strong> 路径</strong> 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。同一个节点在一条路径序列中 <strong>至多出现一次</strong> 。该路径<strong> 至少包含一个 </strong>节点，且不一定经过根节点。</p>

<p><strong>路径和</strong> 是路径中各节点值的总和。</p>

<p>给你一个二叉树的根节点 <code>root</code> ，返回其 <strong>最大路径和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx1.jpg" style="width: 322px; height: 182px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,3]
<strong>输出：</strong>6
<strong>解释：</strong>最优路径是 2 -&gt; 1 -&gt; 3 ，路径和为 2 + 1 + 3 = 6</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/13/exx2.jpg" /> 
<pre>
<strong>输入：</strong>root = [-10,9,20,null,null,15,7]
<strong>输出：</strong>42
<strong>解释：</strong>最优路径是 15 -&gt; 20 -&gt; 7 ，路径和为 15 + 20 + 7 = 42
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目范围是 <code>[1, 3 * 10<sup>4</sup>]</code></li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<div><li>👍 2326</li><li>👎 0</li></div>

经典的二叉树问题：**二叉树中的最大路径和**。解决这个问题需要理解如何通过递归计算每个节点的最大路径贡献，并更新全局的最大路径和。

---
### 思路分析

#### 1. 路径的分类
对于一个节点 \( \text{node} \)，路径可以分为三种情况：
1. 路径经过该节点，只包含它的**左子树**。
2. 路径经过该节点，只包含它的**右子树**。
3. 路径经过该节点，同时包含**左子树**和**右子树**。

我们需要找到所有节点中路径和的最大值。

#### 2. 递归的设计
我们定义一个递归函数 `maxGain(node)`，表示从当前节点出发的最大路径和（只包含左子树或右子树的一部分）。

- 如果当前节点为空，返回 0。
- 对于非空节点：
    1. 计算左子树的最大贡献值：`leftGain = max(maxGain(node.left), 0)`。如果左子树贡献为负，则取 0（不选左子树）。
    2. 计算右子树的最大贡献值：`rightGain = max(maxGain(node.right), 0)`。
    3. 当前路径的最大和（以当前节点为路径顶点）：`currentPathSum = node.val + leftGain + rightGain`。
    4. 更新全局最大路径和：`maxSum = max(maxSum, currentPathSum)`。
    5. 返回当前节点的最大贡献值：`node.val + max(leftGain, rightGain)`。

#### 3. 全局变量
我们需要一个全局变量 `maxSum` 来保存递归过程中找到的最大路径和。

---

### 时间和空间复杂度
- **时间复杂度**：每个节点访问一次，时间复杂度为 \(O(n)\)，其中 \(n\) 是节点数量。
- **空间复杂度**：递归调用的栈深度为树的高度，空间复杂度为 \(O(h)\)。

---

### Java 实现

以下是详细的代码实现：

```java
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class BinaryTreeMaximumPathSum {
    private int maxSum = Integer.MIN_VALUE; // 全局变量，存储最大路径和

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    // 计算节点的最大贡献值
    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0; // 空节点贡献值为 0
        }

        // 递归计算左子树和右子树的最大贡献值
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 当前节点的最大路径和
        int currentPathSum = node.val + leftGain + rightGain;

        // 更新全局最大路径和
        maxSum = Math.max(maxSum, currentPathSum);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        BinaryTreeMaximumPathSum bt = new BinaryTreeMaximumPathSum();

        // 示例 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        System.out.println("最大路径和: " + bt.maxPathSum(root1)); // 输出: 6

        // 示例 2
        TreeNode root2 = new TreeNode(-10);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);
        System.out.println("最大路径和: " + bt.maxPathSum(root2)); // 输出: 42
    }
}
```

---

### 执行流程详解

#### 示例 1 输入：`root = [1,2,3]`

树结构：
```
      1
     / \
    2   3
```

**递归过程**：
1. 对节点 `2`：
    - 左子树和右子树均为空，`leftGain = 0`, `rightGain = 0`。
    - `currentPathSum = 2 + 0 + 0 = 2`，更新 `maxSum = 2`。
    - 返回贡献值：`2 + max(0, 0) = 2`。

2. 对节点 `3`：
    - 左子树和右子树均为空，`leftGain = 0`, `rightGain = 0`。
    - `currentPathSum = 3 + 0 + 0 = 3`，更新 `maxSum = 3`。
    - 返回贡献值：`3 + max(0, 0) = 3`。

3. 对节点 `1`：
    - `leftGain = 2`, `rightGain = 3`。
    - `currentPathSum = 1 + 2 + 3 = 6`，更新 `maxSum = 6`。
    - 返回贡献值：`1 + max(2, 3) = 4`。

最终结果：`maxSum = 6`。

---

#### 示例 2 输入：`root = [-10,9,20,null,null,15,7]`

树结构：
```
       -10
       /  \
      9   20
         /  \
        15   7
```

**递归过程**：
1. 对节点 `9`：
    - 左子树和右子树均为空，`leftGain = 0`, `rightGain = 0`。
    - `currentPathSum = 9 + 0 + 0 = 9`，更新 `maxSum = 9`。
    - 返回贡献值：`9 + max(0, 0) = 9`。

2. 对节点 `15`：
    - 左子树和右子树均为空，`leftGain = 0`, `rightGain = 0`。
    - `currentPathSum = 15 + 0 + 0 = 15`，更新 `maxSum = 15`。
    - 返回贡献值：`15 + max(0, 0) = 15`.

3. 对节点 `7`：
    - 左子树和右子树均为空，`leftGain = 0`, `rightGain = 0`。
    - `currentPathSum = 7 + 0 + 0 = 7`，`maxSum = 15`。
    - 返回贡献值：`7 + max(0, 0) = 7`。

4. 对节点 `20`：
    - `leftGain = 15`, `rightGain = 7`。
    - `currentPathSum = 20 + 15 + 7 = 42`，更新 `maxSum = 42`。
    - 返回贡献值：`20 + max(15, 7) = 35`。

5. 对节点 `-10`：
    - `leftGain = 9`, `rightGain = 35`。
    - `currentPathSum = -10 + 9 + 35 = 34`，`maxSum = 42`。
    - 返回贡献值：`-10 + max(9, 35) = 25`。

最终结果：`maxSum = 42`。

---

### 总结

1. **递归核心**：对每个节点，计算最大路径贡献值并更新全局最大路径和。
2. **复杂度分析**：线性时间复杂度 \(O(n)\)，空间复杂度 \(O(h)\)。
3. **应用场景**：该方法适用于所有涉及二叉树路径和的问题。