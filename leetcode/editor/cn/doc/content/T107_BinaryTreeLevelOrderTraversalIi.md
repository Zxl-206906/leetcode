<p>给你二叉树的根节点 <code>root</code> ，返回其节点值 <strong>自底向上的层序遍历</strong> 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>[[15,7],[9,20],[3]]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[[1]]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目在范围 <code>[0, 2000]</code> 内</li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
</ul>

<div><li>👍 822</li><li>👎 0</li></div>

题目要求返回二叉树的节点值的自底向上的层序遍历，即先遍历最底层的节点，再逐层向上遍历，最后返回根节点的值。要实现这个功能，我们可以使用 **广度优先搜索（BFS）** 或 **深度优先搜索（DFS）** 来进行层序遍历，然后再对结果进行反转。

### 解题思路：
1. 使用 **广度优先搜索**（BFS）进行普通的层序遍历，利用队列来存储节点。
2. 在遍历的过程中，把每一层的节点值存储在一个临时的列表中。
3. 遍历完成后，反转结果列表，得到从底向上的层序遍历。

### 示例代码（Java）：

```java
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // 如果根节点为空，直接返回空列表
        if (root == null) {
            return new ArrayList<>();
        }

        // 存储层序遍历的结果
        List<List<Integer>> result = new ArrayList<>();
        // 使用队列来进行广度优先遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            // 遍历当前层的所有节点
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                // 如果当前节点有左子节点，加入队列
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                // 如果当前节点有右子节点，加入队列
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
            // 当前层遍历完后，将这一层的节点值插入到结果的最前面（实现自底向上）
            result.add(0, currentLevel);
        }

        return result;
    }
}
```

### 代码解释：
1. **初始化：**
    - 使用 `queue`（队列）进行广度优先遍历，队列用于存储当前层的所有节点。
    - 使用 `result` 来存储最终的遍历结果。

2. **BFS遍历：**
    - 队列中最先加入的是根节点。
    - 每次循环时，我们先记录当前层的节点数 `levelSize`。
    - 然后通过 `for` 循环遍历当前层的所有节点，每次从队列中取出一个节点，记录它的值，并将其左右子节点加入队列。
    - 当当前层遍历完后，将这一层的结果加入 `result` 列表的最前面。

3. **返回结果：**
    - 最终 `result` 列表中保存的就是自底向上的层序遍历结果。

### 示例执行流程：

#### 示例 1：
输入：`root = [3, 9, 20, null, null, 15, 7]`

1. 初始时，队列包含根节点 `[3]`。
2. 第一轮遍历：
    - 取出节点 `3`，加入 `currentLevel = [3]`。
    - 加入左子节点 `9` 和右子节点 `20` 到队列中。
    - 这一层遍历完后，`result = [[3]]`。
3. 第二轮遍历：
    - 取出节点 `9` 和 `20`，加入 `currentLevel = [9, 20]`。
    - 分别加入它们的子节点（`9` 没有子节点，`20` 有子节点 `15` 和 `7`）。
    - 这一层遍历完后，`result = [[9, 20], [3]]`。
4. 第三轮遍历：
    - 取出节点 `15` 和 `7`，加入 `currentLevel = [15, 7]`。
    - 这一层遍历完后，`result = [[15, 7], [9, 20], [3]]`。
5. 最终结果：`[[15, 7], [9, 20], [3]]`。

#### 示例 2：
输入：`root = [1]`

1. 初始时，队列包含根节点 `[1]`。
2. 第一轮遍历：
    - 取出节点 `1`，加入 `currentLevel = [1]`。
    - 这一层遍历完后，`result = [[1]]`。
3. 最终结果：`[[1]]`。

#### 示例 3：
输入：`root = []`（空树）

1. 根节点为空，直接返回空列表：`[]`。

### 复杂度分析：
- 时间复杂度：`O(n)`，其中 `n` 是树中节点的个数。每个节点都被访问一次。
- 空间复杂度：`O(n)`，最坏情况下，队列中可能会存储所有的节点（即树是满二叉树）。