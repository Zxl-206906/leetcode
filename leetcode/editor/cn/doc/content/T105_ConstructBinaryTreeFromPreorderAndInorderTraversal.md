<p>给定两个整数数组&nbsp;<code>preorder</code> 和 <code>inorder</code>&nbsp;，其中&nbsp;<code>preorder</code> 是二叉树的<strong>先序遍历</strong>， <code>inorder</code>&nbsp;是同一棵树的<strong>中序遍历</strong>，请构造二叉树并返回其根节点。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" style="height: 302px; width: 277px;" /> 
<pre>
<strong>输入</strong><strong>:</strong> preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
<strong>输出:</strong> [3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> preorder = [-1], inorder = [-1]
<strong>输出:</strong> [-1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= preorder.length &lt;= 3000</code></li> 
 <li><code>inorder.length == preorder.length</code></li> 
 <li><code>-3000 &lt;= preorder[i], inorder[i] &lt;= 3000</code></li> 
 <li><code>preorder</code>&nbsp;和&nbsp;<code>inorder</code>&nbsp;均 <strong>无重复</strong> 元素</li> 
 <li><code>inorder</code>&nbsp;均出现在&nbsp;<code>preorder</code></li> 
 <li><code>preorder</code>&nbsp;<strong>保证</strong> 为二叉树的前序遍历序列</li> 
 <li><code>inorder</code>&nbsp;<strong>保证</strong> 为二叉树的中序遍历序列</li> 
</ul>

<div><li>👍 2426</li><li>👎 0</li></div>


根据给定的前序遍历 (`preorder`) 和中序遍历 (`inorder`)，可以通过递归的方法重建二叉树。以下是用 Java 实现的代码示例：

### 思路
1. **前序遍历**的第一个节点是当前子树的根节点。
2. **中序遍历**中根节点的位置将数组分成左子树和右子树部分。
3. 递归构建左子树和右子树。

---

### Java 代码实现

```java
import java.util.HashMap;
import java.util.Map;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class BuildTreeFromPreInOrder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 使用哈希表存储中序遍历的值和索引
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildSubTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inorderMap);
    }

    private TreeNode buildSubTree(int[] preorder, int preStart, int preEnd,
                                  int[] inorder, int inStart, int inEnd,
                                  Map<Integer, Integer> inorderMap) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // 根节点的值是前序遍历的第一个值
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // 根节点在中序遍历中的位置
        int rootIndex = inorderMap.get(rootVal);

        // 左子树的节点数
        int leftSize = rootIndex - inStart;

        // 构建左子树
        root.left = buildSubTree(preorder, preStart + 1, preStart + leftSize,
                inorder, inStart, rootIndex - 1, inorderMap);

        // 构建右子树
        root.right = buildSubTree(preorder, preStart + leftSize + 1, preEnd,
                inorder, rootIndex + 1, inEnd, inorderMap);

        return root;
    }

    public static void main(String[] args) {
        BuildTreeFromPreInOrder solution = new BuildTreeFromPreInOrder();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = solution.buildTree(preorder, inorder);

        // 测试: 输出结果 (可以编写遍历代码验证结果)
        printInOrder(root); // 输出 [9, 3, 15, 20, 7]
    }

    private static void printInOrder(TreeNode root) {
        if (root != null) {
            printInOrder(root.left);
            System.out.print(root.val + " ");
            printInOrder(root.right);
        }
    }
}
```

---

### 输入输出示例

#### 示例 1
**输入：**  
`preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]`  
**输出：**  
树的结构：
```
      3
     / \
    9  20
       / \
      15  7
```

#### 示例 2
**输入：**  
`preorder = [-1], inorder = [-1]`  
**输出：**  
树的结构：
```
    -1
```

---

### 复杂度分析
1. **时间复杂度：** O(n)
    - 每个节点访问一次。
    - 使用哈希表查找中序索引的操作是 O(1)，总共需要 n 次。
2. **空间复杂度：** O(n)
    - 哈希表占用 O(n)。
    - 递归调用的栈深度在最坏情况下为 O(n)。