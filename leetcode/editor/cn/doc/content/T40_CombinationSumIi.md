<p>给定一个候选人编号的集合&nbsp;<code>candidates</code>&nbsp;和一个目标数&nbsp;<code>target</code>&nbsp;，找出&nbsp;<code>candidates</code>&nbsp;中所有可以使数字和为&nbsp;<code>target</code>&nbsp;的组合。</p>

<p><code>candidates</code>&nbsp;中的每个数字在每个组合中只能使用&nbsp;<strong>一次</strong>&nbsp;。</p>

<p><strong>注意：</strong>解集不能包含重复的组合。&nbsp;</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> candidates =&nbsp;<span><code>[10,1,2,7,6,1,5]</code></span>, target =&nbsp;<span><code>8</code></span>,
<strong>输出:</strong>
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> candidates =&nbsp;[2,5,2,1,2], target =&nbsp;5,
<strong>输出:</strong>
[
[1,2,2],
[5]
]</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>1 &lt;=&nbsp;candidates.length &lt;= 100</code></li> 
 <li><code>1 &lt;=&nbsp;candidates[i] &lt;= 50</code></li> 
 <li><code>1 &lt;= target &lt;= 30</code></li> 
</ul>

<div><li>👍 1600</li><li>👎 0</li></div>



### 问题解析

本题是经典的**回溯算法**问题，和 `组合总和 I` 不同点在于：
1. 每个数字**只能使用一次**。
2. 解集中**不能包含重复的组合**（由于可能有重复元素，需注意去重）。

---

### **解决思路**

#### 核心逻辑
1. **排序**：
    - 对 `candidates` 排序，方便后续通过剪枝减少搜索空间。
    - 排序后，重复元素会连续出现，便于去重。

2. **回溯**：
    - 在递归中依次选择当前数字。
    - 剩余目标值 `target - candidates[i]` 传递到下一层。
    - 每个数字只能用一次，因此递归时 `start` 指针向后移动。

3. **去重策略**：
    - 同一层（同级循环）中，跳过所有相同的数字（`candidates[i] == candidates[i - 1]`）。
    - 确保每次搜索路径唯一，不重复。

---

### **代码实现**

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序，便于去重和剪枝
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if (target == 0) {
            // 找到一个有效组合，加入结果集
            result.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            // 去重：跳过同层中相同的数字
            if (i > start && candidates[i] == candidates[i - 1]) continue;

            // 剪枝：若当前数字大于目标值，直接退出循环
            if (candidates[i] > target) break;

            // 选择当前数字，递归到下一层
            currentCombination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, currentCombination, result);

            // 回溯：移除最后一个数字
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println(combinationSum2(candidates1, target1));
        
        int[] candidates2 = {2, 5, 2, 1, 2};
        int target2 = 5;
        System.out.println(combinationSum2(candidates2, target2));
    }
}
```

---

### **执行流程**

#### 示例 1：
输入：`candidates = [10, 1, 2, 7, 6, 1, 5]`, `target = 8`

1. **排序**：  
   `candidates = [1, 1, 2, 5, 6, 7, 10]`

2. **回溯递归**：
    - 初始：`start = 0, target = 8, currentCombination = []`。
    - 每次选择一个数字，递归到下一层。

---

#### 详细递归过程：
1. 第一层：选择 `1`：
    - `target = 8 - 1 = 7`，递归：
    - 第二层继续选择 `1`：
        - `target = 7 - 1 = 6`，递归：
            - 第三层选择 `6`：
                - `target = 6 - 6 = 0`，找到组合 `[1, 1, 6]`。

2. 第一层：选择 `1`，跳过第二个 `1`（去重），选择 `2`：
    - `target = 8 - 2 = 6`，递归：
    - 第二层选择 `6`：
        - `target = 6 - 6 = 0`，找到组合 `[1, 2, 5]`。

3. 第一层：选择 `7`：
    - `target = 8 - 7 = 0`，找到组合 `[1, 7]`。

4. 第一层：选择 `2`，跳过重复组合。

---

#### 示例 2：
输入：`candidates = [2, 5, 2, 1, 2]`, `target = 5`

1. **排序**：  
   `candidates = [1, 2, 2, 2, 5]`

2. **回溯递归**：
    - 第一层选择 `1`：
        - 第二层选择 `2`：
            - 第三层选择另一个 `2`，找到组合 `[1, 2, 2]`。
    - 第一层选择 `5`，找到组合 `[5]`。

---

### **输出结果**

#### 示例 1：
```java
[[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]
```

#### 示例 2：
```java
[[1, 2, 2], [5]]
```

---

### **时间复杂度**

1. **组合生成复杂度**：
    - 每个数字可用或不用，复杂度近似为 \(O(2^n)\)。

2. **剪枝优化**：
    - 排序后大幅减少递归分支，实际效率更高。

3. **总体复杂度**：  
   \(O(k \cdot 2^n)\)，其中 \(k\) 是找到的组合个数。

---

### **总结**

- **去重核心**：`if (i > start && candidates[i] == candidates[i - 1]) continue;`。
- **递归控制**：递归时传递 `start = i + 1`，避免重复使用数字。
- **剪枝优化**：排序后跳过不可行分支（`if (candidates[i] > target) break;`）。