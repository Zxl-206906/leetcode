<p>给你两棵二叉树的根节点 <code>p</code> 和 <code>q</code> ，编写一个函数来检验这两棵树是否相同。</p>

<p>如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex1.jpg" style="width: 622px; height: 182px;" /> 
<pre>
<strong>输入：</strong>p = [1,2,3], q = [1,2,3]
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex2.jpg" style="width: 382px; height: 182px;" /> 
<pre>
<strong>输入：</strong>p = [1,2], q = [1,null,2]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/12/20/ex3.jpg" style="width: 622px; height: 182px;" /> 
<pre>
<strong>输入：</strong>p = [1,2,1], q = [1,1,2]
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>两棵树上的节点数目都在范围 <code>[0, 100]</code> 内</li> 
 <li><code>-10<sup>4</sup> &lt;= Node.val &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 1191</li><li>👎 0</li></div>


本题我们还是使用解决二叉树问题中最常用的办法——**“递归”**。



假设我们现在获得了两棵树的根节点（`p`和`q`），一眼看出他俩是相同的很难，但也有例外，比如说两个都是`null`，空树自然是一样的。



再换另外一个角度，想要一眼看出两棵树是不同的，只要`p.val != q.val`，我们就能分辨出。



至于其他的情况，就不能这么简单地分辨了，因为涉及到左子树和右子树相同的情况，但是不要忘记——**递归**！



只要继续递归处理各自的左子树和右子树，问题就迎刃而解了，换句话说，`p`的左子树必然和`q`的左子树是相同的，`p`的右子树必然和`q`的右子树也是相同的，而左子树和右子树同样也是二叉树。



这时候整个算法的大概框架就浮现出来了——以递归为基础，先判断我们前面提到的两种最直观的情况，否则就递归判断左子树和右子树！

