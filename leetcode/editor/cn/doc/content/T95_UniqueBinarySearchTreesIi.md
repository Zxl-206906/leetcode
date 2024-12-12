<p>给你一个整数 <code>n</code> ，请你生成并返回所有由 <code>n</code> 个节点组成且节点值从 <code>1</code> 到 <code>n</code> 互不相同的不同 <strong>二叉搜索树</strong><em> </em>。可以按 <strong>任意顺序</strong> 返回答案。</p>

<p>&nbsp;</p>

<div class="original__bRMd"> 
 <div> 
  <p><strong>示例 1：</strong></p> 
  <img alt="" src="https://assets.leetcode.com/uploads/2021/01/18/uniquebstn3.jpg" style="width: 600px; height: 148px;" /> 
  <pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>[[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
</pre> 
 </div>
</div>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>[[1]]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= n &lt;= 8</code></li> 
</ul>

<div><li>👍 1587</li><li>👎 0</li></div>


首先我们来了解一下什么是二叉搜索树。



> 二叉搜索树是一种二叉树的树形数据结构，其定义如下：
>
>
>
> 1.  空树是二叉搜索树。
> 2.  若二叉搜索树的左子树不为空，则其左子树上所有点的附加权值均小于其根节点的值。
> 3.  若二叉搜索树的右子树不为空，则其右子树上所有点的附加权值均大于其根节点的值。
> 4.  二叉搜索树的左右子树均为二叉搜索树。
>
>
>
> 												——引自[二叉搜索树 & 平衡树 - OI Wiki](https://oiwiki.org/ds/bst/)



简单来说，二叉搜索树需要满足：每个节点的左子树都比自己小、右子树都比自己大。



好，接下来我们要知道节点个数为n的二叉搜索树的个数是多少。



假设n个节点的 _二叉搜索树_ 的个数为$ G(n) $，$ f(i) $为以$ i $作为根节点的n个节点的二叉搜索树个数。



我们可以得到这样的式子：



$ G(n) = f(1) + f(2) + \dots + f(n)
$



并且当我们分析$ f(i) $的时候，还可以得到这样子一个式子：



$ f(i) = G(i-1) * G(n - i)
$



因为我们知道总数为n的话，当前节点的两个子树，必然是大小为`i-1`和`n-i`的两棵二叉搜索树。



综合一下两个公式我们能得到如下式子：



$ G(n) = G(0) * G(n - 1) + G(1) * G(n - 2) + \dots + G(n - 1) * G(0)
$



而这，其实就是卡特兰数。



简单来说，卡特兰数就是在某些特定限制条件下，可能的组合或排列的数量。



举一个常见的例子：你有一堆左括号和右括号，要组成一个正确的括号组合（即每个左括号都有一个与之对应的右括号），那么可能的组合数就是一个卡特兰数。



好，我们选择一个数作为根节点，然后分配左右子树，最后按照左右子树的大小继续递归建树即可得到答案。



```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    // 主方法，接收一个整数n作为参数
    public List<TreeNode> generateTrees(int n) {
        // 当n为0时，没有可生成的树，直接返回一个空的列表
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        // 调用辅助方法generateTrees，生成从1到n的所有可能的二叉搜索树
        return generateTrees(1, n);
    }

    // 辅助方法，接收两个整数作为参数，表示生成树的值的范围
    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> trees = new LinkedList<TreeNode>(); // 用于存储生成的所有树的列表

        // 当开始值大于结束值时，表示该区间无有效数字，返回一个包含null的列表
        if (start > end) {
            trees.add(null);
            return trees;
        }

        // 遍历从start到end的所有数字，将每一个数字作为根节点
        for (int i = start; i <= end; i++) {
            // 生成所有可能的左子树
            List<TreeNode> leftTrees = generateTrees(start, i - 1);
            // 生成所有可能的右子树
            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            // 对于每一颗左子树和右子树的组合，创建一个新的树
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode newTree = new TreeNode(i); // 创建一个新的树的根节点
                    newTree.left = left;  // 将当前左子树连接到新树的左侧
                    newTree.right = right; // 将当前右子树连接到新树的右侧
                    trees.add(newTree); // 将新生成的树添加到trees列表中
                }
            }
        }
        return trees; // 返回生成的所有树的列表
    }
}
```



当输入 `n = 3` 时，我们需要构造所有可能的二叉搜索树，其中树的节点值从 1 到 3。



遍历每个数字 `i` 从 1 到 3，将其视为根节点，并递归地为其左右子树生成所有可能的结构。



让我们模拟题解的过程：



1.  **i = 1 作为根节点**：
    - 左子树: 无
    - 右子树: `2,3`
        * 当 2 作为根节点：左子树无，右子树有 3
        * 当 3 作为根节点：左子树有 2，右子树无  
          结果：

```plain
1         1
 \         \
  2         3
   \       /
    3     2
```

2.  **i = 2 作为根节点**：
    - 左子树: `1`
    - 右子树: `3`  
      结果：

```plain
  2
 / \
1   3
```

3.  **i = 3 作为根节点**：
    - 左子树: `1,2`
        * 当 1 作为根节点：右子树有 2，左子树无
        * 当 2 作为根节点：左子树有 1，右子树无
    - 右子树: 无  
      结果：

```plain
    3       3
   /       /
  1       2
   \     /
    2   1
```



合并上述所有的结果，我们可以得到5个不同的二叉搜索树。

