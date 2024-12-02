<p>给你一个满足下述两条属性的 <code>m x n</code> 整数矩阵：</p>

<ul> 
 <li>每行中的整数从左到右按非严格递增顺序排列。</li> 
 <li>每行的第一个整数大于前一行的最后一个整数。</li> 
</ul>

<p>给你一个整数 <code>target</code> ，如果 <code>target</code> 在矩阵中，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p> 
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/05/mat.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
<strong>输出：</strong>true
</pre>

<p><strong>示例 2：</strong></p> 
<img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/11/25/mat2.jpg" style="width: 322px; height: 242px;" /> 
<pre>
<strong>输入：</strong>matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
<strong>输出：</strong>false
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>m == matrix.length</code></li> 
 <li><code>n == matrix[i].length</code></li> 
 <li><code>1 &lt;= m, n &lt;= 100</code></li> 
 <li><code>-10<sup>4</sup> &lt;= matrix[i][j], target &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 991</li><li>👎 0</li></div>



### 代码解释

这段Java代码实现了一个在二维矩阵中查找目标值的功能。具体步骤如下：

1. **初始化**：定义两个指针 `lef` 和 `rig`，分别指向矩阵的起始位置和结束位置。
2. **二分查找**：使用二分查找算法在矩阵中查找目标值。
    - 计算中间位置 `mid`。
    - 根据 `mid` 位置的值与目标值 `target` 的比较结果，调整 `lef` 或 `rig` 指针。
3. **返回结果**：如果找到目标值，返回 `true`；否则，返回 `false`。

### 控制流图

```mermaid
flowchart TD
    Start[开始] --> Init[初始化 lef 和 rig]
    Init --> Loop{lef <= rig?}
    Loop -->|Yes| Mid[计算 mid]
    Mid --> Check{matrix[mid] == target?}
    Check -->|Yes| Found[返回 true]
    Check -->|No| Compare{matrix[mid] < target?}
    Compare -->|Yes| UpdateLef[lef = mid + 1]
    Compare -->|No| UpdateRig[rig = mid - 1]
    UpdateLef --> Loop
    UpdateRig --> Loop
    Loop -->|No| NotFound[返回 false]
```


### 详细解释

1. **初始化**：
    - `lef` 初始化为 0。
    - `rig` 初始化为矩阵的总元素数减 1。

2. **二分查找**：
    - 在 `lef` 小于等于 `rig` 的情况下，进入循环。
    - 计算中间位置 `mid`。
    - 检查 `matrix[mid / matrix[0].length][mid % matrix[0].length]` 是否等于目标值 `target`。
        - 如果相等，返回 `true`。
        - 如果小于目标值，更新 `lef` 为 `mid + 1`。
        - 否则，更新 `rig` 为 `mid - 1`。

3. **返回结果**：
    - 如果循环结束时仍未找到目标值，返回 `false`。