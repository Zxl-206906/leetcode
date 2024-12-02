<p>给定一个包含红色、白色和蓝色、共&nbsp;<code>n</code><em> </em>个元素的数组
 <meta charset="UTF-8" />&nbsp;<code>nums</code>&nbsp;，<strong><a href="https://baike.baidu.com/item/%E5%8E%9F%E5%9C%B0%E7%AE%97%E6%B3%95" target="_blank">原地</a>&nbsp;</strong>对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。</p>

<p>我们使用整数 <code>0</code>、&nbsp;<code>1</code> 和 <code>2</code> 分别表示红色、白色和蓝色。</p>

<ul> 
</ul>

<p>必须在不使用库内置的 sort 函数的情况下解决这个问题。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,2,1,1,0]
<strong>输出：</strong>[0,0,1,1,2,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,0,1]
<strong>输出：</strong>[0,1,2]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>n == nums.length</code></li> 
 <li><code>1 &lt;= n &lt;= 300</code></li> 
 <li><code>nums[i]</code> 为 <code>0</code>、<code>1</code> 或 <code>2</code></li> 
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul> 
 <li>你能想出一个仅使用常数空间的一趟扫描算法吗？</li> 
</ul>

<div><li>👍 1854</li><li>👎 0</li></div>

题目的最后一句话，很容易误导我们走上排序算法的“不归路”，从而想到快速排序，但是快速排序算法真的是最优解吗？

并不是！

因为即便是快速排序，最快也只能够做到$ O(n\log{n}) $，但是有一种更快的排序算法——桶排！

我们可以利用一个`int[] bucket`来记录每个数字出现的次数，例如：如果`5`出现了3次，那么`bucket[5]`就等于3。

基于这样的想法，我们只需要遍历一次`nums[]`，就能得出每个数字到底有几个，然后再遍历`bucket[]`将其中的数字依次按照数量取出来，不就能够在$ O(n) $的时间内解决问题了吗？

是的，这样看起来，桶排是不是非常优秀？比快速排序还优秀的时间复杂度，是不是就可以无脑替换掉快排呢？

不行！

仔细观察一下，影响桶排的最重要因素，是`nums[]`的值域范围！

假设我们有一个数`21474843647`，那是不是意味着`bucket[]`的大小就要是……

是的，桶排的时间复杂度不仅仅由`nums[]`的长度决定，还由`nums[]`的值域范围决定。

然而这道题目，`nums[i]`的可能取值只有`0,1,2`，所以我们可以放心大胆地使用桶排。

```java
class Solution {
    public void sortColors(int[] nums) {
        int[] cnt = new int[3];
        for (int num : nums) cnt[num]++;
        for(int i = 0;i < cnt[0];i++)
            nums[i] = 0;
        for(int i = cnt[0];i < cnt[0] + cnt[1];i++)
            nums[i] = 1;
        for(int i = cnt[0] + cnt[1];i < cnt[0] + cnt[1] + cnt[2];i++)
            nums[i] = 2;
    }
}
```


