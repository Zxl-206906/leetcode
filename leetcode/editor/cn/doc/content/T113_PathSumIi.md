<p>给你二叉树的根节点 <code>root</code> 和一个整数目标和 <code>targetSum</code> ，找出所有 <strong>从根节点到叶子节点</strong> 路径总和等于给定目标和的路径。</p>

<p><strong>叶子节点</strong> 是指没有子节点的节点。</p>

<div class="original__bRMd"> 
 <div> 
  <p>&nbsp;</p> 
 </div>
</div>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg" style="width: 500px; height: 356px;" /> 
<pre>
<strong>输入：</strong>root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
<strong>输出：</strong>[[5,4,11,2],[5,8,4,5]]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg" style="width: 212px; height: 181px;" /> 
<pre>
<strong>输入：</strong>root = [1,2,3], targetSum = 5
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1,2], targetSum = 0
<strong>输出：</strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点总数在范围 <code>[0, 5000]</code> 内</li> 
 <li><code>-1000 &lt;= Node.val &lt;= 1000</code></li> 
 <li><code>-1000 &lt;= targetSum &lt;= 1000</code></li> 
</ul>

<div><li>👍 1160</li><li>👎 0</li></div>


要找出所有从根节点到叶子节点的路径总和等于目标和的路径，我们可以使用 **回溯法** 解决。

### 思路
1. **递归遍历树**：
    - 使用深度优先搜索 (DFS) 遍历树。
    - 在遍历过程中，记录当前路径的节点值。
2. **回溯**：
    - 遍历到叶子节点时，检查路径和是否等于 `targetSum`。
    - 如果是，将当前路径加入结果集。
    - 无论是否符合条件，都需要回溯，移除当前节点值以便处理其他路径。
3. **叶子节点条件**：
    - 当前节点的左子树和右子树都为空时，为叶子节点。

### Java 实现代码
```java
import java.util.ArrayList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        dfs(root, targetSum, currentPath, result);
        return result;
    }

    private void dfs(TreeNode node, int targetSum, List<Integer> currentPath, List<List<Integer>> result) {
        if (node == null) {
            return;
        }
        // 添加当前节点到路径
        currentPath.add(node.val);
        // 如果是叶子节点且路径和等于目标和
        if (node.left == null && node.right == null && targetSum == node.val) {
            result.add(new ArrayList<>(currentPath)); // 将路径加入结果
        } else {
            // 递归到左右子树
            dfs(node.left, targetSum - node.val, currentPath, result);
            dfs(node.right, targetSum - node.val, currentPath, result);
        }
        // 回溯：移除当前节点
        currentPath.remove(currentPath.size() - 1);
    }

    public static void main(String[] args) {
        PathSumII solution = new PathSumII();

        // 示例 1
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(8);
        root1.left.left = new TreeNode(11);
        root1.left.left.left = new TreeNode(7);
        root1.left.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(13);
        root1.right.right = new TreeNode(4);
        root1.right.right.left = new TreeNode(5);
        root1.right.right.right = new TreeNode(1);

        System.out.println(solution.pathSum(root1, 22)); // 输出: [[5, 4, 11, 2], [5, 8, 4, 5]]

        // 示例 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);

        System.out.println(solution.pathSum(root2, 5)); // 输出: []

        // 示例 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);

        System.out.println(solution.pathSum(root3, 0)); // 输出: []
    }
}
```

### 执行流程（以示例 1 为例）
输入：`root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22`
1. **初始状态**：
    - 当前节点值为 `5`，路径为 `[5]`，剩余目标和为 `17`。
2. **左子树递归**：
    - 当前节点值为 `4`，路径为 `[5, 4]`，剩余目标和为 `13`。
    - 再进入节点 `11`，路径为 `[5, 4, 11]`，剩余目标和为 `2`。
    - 到达叶子节点 `7`，路径和不匹配，回溯。
    - 到达叶子节点 `2`，路径为 `[5, 4, 11, 2]`，和匹配，加入结果。
3. **右子树递归**：
    - 回到根节点 `5`，进入右子树 `8`，路径为 `[5, 8]`，剩余目标和为 `14`。
    - 再进入右子树 `4`，路径为 `[5, 8, 4]`，剩余目标和为 `5`。
    - 到达叶子节点 `5`，路径为 `[5, 8, 4, 5]`，和匹配，加入结果。
4. **返回结果**：`[[5, 4, 11, 2], [5, 8, 4, 5]]`

### 时间复杂度
- **O(N)**：每个节点访问一次，`N` 是节点总数。

### 空间复杂度
- **O(H)**：递归栈深度，`H` 是树的高度。