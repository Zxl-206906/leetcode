<p>给你一个链表的头节点 <code>head</code> ，旋转链表，将链表每个节点向右移动&nbsp;<code>k</code><em>&nbsp;</em>个位置。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/rotate1.jpg" style="width: 450px;" /> 
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[4,5,1,2,3]
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/roate2.jpg" style="width: 305px; height: 350px;" /> 
<pre>
<strong>输入：</strong>head = [0,1,2], k = 4
<strong>输出：</strong>[2,0,1]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>链表中节点的数目在范围 <code>[0, 500]</code> 内</li> 
 <li><code>-100 &lt;= Node.val &lt;= 100</code></li> 
 <li><code>0 &lt;= k &lt;= 2 * 10<sup>9</sup></code></li> 
</ul>

<div><li>👍 1102</li><li>👎 0</li></div>


### 分析


我们先把如何 **”旋转“** 链表的这个问题放在一边，来思考向右移动 `k` 个位置后链表的状态。



观察例2的过程，我们可以发现，当向右移动`sizeOfList`次之后，链表会恢复原有的样子。



换言之，我们并不需要移动完整的`k`次，而是仅仅需要移动`k % sizeOfList`次即可。



那么我们接下来来看看如何 **”旋转“** 链表。



旋转链表需要把后`k`个节点移动到最前面，并且把最后一个节点的下一个节点设置成原本的头节点。



能不能把顺序倒过来呢？



我们先把最后一个节点的下一个节点设置为原本的头节点，让整个链表成为一个环形的循环链表，然后再在距离原本头节点`sizeOfList - k % sizeOfList`处断开，这样子就能把倒数第`k`个节点变成新的头节点，这时候，返回它就可以了。
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(k == 0 || head == null || head.next == null)
            return head;
        int sizeOfList = 1;
        ListNode tail = head;
        while(tail.next != null){
            tail = tail.next;//获取整个链表最末端的节点
            sizeOfList++;//计算链表长度
        }
        int breakPos = sizeOfList - k % sizeOfList;//断开的位置
        if(breakPos == sizeOfList)
            return head;
        tail.next = head;//将链表变为循环链表
        while(breakPos-- > 0){
            tail = tail.next;//移动到应该断开的位置
        }
        ListNode newHead = tail.next;
        tail.next = null;//断开
        return newHead;
    }
}
```


时间复杂度：$ O(n) $



`n`为整个链表的长度（即`sizeOfList`），因为我们找出了向右移动`sizeOfList`次后，链表会恢复到原本的样子的这一特性，从而不用去频繁执行移动操作，整个算法流程最差情况（当`breakPos == n - 1`）时，我们才需要遍历整个链表`2n - 1`次，其余情况均不会超过`2n - 1`。所以时间复杂度为$ O(n) $。

