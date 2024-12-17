<p>给定一个单链表的头节点 &nbsp;<code>head</code>&nbsp;，其中的元素 <strong>按升序排序</strong> ，将其转换为 <span data-keyword="height-balanced">平衡</span> 二叉搜索树。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<p><img src="https://assets.leetcode.com/uploads/2020/08/17/linked.jpg" style="height: 388px; width: 500px;" /></p>

<pre>
<strong>输入:</strong> head = [-10,-3,0,5,9]
<strong>输出:</strong> [0,-3,9,-10,null,5]
<strong>解释:</strong> 一个可能的答案是[0，-3,9，-10,null,5]，它表示所示的高度平衡的二叉搜索树。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> head = []
<strong>输出:</strong> []
</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>head</code>&nbsp;中的节点数在<code>[0, 2 * 10<sup>4</sup>]</code>&nbsp;范围内</li> 
 <li><code>-10<sup>5</sup>&nbsp;&lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
</ul>

<div><li>👍 930</li><li>👎 0</li></div>

要将一个升序排列的单链表转换成平衡的二叉搜索树（BST），我们可以借鉴**二分查找**的思想，利用链表的升序特性来递归地构建树。具体的步骤如下：

### 思路

1. **中间节点作为根节点**：对于平衡二叉搜索树，我们希望每次选择当前链表的中间节点作为树的根节点。中间节点的左子树和右子树分别是中间节点左右两部分链表所构建的树。

2. **递归构建树**：通过递归的方式，逐渐将链表拆分成更小的子链表，直到链表为空或只包含一个元素为止。

3. **如何获取中间节点**：
    - 使用快慢指针法来寻找链表的中间节点。快指针每次走两步，慢指针每次走一步。当快指针到达链表的末尾时，慢指针正好指向中间节点。

4. **构建二叉搜索树**：选择中间节点作为根节点，然后递归地构建左右子树。

### 代码实现

```java
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val) { this.val = val; }
}

public class Solution {

    // 计算链表长度
    private ListNode head;
    
    public TreeNode sortedListToBST(ListNode head) {
        this.head = head;
        int size = getSize(head);
        return sortedListToBSTHelper(0, size - 1);
    }

    // 获取链表长度
    private int getSize(ListNode head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    // 递归函数，构建平衡二叉搜索树
    private TreeNode sortedListToBSTHelper(int left, int right) {
        if (left > right) {
            return null;
        }

        // 递归构建左子树
        int mid = (left + right) / 2;
        TreeNode leftChild = sortedListToBSTHelper(left, mid - 1);

        // 当前节点就是树的根节点
        TreeNode root = new TreeNode(head.val);
        root.left = leftChild;

        // 移动链表指针到下一个节点
        head = head.next;

        // 递归构建右子树
        root.right = sortedListToBSTHelper(mid + 1, right);

        return root;
    }
}
```

### 解释：

1. **ListNode类**：表示链表节点，包含值`val`和指向下一个节点的`next`指针。
2. **TreeNode类**：表示二叉树节点，包含值`val`、指向左子树的`left`指针和指向右子树的`right`指针。
3. **sortedListToBST**：主函数，首先通过`getSize`获取链表的长度，然后调用`sortedListToBSTHelper`递归地构建树。
4. **getSize**：计算链表的长度，返回链表的元素个数。
5. **sortedListToBSTHelper**：递归函数，通过快慢指针思想和二分法构建平衡的二叉搜索树。每次递归时，它会首先递归构建左子树，然后将当前链表的头节点作为当前子树的根节点，接着递归构建右子树。

### 复杂度分析：
- **时间复杂度**：O(n)，其中n是链表的节点数。每个节点只会被访问一次，因此时间复杂度是线性的。
- **空间复杂度**：O(log n)，递归调用栈的深度是树的高度，而树的高度是O(log n)（由于是平衡树）。
