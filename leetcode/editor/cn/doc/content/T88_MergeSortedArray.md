<p>给你两个按 <strong>非递减顺序</strong> 排列的整数数组&nbsp;<code>nums1</code><em> </em>和 <code>nums2</code>，另有两个整数 <code>m</code> 和 <code>n</code> ，分别表示 <code>nums1</code> 和 <code>nums2</code> 中的元素数目。</p>

<p>请你 <strong>合并</strong> <code>nums2</code><em> </em>到 <code>nums1</code> 中，使合并后的数组同样按 <strong>非递减顺序</strong> 排列。</p>

<p><strong>注意：</strong>最终，合并后数组不应由函数返回，而是存储在数组 <code>nums1</code> 中。为了应对这种情况，<code>nums1</code> 的初始长度为 <code>m + n</code>，其中前 <code>m</code> 个元素表示应合并的元素，后 <code>n</code> 个元素为 <code>0</code> ，应忽略。<code>nums2</code> 的长度为 <code>n</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
<strong>输出：</strong>[1,2,2,3,5,6]
<strong>解释：</strong>需要合并 [1,2,3] 和 [2,5,6] 。
合并结果是 [<em><strong>1</strong></em>,<em><strong>2</strong></em>,2,<em><strong>3</strong></em>,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [1], m = 1, nums2 = [], n = 0
<strong>输出：</strong>[1]
<strong>解释：</strong>需要合并 [1] 和 [] 。
合并结果是 [1] 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [0], m = 0, nums2 = [1], n = 1
<strong>输出：</strong>[1]
<strong>解释：</strong>需要合并的数组是 [] 和 [1] 。
合并结果是 [1] 。
注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>nums1.length == m + n</code></li> 
 <li><code>nums2.length == n</code></li> 
 <li><code>0 &lt;= m, n &lt;= 200</code></li> 
 <li><code>1 &lt;= m + n &lt;= 200</code></li> 
 <li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计实现一个时间复杂度为 <code>O(m + n)</code> 的算法解决此问题吗？</p>

<div><li>👍 2603</li><li>👎 0</li></div>



因为`nums1`在`m`之后的位置（即`m`到`m+n-1`），是没有被占用的，所以我们可以利用这段空间，从后往前依次确定，即从大到小来获取对应的元素即可。



```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // pos 指向 nums1 的最后一个位置
        int pos = m + n - 1;
        // 将 m 和 n 减一，让它们直接作为 nums1 和 nums2 的索引
        m--;
        n--;

        // 循环条件是 m 或 n 任一个还没遍历完
        while (m >= 0 || n >= 0) {
            // 如果 nums1 已经遍历完，只需将 nums2 的元素复制到 nums1
            if (m == -1) {
                nums1[pos--] = nums2[n--];
            } else {
                // 如果 nums2 已经遍历完，则 nums1 已在正确位置，只需移动 m
                if (n == -1) {
                    nums1[pos--] = nums1[m--];
                } else {
                    // 比较 nums1 和 nums2 当前元素，将较大的元素放到 nums1 的 pos 位置
                    if (nums1[m] >= nums2[n]) {
                        nums1[pos--] = nums1[m--];
                    } else {
                        nums1[pos--] = nums2[n--];
                    }
                }
            }
        }
    }
}
```



**解释：**



+  这个方法的核心思想是从两个数组的尾部开始比较，并从 `nums1` 的尾部开始填充结果。这样我们可以直接在 `nums1` 上操作，而不需要额外的存储空间。
+  在每一步，我们比较 `nums1[m]` 和 `nums2[n]`，并将较大的元素放在 `nums1[pos]` 的位置，然后递减相应的指针（下标）。
+  如果 `nums1` 的元素都被遍历完了（即 `m` 为 `-1`），我们就直接将 `nums2` 的元素复制到 `nums1`。
+  如果 `nums2` 的元素都被遍历完了（即 `n` 为 `-1`），那么 `nums1` 的元素已经在正确的位置上，不需要做任何事情。



我们来模拟一下整个过程。



考虑下面的例子:



`nums1 = [1,3,5,0,0,0]`, m=3 和 `nums2 = [2,4,6]`, n=3



这是两个有序数组，我们希望合并 `nums2` 到 `nums1`。



我们的指针（下标）分别是：`m`, `n`, 和 `pos`。



初始化状态:



```plain
nums1:  1  3  5  0  0  0
index:  0  1  2  3  4  5

nums2:  2  4  6
index:  0  1  2

m=2, n=2, pos=5
```



开始合并：



```plain
1. nums1[m] 是 5, nums2[n] 是 6. 6 更大，所以放入 pos 位置。
nums1:  1  3  5  0  0  6
n--, pos--

2. nums1[m] 是 5, nums2[n] 是 4. 5 更大，所以放入 pos 位置。
nums1:  1  3  5  0  5  6
m--, pos--

3. nums1[m] 是 3, nums2[n] 是 4. 4 更大，所以放入 pos 位置。
nums1:  1  3  5  4  5  6
n--, pos--

4. nums1[m] 是 3, nums2[n] 是 2. 3 更大，所以放入 pos 位置。
nums1:  1  3  3  4  5  6
m--, pos--

5. nums1[m] 是 1, nums2[n] 是 2. 2 更大，所以放入 pos 位置。
nums1:  1  2  3  4  5  6
n--, pos--

6. 最后，我们只剩下 nums1 的第一个元素。但是它已经在正确的位置上，所以不需要做任何事情。
```



结束后，nums1 完整地包含了合并后的有序数组。



这种方法的时间复杂度是 (O(m+n))，而空间复杂度是 (O(1))。