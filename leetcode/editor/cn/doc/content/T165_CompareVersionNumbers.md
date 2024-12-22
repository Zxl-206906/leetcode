<p>给你两个 <strong>版本号字符串</strong>&nbsp;<code>version1</code> 和 <code>version2</code> ，请你比较它们。版本号由被点&nbsp;<code>'.'</code> 分开的修订号组成。<strong>修订号的值</strong> 是它 <strong>转换为整数</strong> 并忽略前导零。</p>

<p>比较版本号时，请按 <strong>从左到右的顺序</strong> 依次比较它们的修订号。如果其中一个版本字符串的修订号较少，则将缺失的修订号视为 <code>0</code>。</p>

<p>返回规则如下：</p>

<ul> 
 <li>如果&nbsp;<code><em>version1&nbsp;</em>&lt;&nbsp;<em>version2</em></code> 返回 <code>-1</code>，</li> 
 <li>如果&nbsp;<code><em>version1&nbsp;</em>&gt;&nbsp;<em>version2</em></code>&nbsp;返回&nbsp;<code>1</code>，</li> 
 <li>除此之外返回 <code>0</code>。</li> 
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">version1 = "1.2", version2 = "1.10"</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>version1 的第二个修订号为&nbsp;"2"，version2 的第二个修订号为 "10"：2 &lt; 10，所以 version1 &lt; version2。</p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">version1 = "1.01", version2 = "1.001"</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>忽略前导零，"01" 和 "001" 都代表相同的整数 "1"。</p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">version1 = "1.0", version2 = "1.0.0.0"</span></p> 
</div>

<p><strong>输出：</strong><span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>version1 有更少的修订号，每个缺失的修订号按 "0" 处理。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= version1.length, version2.length &lt;= 500</code></li> 
 <li><code>version1</code> 和 <code>version2</code> 仅包含数字和 <code>'.'</code></li> 
 <li><code>version1</code> 和 <code>version2</code> 都是 <strong>有效版本号</strong></li> 
 <li><code>version1</code> 和 <code>version2</code> 的所有修订号都可以存储在 <strong>32 位整数</strong> 中</li> 
</ul>

<div><li>👍 436</li><li>👎 0</li></div>


这个问题要求比较两个版本号的大小，版本号是由多个修订号组成的字符串，用点 `.` 分隔。每个修订号可以包含前导零，但在比较时我们要忽略这些前导零。

### 思路

1. **分割字符串**：首先，将每个版本号字符串按照 `.` 进行分割，得到多个修订号。
2. **对比修订号**：按顺序逐个比较修订号。如果一个版本的修订号比另一个大，则直接返回结果。
3. **处理不同长度的版本号**：如果两个版本号的修订号个数不一样，我们可以将缺少的修订号视为 `0` 来补齐。
4. **忽略前导零**：将修订号转化为整数后再进行比较，避免前导零的影响。

### 解决步骤

1. 分割版本号字符串，通过 `split('.')` 获取每个修订号。
2. 比较每个修订号：
    - 如果某个修订号为空或缺失，则视为 `0`。
    - 按整数进行比较。
3. 若一方版本较大或较小，立即返回结果；如果所有修订号都相同，则返回 0。

### Java 实现

```java
public class VersionComparator {
    public int compareVersion(String version1, String version2) {
        // 使用 . 分割版本号
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        
        // 计算最大长度，方便补充缺失的修订号
        int maxLength = Math.max(v1.length, v2.length);
        
        for (int i = 0; i < maxLength; i++) {
            // 如果 v1 的修订号超出范围，用 0 填充
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            // 如果 v2 的修订号超出范围，用 0 填充
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            
            // 比较两个修订号
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }
        
        // 所有修订号都相同
        return 0;
    }

    public static void main(String[] args) {
        VersionComparator solution = new VersionComparator();

        // 示例测试
        System.out.println(solution.compareVersion("1.2", "1.10")); // 输出: -1
        System.out.println(solution.compareVersion("1.01", "1.001")); // 输出: 0
        System.out.println(solution.compareVersion("1.0", "1.0.0.0")); // 输出: 0
    }
}
```

### 代码说明

1. **`split("\\.")`**：将版本号字符串按 `.` 分割成数组。例如，`"1.2.3"` 会被分割成 `["1", "2", "3"]`。
2. **处理长度不一致的版本号**：
    - 我们通过 `Math.max(v1.length, v2.length)` 获取两个版本号数组的最大长度，确保我们能够逐个比较。
    - 如果一个版本号数组比另一个短，则缺失的修订号视为 `0`。
3. **`Integer.parseInt(v1[i])`**：将字符串修订号转为整数，自动忽略前导零。
4. **逐个比较**：在循环中，我们逐个比较修订号。如果某个修订号较大或较小，就直接返回结果。
5. 如果所有修订号都相等，则返回 `0`。

### 示例分析

#### 示例 1:
输入：`version1 = "1.2"`, `version2 = "1.10"`
- 比较第一个修订号 `1` 和 `1`，相等。
- 比较第二个修订号 `2` 和 `10`，`2 < 10`，所以返回 `-1`。

#### 示例 2:
输入：`version1 = "1.01"`, `version2 = "1.001"`
- 比较第一个修订号 `1` 和 `1`，相等。
- 比较第二个修订号 `1` 和 `1`，相等。
- 所有修订号都相等，所以返回 `0`。

#### 示例 3:
输入：`version1 = "1.0"`, `version2 = "1.0.0.0"`
- 比较第一个修订号 `1` 和 `1`，相等。
- 比较第二个修订号 `0` 和 `0`，相等。
- 比较第三个修订号 `0` 和 `0`，相等。
- 比较第四个修订号 `0` 和 `0`，相等。
- 所有修订号都相等，所以返回 `0`。

### 时间复杂度

- **时间复杂度**：`O(n)`，其中 `n` 是版本号中修订号的个数。每次我们分割字符串并解析修订号，所以时间复杂度与修订号的个数线性相关。

- **空间复杂度**：`O(n)`，我们需要存储分割后的修订号数组，空间复杂度与版本号的长度成正比。

### 总结

- 本问题的关键在于正确地处理版本号的比较，并考虑到版本号数组长度不同以及忽略前导零的需求。
- 通过分割字符串并逐个比较修订号，可以确保按题意正确比较两个版本号。