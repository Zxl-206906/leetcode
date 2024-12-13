<p>给定两个整数数组 <code>inorder</code> 和 <code>postorder</code> ，其中 <code>inorder</code> 是二叉树的中序遍历， <code>postorder</code> 是同一棵树的后序遍历，请你构造并返回这颗&nbsp;<em>二叉树</em>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/tree.jpg" /> 
<pre>
<b>输入：</b>inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
<b>输出：</b>[3,9,20,null,null,15,7]
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<b>输入：</b>inorder = [-1], postorder = [-1]
<b>输出：</b>[-1]
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;= inorder.length &lt;= 3000</code></li> 
 <li><code>postorder.length == inorder.length</code></li> 
 <li><code>-3000 &lt;= inorder[i], postorder[i] &lt;= 3000</code></li> 
 <li><code>inorder</code>&nbsp;和&nbsp;<code>postorder</code>&nbsp;都由 <strong>不同</strong> 的值组成</li> 
 <li><code>postorder</code>&nbsp;中每一个值都在&nbsp;<code>inorder</code>&nbsp;中</li> 
 <li><code>inorder</code>&nbsp;<strong>保证</strong>是树的中序遍历</li> 
 <li><code>postorder</code>&nbsp;<strong>保证</strong>是树的后序遍历</li> 
</ul>

<div><li>👍 1291</li><li>👎 0</li></div>


根据给定的中序遍历 (`inorder`) 和后序遍历 (`postorder`)，可以通过递归的方法重建二叉树。

### 解题思路
1. **后序遍历特性**：后序遍历的最后一个元素是当前子树的根节点。
2. **中序遍历特性**：在中序遍历中，根节点将数组分成左子树和右子树。
3. **递归构造**：递归处理中序遍历的左右两部分，结合后序遍历构造树。

---

### Java 实现

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

public class BuildTreeFromInPostOrder {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // 构造哈希表，快速定位中序遍历中的根节点位置
        Map<Integer, Integer> inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildSubTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, inorderMap);
    }

    private TreeNode buildSubTree(int[] inorder, int inStart, int inEnd,
                                  int[] postorder, int postStart, int postEnd,
                                  Map<Integer, Integer> inorderMap) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        // 获取根节点值（后序遍历的最后一个值）
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // 找到根节点在中序遍历中的位置
        int rootIndex = inorderMap.get(rootVal);

        // 计算左子树的节点数量
        int leftSize = rootIndex - inStart;

        // 递归构造左子树
        root.left = buildSubTree(inorder, inStart, rootIndex - 1,
                postorder, postStart, postStart + leftSize - 1, inorderMap);

        // 递归构造右子树
        root.right = buildSubTree(inorder, rootIndex + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1, inorderMap);

        return root;
    }

    public static void main(String[] args) {
        BuildTreeFromInPostOrder solution = new BuildTreeFromInPostOrder();

        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        TreeNode root = solution.buildTree(inorder, postorder);

        // 测试：输出结果（可以通过遍历验证树结构）
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

### 示例输入输出

#### 示例 1
**输入：**  
`inorder = [9,3,15,20,7]`  
`postorder = [9,15,7,20,3]`

**输出：**  
构造出的二叉树：
```
      3
     / \
    9  20
       / \
      15  7
```

---

### 复杂度分析
1. **时间复杂度：** O(n)
    - 遍历中序和后序数组构建哈希表，递归构造二叉树。
2. **空间复杂度：** O(n)
    - 哈希表占用 O(n)，递归调用的栈深度最坏情况下为 O(n)。
      以下是执行流程的详细解释：

### 核心思想
通过中序遍历和后序遍历恢复二叉树：
1. **后序遍历特性**：后序遍历的最后一个元素是当前子树的根节点。
2. **中序遍历特性**：中序遍历中，根节点将数组分为左子树和右子树。
3. 结合这两个特性，通过递归构造二叉树。

---

### 示例输入
```java
int[] inorder = {9, 3, 15, 20, 7};
int[] postorder = {9, 15, 7, 20, 3};
```

---

### 执行流程
以下是代码的逐步执行流程，构造这棵树：

```
       3
      / \
     9  20
        / \
       15  7
```

---

#### **步骤 1：确定根节点**
1. 后序遍历的最后一个元素是 `3`。
    - 将 `3` 作为当前树的根节点。
2. 在中序遍历中找到 `3` 的位置：
    - `3` 的索引是 1。
3. 根据中序遍历：
    - 左子树：中序遍历的左半部分 `{9}`。
    - 右子树：中序遍历的右半部分 `{15, 20, 7}`。

---

#### **步骤 2：递归构造右子树**
1. 右子树的后序遍历为 `{15, 7, 20}`，中序遍历为 `{15, 20, 7}`。
2. 后序遍历的最后一个元素是 `20`。
    - 将 `20` 作为右子树的根节点。
3. 在右子树的中序遍历中找到 `20` 的位置：
    - `20` 的索引是 1。
4. 根据中序遍历：
    - 左子树：中序遍历的左半部分 `{15}`。
    - 右子树：中序遍历的右半部分 `{7}`。

---

#### **步骤 3：递归构造右子树的左子树**
1. 右子树左子树的后序遍历为 `{15}`，中序遍历为 `{15}`。
2. 后序遍历的最后一个元素是 `15`。
    - 将 `15` 作为右子树左子树的根节点。
3. 中序遍历中仅剩一个节点，无需再递归，返回节点 `15`。

---

#### **步骤 4：递归构造右子树的右子树**
1. 右子树右子树的后序遍历为 `{7}`，中序遍历为 `{7}`。
2. 后序遍历的最后一个元素是 `7`。
    - 将 `7` 作为右子树右子树的根节点。
3. 中序遍历中仅剩一个节点，无需再递归，返回节点 `7`。

---

#### **步骤 5：递归构造左子树**
1. 左子树的后序遍历为 `{9}`，中序遍历为 `{9}`。
2. 后序遍历的最后一个元素是 `9`。
    - 将 `9` 作为左子树的根节点。
3. 中序遍历中仅剩一个节点，无需再递归，返回节点 `9`。

---

### 完整树构造
结合以上步骤，最终构造出的二叉树为：
```
       3
      / \
     9  20
        / \
       15  7
```

---

### 递归深度与复杂度
1. **时间复杂度**：O(n)
    - 每次递归查找中序遍历中的根节点索引，构造左右子树。
2. **空间复杂度**：O(n)
    - 使用哈希表存储中序遍历索引，递归调用消耗栈空间。