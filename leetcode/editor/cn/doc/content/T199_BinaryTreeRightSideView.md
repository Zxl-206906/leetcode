<p>给定一个二叉树的 <strong>根节点</strong> <code>root</code>，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1,2,3,null,5,null,4]</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">[1,3,4]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/11/24/tmpd5jn43fs-1.png" style="width: 400px; height: 207px;" /></p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = [1,2,3,4,null,null,null,5]</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>[1,3,4,5]</span></p>

<p><strong>解释：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/11/24/tmpkpe40xeh-1.png" style="width: 400px; height: 214px;" /></p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">root = [1,null,3]</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">[1,3]</span></p>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>root = []</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">[]</span></p>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li>二叉树的节点个数的范围是 <code>[0,100]</code></li> 
 <li>
  <meta charset="UTF-8" /><code>-100&nbsp;&lt;= Node.val &lt;= 100</code>&nbsp;</li> 
</ul>

<div><li>👍 1150</li><li>👎 0</li></div>

要解决这个问题，要求我们从二叉树的右侧看树，从顶部到底部输出所有可见的节点。这个问题可以通过层次遍历（BFS）来解决，在遍历每一层时，我们选择每一层最右边的节点。

### 思路：
1. **层次遍历**（广度优先搜索，BFS）是一个理想的选择，因为它可以按层次遍历树。
2. 对于每一层，我们只关心该层的最右边的节点。
3. 我们可以使用队列来进行层次遍历。每次取出队列的节点，遍历其子节点（先右后左，保证先遍历右子树）。
4. 记录每一层最右边的节点。

### 具体步骤：
- 使用 **队列** 来进行层次遍历，每次遍历一层。
- 对于每一层，我们记录该层的最右边节点。
- 从右侧看到的节点顺序就是每一层的最后一个节点。

### Java 实现：

```java
import java.util.*;

public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            // 遍历当前层的所有节点
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                // 如果是当前层的最后一个节点，记录下来
                if (i == size - 1) {
                    result.add(current.val);
                }

                // 先加入右子树，再加入左子树
                if (current.right != null) {
                    queue.offer(current.right);
                }
                if (current.left != null) {
                    queue.offer(current.left);
                }
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        // 示例 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);

        Solution solution = new Solution();
        System.out.println(solution.rightSideView(root1)); // 输出 [1, 3, 4]

        // 示例 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);

        System.out.println(solution.rightSideView(root2)); // 输出 [1, 3, 4, 5]
    }
}

// 定义树的节点类
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}
```

### 解释：
1. **TreeNode** 类表示二叉树的节点，包含值、左子节点和右子节点。
2. **rightSideView** 方法使用 BFS（广度优先搜索）来层次遍历二叉树，队列用于存储当前层的节点。
3. 在每一层遍历时，记录当前层的最后一个节点，即从右侧可以看到的节点。
4. 每次遍历右子树，再遍历左子树，保证右侧节点优先进入队列。
5. 最终返回的 `result` 列表就是从右侧视角看到的节点值。

### 示例：
#### 示例 1：
```java
输入：root = [1,2,3,null,5,null,4]
输出：[1,3,4]
解释：从右侧看，首先看到节点1，然后是3（3的右子树4被隐藏在2后面），最后看到4。
```

#### 示例 2：
```java
输入：root = [1,2,3,4,null,null,null,5]
输出：[1,3,4,5]
解释：从右侧看，首先看到节点1，然后是3（3的右子树4被隐藏在2后面），接着看到4和5。
```

#### 示例 3：
```java
输入：root = [1,null,3]
输出：[1,3]
解释：从右侧看，首先看到1，然后是3。
```

#### 示例 4：
```java
输入：root = []
输出：[]
解释：没有节点，返回空列表。
```

### 时间和空间复杂度：
- **时间复杂度**：O(n)，n 是树的节点数。每个节点都被访问一次。
- **空间复杂度**：O(n)，队列中最多存储 n 个节点，最坏情况下是树的最底层。

这种方法通过广度优先搜索确保按层次遍历树，并且从右侧依次提取每层最右侧的节点，符合题目要求。