<p>给你二叉树的根节点 <code>root</code> ，返回其节点值的 <strong>层序遍历</strong> 。 （即逐层地，从左到右访问所有节点）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg" style="width: 277px; height: 302px;" /> 
<pre>
<strong>输入：</strong>root = [3,9,20,null,null,15,7]
<strong>输出：</strong>[[3],[9,20],[15,7]]
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

<div><li>👍 2048</li><li>👎 0</li></div>




层序遍历（Level Order Traversal）是按照二叉树的每一层从左到右依次遍历节点值的方式进行。使用 **广度优先搜索（BFS）** 方法实现最为常见。以下是使用 Java 的代码示例：

### Java 实现代码
```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class LevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            result.add(currentLevel);
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        LevelOrderTraversal traversal = new LevelOrderTraversal();
        List<List<Integer>> result = traversal.levelOrder(root);
        System.out.println(result);
    }
}
```

---

### 输入输出说明
1. **输入：**
    - 构造一个二叉树：`[3, 9, 20, null, null, 15, 7]`。
    - 二叉树结构：
      ```
            3
           / \
          9   20
             /  \
            15   7
      ```

2. **输出：**
    - `[[3], [9, 20], [15, 7]]`

---

### 关键点说明
1. **队列（Queue）：** 利用 `Queue` 来存储每一层的节点，实现广度优先遍历。
2. **层级分割：** 使用 `levelSize` 确定当前层的节点个数，遍历一层后加入 `result`。
3. **空树处理：** 特殊处理输入为空树的情况，直接返回空列表。

---

### 复杂度分析
1. **时间复杂度：** O(n)，其中 n 是二叉树的节点数，每个节点访问一次。
2. **空间复杂度：** O(n)，队列的大小在最坏情况下（完全二叉树）与层级节点数量相同。