<p>给你一个链表的头节点 <code>head</code> 和一个特定值<em> </em><code>x</code> ，请你对链表进行分隔，使得所有 <strong>小于</strong> <code>x</code> 的节点都出现在 <strong>大于或等于</strong> <code>x</code> 的节点之前。</p>

<p>你应当 <strong>保留</strong> 两个分区中每个节点的初始相对位置。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2021/01/04/partition.jpg" style="width: 662px; height: 222px;" /> 
<pre>
<strong>输入：</strong>head = [1,4,3,2,5,2], x = 3
<strong>输出</strong>：[1,2,2,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>head = [2,1], x = 2
<strong>输出</strong>：[1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目在范围 <code>[0, 200]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li><code>-200 &lt;= x &lt;= 200</code></li> 
</ul>

<div><li>👍 876</li><li>👎 0</li></div>


本题考察的是对链表的遍历和基础操作。



思路其实很简单，遍历一遍链表，然后按照节点元素的大小，将其分别存储到两个链表中，最后再将较大的链表链接到较小的链表，即可完成本题。



题目的难点主要集中在链表的操作上，有时我们常常会被链表的一些基础操作绕晕，要不被绕晕，就要对链表这一基础数据结构烂熟于心。



因为要用两个头节点存放链表的较小元素和较大元素，同时为了防止其中任一链表为空，我们可以这样来实现。



利用`smallListHead`和`largeListHead`两个头节点来存储两个链表，然后遍历链表，按照节点大小将节点插入两个链表即可。



最后，记得将存储较大元素的链表尾节点的`next`设置为`null`，因为这时的尾节点不一定是原先链表的尾节点，可能会导致我们的最终答案还多出一节。


**使用两个指针，分别维护小于 x 和大于等于 x 的两个链表，遍历原链表后将得到的两链表拼接即可。**