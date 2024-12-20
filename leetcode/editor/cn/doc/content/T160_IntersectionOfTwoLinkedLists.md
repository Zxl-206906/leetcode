<p>给你两个单链表的头节点&nbsp;<code>headA</code> 和 <code>headB</code> ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 <code>null</code> 。</p>

<p>图示两个链表在节点 <code>c1</code> 开始相交<strong>：</strong></p>

<p><a href="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" target="_blank"><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png" style="height:130px; width:400px" /></a></p>

<p>题目数据 <strong>保证</strong> 整个链式结构中不存在环。</p>

<p><strong>注意</strong>，函数返回结果后，链表必须 <strong>保持其原始结构</strong> 。</p>

<p><strong>自定义评测：</strong></p>

<p><strong>评测系统</strong> 的输入如下（你设计的程序 <strong>不适用</strong> 此输入）：</p>

<ul> 
 <li><code>intersectVal</code> - 相交的起始节点的值。如果不存在相交节点，这一值为 <code>0</code></li> 
 <li><code>listA</code> - 第一个链表</li> 
 <li><code>listB</code> - 第二个链表</li> 
 <li><code>skipA</code> - 在 <code>listA</code> 中（从头节点开始）跳到交叉节点的节点数</li> 
 <li><code>skipB</code> - 在 <code>listB</code> 中（从头节点开始）跳到交叉节点的节点数</li> 
</ul>

<p>评测系统将根据这些输入创建链式数据结构，并将两个头节点 <code>headA</code> 和 <code>headB</code> 传递给你的程序。如果程序能够正确返回相交节点，那么你的解决方案将被 <strong>视作正确答案</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_1.png" target="_blank"><img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_1_1.png" style="height:130px; width:400px" /></a></p>

<pre>
<strong>输入：</strong>intersectVal = 8, listA = [4,1,8,4,5], listB = [5,6,1,8,4,5], skipA = 2, skipB = 3
<strong>输出：</strong>Intersected at '8'
<strong>解释：</strong>相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,6,1,8,4,5]。
在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
— 请注意相交节点的值不为 1，因为在链表 A 和链表 B 之中值为 1 的节点 (A 中第二个节点和 B 中第三个节点) 是不同的节点。换句话说，它们在内存中指向两个不同的位置，而链表 A 和链表 B 中值为 8 的节点 (A 中<font size="1">第三个</font>节点，B 中第四个节点) 在内存中指向相同的位置。
</pre>

<p>&nbsp;</p>

<p><strong>示例&nbsp;2：</strong></p>

<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_2.png" target="_blank"><img alt="" src="https://assets.leetcode.com/uploads/2021/03/05/160_example_2.png" style="height:136px; width:350px" /></a></p>

<pre>
<strong>输入：</strong>intersectVal&nbsp;= 2, listA = [1,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
<strong>输出：</strong>Intersected at '2'
<strong>解释：</strong>相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。
从各自的表头开始算起，链表 A 为 [1,9,1,2,4]，链表 B 为 [3,2,4]。
在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<p><a href="https://assets.leetcode.com/uploads/2018/12/13/160_example_3.png" target="_blank"><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png" style="height:126px; width:200px" /></a></p>

<pre>
<strong>输入：</strong>intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
<strong>输出：</strong>No intersection
<strong>解释：</strong>从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。
由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
这两个链表不相交，因此返回 null 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>listA</code> 中节点数目为 <code>m</code></li> 
 <li><code>listB</code> 中节点数目为 <code>n</code></li> 
 <li><code>1 &lt;= m, n &lt;= 3 * 10<sup>4</sup></code></li> 
 <li><code>1 &lt;= Node.val &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= skipA &lt;= m</code></li> 
 <li><code>0 &lt;= skipB &lt;= n</code></li> 
 <li>如果 <code>listA</code> 和 <code>listB</code> 没有交点，<code>intersectVal</code> 为 <code>0</code></li> 
 <li>如果 <code>listA</code> 和 <code>listB</code> 有交点，<code>intersectVal == listA[skipA] == listB[skipB]</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你能否设计一个时间复杂度 <code>O(m + n)</code> 、仅用 <code>O(1)</code> 内存的解决方案？</p>

<div><li>👍 2560</li><li>👎 0</li></div>

要解决这个问题，我们可以使用一种双指针方法，能够在 **O(m + n)** 的时间复杂度下找到链表的相交节点，同时使用 **O(1)** 的额外空间。

### 解题思路
1. **核心思想**：
    - 如果两个链表相交，那么从相交点到链表尾部的部分是相同的。
    - 我们可以使用两个指针 `pA` 和 `pB`，分别从 `headA` 和 `headB` 开始遍历。
    - 当 `pA` 到达链表末尾时，跳到 `headB`；当 `pB` 到达链表末尾时，跳到 `headA`。
    - 如果两个链表有相交部分，最终两个指针会在相交节点相遇；如果没有相交部分，则两个指针都会到达 `null`。

2. **步骤**：
    - 初始化两个指针 `pA` 和 `pB` 分别指向 `headA` 和 `headB`。
    - 当 `pA` 和 `pB` 不相等时，继续遍历：
        - 如果 `pA` 到达链表末尾，则将其指向 `headB`；
        - 如果 `pB` 到达链表末尾，则将其指向 `headA`。
    - 最后返回 `pA` 或 `pB`（若相交，则为相交节点；若不相交，则为 `null`）。

3. **时间复杂度**：
    - 两个指针最多遍历两个链表各一次，因此时间复杂度为 \(O(m + n)\)。

4. **空间复杂度**：
    - 使用了常量空间，仅用了两个指针，因此空间复杂度为 \(O(1)\)。

---

### Java 实现

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;

        // 初始化两个指针
        ListNode pA = headA;
        ListNode pB = headB;

        // 遍历两个链表
        while (pA != pB) {
            // 如果 pA 到达末尾，切换到 headB；否则继续向下移动
            pA = (pA == null) ? headB : pA.next;

            // 如果 pB 到达末尾，切换到 headA；否则继续向下移动
            pB = (pB == null) ? headA : pB.next;
        }

        // 返回相交节点或 null
        return pA;
    }
}
```

---

### 示例解析

#### 示例 1
- 输入：
  ```text
  listA = [4,1,8,4,5]
  listB = [5,6,1,8,4,5]
  skipA = 2, skipB = 3
  ```
- 步骤：
    - `pA` 从 `headA` 开始，`pB` 从 `headB` 开始。
    - 两个指针在节点值为 `8` 的位置相遇。
- 输出：返回节点值 `8`。

#### 示例 2
- 输入：
  ```text
  listA = [1,9,1,2,4]
  listB = [3,2,4]
  skipA = 3, skipB = 1
  ```
- 步骤：
    - 两个指针在节点值为 `2` 的位置相遇。
- 输出：返回节点值 `2`。

#### 示例 3
- 输入：
  ```text
  listA = [2,6,4]
  listB = [1,5]
  skipA = 3, skipB = 2
  ```
- 步骤：
    - 两个指针分别遍历完各自链表后均到达 `null`。
- 输出：返回 `null`。

---

### 进阶思考
这种双指针方法不需要额外的存储空间，非常适合处理大型链表数据。如果链表较小且可以接受额外空间，也可以使用哈希表记录节点来检查相交点。