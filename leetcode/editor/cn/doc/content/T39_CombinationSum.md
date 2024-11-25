<p>给你一个 <strong>无重复元素</strong> 的整数数组&nbsp;<code>candidates</code> 和一个目标整数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中可以使数字和为目标数&nbsp;<code>target</code> 的 所有<em>&nbsp;</em><strong>不同组合</strong> ，并以列表形式返回。你可以按 <strong>任意顺序</strong> 返回这些组合。</p>

<p><code>candidates</code> 中的 <strong>同一个</strong> 数字可以 <strong>无限制重复被选取</strong> 。如果至少一个数字的被选数量不同，则两种组合是不同的。&nbsp;</p>

<p>对于给定的输入，保证和为&nbsp;<code>target</code> 的不同组合数少于 <code>150</code> 个。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1：</strong></p>

<pre>
<strong>输入：</strong>candidates = <span><code>[2,3,6,7]</code></span>, target = <span><code>7</code></span>
<strong>输出：</strong>[[2,2,3],[7]]
<strong>解释：</strong>
2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
7 也是一个候选， 7 = 7 。
仅有这两种组合。</pre>

<p><strong>示例&nbsp;2：</strong></p>

<pre>
<strong>输入: </strong>candidates = [2,3,5]<span><code>, </code></span>target = 8
<strong>输出: </strong>[[2,2,2,2],[2,3,3],[3,5]]</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入: </strong>candidates = <span><code>[2], </code></span>target = 1
<strong>输出: </strong>[]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= candidates.length &lt;= 30</code></li> 
 <li><code>2 &lt;= candidates[i] &lt;= 40</code></li> 
 <li><code>candidates</code> 的所有元素 <strong>互不相同</strong></li> 
 <li><code>1 &lt;= target &lt;= 40</code></li> 
</ul>

<div><li>👍 2928</li><li>👎 0</li></div>

- [ ] 待办事项1
    - [ ] 待办事项2
    - [x] 已办事项1
    - [x] 已办事项2



### 解决思路

该问题是一个经典的**回溯问题**，我们通过递归搜索所有可能的组合来解决。核心思想如下：

---

### **核心思路**
1. **回溯的定义**：
  - 使用递归构造当前组合（`currentCombination`）。
  - 每次选择一个候选数（`candidates[i]`）加入组合，并递归处理剩余的目标数 `target - candidates[i]`。
  - 如果 `target == 0`，说明找到一个有效组合，将其加入结果集。
  - 如果 `target < 0`，则结束当前路径，回溯到上一步。

2. **去重与剪枝**：
  - **去重**：候选数组中无重复元素，递归时只允许从当前或后续元素开始，避免重复组合。
  - **剪枝**：若当前数字大于目标数 `target`，直接跳过，减少不必要的计算。

3. **递归控制**：
  - 递归终止条件：`target == 0` 或遍历完所有候选数。
  - 每次递归都将剩余目标数 `target` 传递到下一层，并限制搜索范围（从当前数字起，避免重复）。

4. **初始化**：
  - 将候选数组排序（便于剪枝）。
  - 初始化结果列表，用于存储所有满足条件的组合。

---

### **代码实现**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序，便于后续剪枝
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            // 找到一个有效组合
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) break; // 剪枝
            currentCombination.add(candidates[i]); // 选择当前数字
            backtrack(candidates, target - candidates[i], i, currentCombination, result); // 递归，允许重复选择当前数字
            currentCombination.remove(currentCombination.size() - 1); // 回溯，移除最后一个数字
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(combinationSum(candidates, target));
    }
}
```

---

### **执行流程**

#### 输入：
`candidates = {2, 3, 6, 7}`, `target = 7`

---

#### 步骤 1：排序
- 对 `candidates` 排序，结果为 `{2, 3, 6, 7}`。

---

#### 步骤 2：回溯
- 初始调用：`backtrack({2, 3, 6, 7}, target=7, start=0, currentCombination=[], result=[])`

---

**第 1 层递归（从 2 开始）**：
1. 选择 `2`，目标数变为 `7 - 2 = 5`：
  - 递归调用：`backtrack({2, 3, 6, 7}, target=5, start=0, currentCombination=[2], result=[])`

---

**第 2 层递归（从 2 开始）**：
1. 再次选择 `2`，目标数变为 `5 - 2 = 3`：
  - 递归调用：`backtrack({2, 3, 6, 7}, target=3, start=0, currentCombination=[2, 2], result=[])`

2. 再次选择 `2`，目标数变为 `3 - 2 = 1`：
  - 递归调用：`backtrack({2, 3, 6, 7}, target=1, start=0, currentCombination=[2, 2, 2], result=[])`

3. 选择 `2` 超过目标，剪枝。

4. 回溯到上一层，尝试选择 `3`：
  - 目标数变为 `3 - 3 = 0`，找到一个组合 `[2, 2, 3]`。
  - 将其加入结果集：`result = [[2, 2, 3]]`。

---

**第 3 层递归（从 3 开始）**：
1. 回溯到初始层，尝试选择 `3`：
  - 目标数变为 `7 - 3 = 4`。
  - 递归调用：`backtrack({2, 3, 6, 7}, target=4, start=1, currentCombination=[3], result=[[2, 2, 3]])`

2. 再次选择 `3`，目标数变为 `4 - 3 = 1`，继续搜索。

---

**找到其他组合**：
1. 选择 `6` 超过目标，剪枝。
2. 选择 `7`，目标数变为 `0`，找到组合 `[7]`。
  - 将其加入结果集：`result = [[2, 2, 3], [7]]`。

---

### **最终结果**
`[[2, 2, 3], [7]]`

---

### **时间复杂度**
- **最坏情况**：生成所有可能的组合，每个组合最多长度为 \( \text{target}/\min(candidates) \)，总复杂度为指数级 \( O(2^n) \)。
- **剪枝优化**：通过排序和剪枝，减少不必要的递归分支。

