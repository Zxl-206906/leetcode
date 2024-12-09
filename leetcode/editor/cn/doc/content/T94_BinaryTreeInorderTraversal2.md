<p>给定一个二叉树的根节点 <code>root</code> ，返回 <em>它的 <strong>中序</strong>&nbsp;遍历</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/15/inorder_1.jpg" style="height: 200px; width: 125px;" /> 
<pre>
<strong>输入：</strong>root = [1,null,2,3]
<strong>输出：</strong>[1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>root = []
<strong>输出：</strong>[]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>root = [1]
<strong>输出：</strong>[1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目在范围 <code>[0, 100]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶:</strong>&nbsp;递归算法很简单，你可以通过迭代算法完成吗？</p>

<div><li>👍 2163</li><li>👎 0</li></div>


时间复杂度：O（n），遍历每个节点。

空间复杂度：O（h），压栈消耗，h 是二叉树的高度。


利用栈，去模拟递归。递归压栈的过程，就是保存现场，就是保存当前的变量，而解法一中当前有用的变量就是 node，所以我们用栈把每次的 node 保存起来即可。

模拟下递归的过程，只考虑 node 的压栈。



```text
//当前节点为空，出栈
if (node == null) {
    return;
}
//当前节点不为空
getAns(node.left, ans);  //压栈
ans.add(node.val); //出栈后添加
getAns(node.right, ans); //压栈
//左右子树遍历完，出栈
Copy
看一个具体的例子，想象一下吧。
```

```text

        1
      /   \
     2     3
    / \   /
   4   5 6

 push   push   push   pop     pop    push     pop     pop 
|   |  |   |  |_4_|  |   |   |   |  |   |    |   |   |   |  
|   |  |_2_|  |_2_|  |_2_|   |   |  |_5_|    |   |   |   |
|_1_|  |_1_|  |_1_|  |_1_|   |_1_|  |_1_|    |_1_|   |   |
ans                  add 4   add 2           add 5   add 1
[]                   [4]     [4 2]           [4 2 5] [4 2 5 1]
 push   push   pop          pop 
|   |  |   |  |   |        |   |  
|   |  |_6_|  |   |        |   |  
|_3_|  |_3_|  |_3_|        |   |
              add 6        add 3
              [4 2 5 1 6]  [4 2 5 1 6 3]
```
结合代码。
```java
public List<Integer> inorderTraversal(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = root;
    while (cur != null || !stack.isEmpty()) {
        //节点不为空一直压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.left; //考虑左子树
        }
        //节点为空，就出栈
        cur = stack.pop();
        //当前值加入
        ans.add(cur.val);
        //考虑右子树
        cur = cur.right;
    }
    return ans;
}
```

时间复杂度：O（n）。

空间复杂度：O（h），栈消耗，h 是二叉树的高度。