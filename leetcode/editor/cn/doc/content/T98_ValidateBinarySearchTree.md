<p>给你一个二叉树的根节点 <code>root</code> ，判断其是否是一个有效的二叉搜索树。</p>

<p><strong>有效</strong> 二叉搜索树定义如下：</p>

<ul> 
 <li>节点的左<span data-keyword="subtree">子树</span>只包含<strong> 小于 </strong>当前节点的数。</li> 
 <li>节点的右子树只包含 <strong>大于</strong> 当前节点的数。</li> 
 <li>所有左子树和右子树自身必须也是二叉搜索树。</li> 
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree1.jpg" style="width: 302px; height: 182px;" /> 
<pre>
<strong>输入：</strong>root = [2,1,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/01/tree2.jpg" style="width: 422px; height: 292px;" /> 
<pre>
<strong>输入：</strong>root = [5,1,4,null,null,3,6]
<strong>输出：</strong>false
<strong>解释：</strong>根节点的值是 5 ，但是右子节点的值是 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>树中节点数目范围在<code>[1, 10<sup>4</sup>]</code> 内</li> 
 <li><code>-2<sup>31</sup> &lt;= Node.val &lt;= 2<sup>31</sup> - 1</code></li> 
</ul>

<div><li>👍 2462</li><li>👎 0</li></div>

**树的本质就是递归**，相信这个朴素的道理大家都能懂，二叉搜索树也不例外，我们仍然可以通过递归来检验当前二叉树是否为二叉搜索树。



观察一下题目中的条件：



> + 节点的左子树只包含 **小于** 当前节点的数。
> + 节点的右子树只包含 **大于** 当前节点的数。
> + 所有左子树和右子树自身必须也是二叉搜索树。



这就把解题的思路告诉我们了：



+ 每个节点的左子树中的最大值要小于当前节点的值
+ 每个节点的右子树中的最小值要大于当前节点的值



也就是说，对于任意节点 n，其左子树中的所有节点的值都小于 n.val，其右子树中的所有节点的值都大于 n.val。



最后注意一个非常关键的点：**空树也是二叉搜索树**，这道题便能解开了。

对于最初的根节点，我们不需要对其进行任何限制，所以我们可以直接将限制设置为`Long.MIN_VALUE`和`Long.MAX_VALUE`。



假如 root 为 `[5,1,4,null,null,3,6]`，我们来验证一下，首先构建这棵树的结构：



```plain
    5
   / \
  1   4
     / \
    3   6
```



接下来，我们按照题解的过程一步步验证这是否是一个有效的二叉搜索树（BST）：



1.  调用 `isValidBST` 根节点为 5，上下界为 `Long.MIN_VALUE` 和 `Long.MAX_VALUE`。
2.  节点 5 在上下界 `Long.MIN_VALUE` 和 `Long.MAX_VALUE` 之间，因此有效。接下来：
    - 对左子树调用 `isValidBST`，上界更新为 5。
    - 对右子树调用 `isValidBST`，下界更新为 5。
3.  在节点 1 上：
    - 节点 1 在上下界 `Long.MIN_VALUE` 和 5 之间，因此有效。
    - 因为节点 1 没有子节点，所以其左右子树都是空的，对它们的递归调用将返回 `true`。
4.  在节点 4 上：
    - 节点 4 在上下界 5 和 `Long.MAX_VALUE` 之间，因此有效。
    - 对左子树调用 `isValidBST`，上界更新为 4。
    - 对右子树调用 `isValidBST`，下界更新为 4。
5.  在节点 3 上：
    - 节点 3 在上下界 `Long.MIN_VALUE` 和 4 之间，但是节点 3 应该大于所有祖先节点中在它右边的，也就是它应该大于 5。因此，它违反了 BST 的条件，返回 `false`。
6.  在节点 6 上：
    - 尽管节点 6 在上下界 4 和 `Long.MAX_VALUE` 之间，但我们不会到达这里，因为它的左兄弟（节点 3）已经导致了失败的结果。



综上，当遇到节点 3 的时候，它没有满足BST的条件，所以整棵树不是BST，验证方法将返回 `false`。



假如 root 为 `[2,1,3]`，这棵树的结构如下：



```plain
  2
 / \
1   3
```



按照题解的过程验证这是否是一个有效的二叉搜索树（BST）：



1.  调用 `isValidBST` 根节点为 2，上下界为 `Long.MIN_VALUE` 和 `Long.MAX_VALUE`。
2.  节点 2 在上下界 `Long.MIN_VALUE` 和 `Long.MAX_VALUE` 之间，因此有效。接下来：
    - 对左子树调用 `isValidBST`，上界更新为 2。
    - 对右子树调用 `isValidBST`，下界更新为 2。
3.  在节点 1 上：
    - 节点 1 在上下界 `Long.MIN_VALUE` 和 2 之间，因此有效。
    - 节点 1 没有子节点，所以其左右子树都是空的，对它们的递归调用将返回 `true`。
4.  在节点 3 上：
    - 节点 3 在上下界 2 和 `Long.MAX_VALUE` 之间，因此有效。
    - 节点 3 没有子节点，所以其左右子树都是空的，对它们的递归调用将返回 `true`。



由于所有节点都在各自的上下界之内，并且没有违反BST的特性，所以整棵树符合BST的定义，验证方法将返回 `true`。

为什么要用Long类型来进行限制呢？



在这个题解中，使用 `Long` 类型的 `MIN_VALUE` 和 `MAX_VALUE` 作为边界，是因为二叉搜索树的定义中对于任何节点来说，其左子树上的所有节点的值都要小于该节点的值，而右子树上的所有节点的值都要大于该节点的值。为了通用性，根节点的值可以是任何 `Integer` 类型内的值，包括 `Integer.MIN_VALUE` 和 `Integer.MAX_VALUE`。



如果我们仅使用 `Integer` 类型的边界来初始化，那么当根节点的值恰好是 `Integer.MIN_VALUE` 或 `Integer.MAX_VALUE` 时，我们就无法设置一个比 `Integer.MIN_VALUE` 更小或比 `Integer.MAX_VALUE` 更大的边界值了。这将导致算法不能正确地处理这些边界情况。



通过使用 `Long` 类型的 `MIN_VALUE` 和 `MAX_VALUE`，我们确保了即使是 `Integer` 范围的最小或最大值也会有有效的边界检查。这是一种编程上的预防措施，以确保算法对所有合法的 `Integer` 值都是健壮的。在实际情况下，对于二叉搜索树的每个节点，其值都是一个 `int`，但在递归检查时，我们可以用更大范围的 `long` 类型来避免边界条件的问题。
