<p>给你一个整数数组 <code>nums</code> ，其中元素已经按 <strong>升序</strong> 排列，请你将其转换为一棵 <span data-keyword="height-balanced">平衡</span> 二叉搜索树。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree1.jpg" style="width: 302px; height: 222px;" /> 
<pre>
<strong>输入：</strong>nums = [-10,-3,0,5,9]
<strong>输出：</strong>[0,-3,9,-10,null,5]
<strong>解释：</strong>[0,-10,5,null,-3,null,9] 也将被视为正确答案：
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree2.jpg" style="width: 302px; height: 222px;" />
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/btree.jpg" style="width: 342px; height: 142px;" /> 
<pre>
<strong>输入：</strong>nums = [1,3]
<strong>输出：</strong>[3,1]
<strong>解释：</strong>[1,null,3] 和 [3,1] 都是高度平衡二叉搜索树。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li> 
 <li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li> 
 <li><code>nums</code> 按 <strong>严格递增</strong> 顺序排列</li> 
</ul>

<div><li>👍 1581</li><li>👎 0</li></div>


为了将一个已按升序排列的整数数组转换为一棵平衡二叉搜索树（BST），我们可以利用二分查找的思想。平衡二叉搜索树的特点是左右子树的高度差不超过 1，而升序排列的数组的中间元素是最适合成为树的根节点的。

### 解题思路：

1. **选择中间元素作为根节点**：
    - 如果数组已经排序，取数组的中间元素作为树的根节点，这样可以保证树的左右子树的大小平衡。

2. **递归构建左右子树**：
    - 将数组的左半部分递归构建为左子树，右半部分递归构建为右子树。

3. **结束条件**：
    - 当数组为空时，返回 `null`。

### 代码实现：

```java
// 定义二叉树节点结构
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        // 递归终止条件
        if (left > right) {
            return null;
        }

        // 取中间元素作为根节点
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // 递归构建左右子树
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);

        return root;
    }
}
```

### 解释：
1. **`sortedArrayToBST`**：这是外部调用的方法，接受排序数组 `nums` 作为输入。
2. **`sortedArrayToBSTHelper`**：这是一个递归方法，负责在给定的数组范围内构建平衡二叉搜索树。它接收三个参数：
    - `nums`：数组本身。
    - `left` 和 `right`：当前子数组的起始和结束索引。
3. **递归构建树**：
    - 每次取中间的元素作为树的根节点。
    - 左子树递归处理 `nums[left..mid-1]`，右子树递归处理 `nums[mid+1..right]`。
4. **返回值**：递归的返回值是一个 `TreeNode`，表示树的根节点。

### 时间复杂度：
- 时间复杂度为 O(n)，其中 n 是数组的长度。每次递归都将数组分为两半，因此最多进行 O(log n) 次递归，且每次递归都涉及到一个常数时间的操作（取中间值并构建树节点）。

### 空间复杂度：
- 空间复杂度为 O(log n)，这是因为递归调用的栈深度最多为树的高度，而树的高度为 O(log n)。

### 示例：
1. 输入：`nums = [-10, -3, 0, 5, 9]`
   输出：
   ```
       0
      / \
    -3   9
   /     /
-10    5
   ```

2. 输入：`nums = [1, 3]`
   输出：
   ```
     3
    /
1
   ```

这个方法能保证生成一棵平衡二叉搜索树，且结构上是正确的。