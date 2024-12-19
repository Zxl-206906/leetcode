<p>在一条环路上有 <code>n</code>&nbsp;个加油站，其中第 <code>i</code>&nbsp;个加油站有汽油&nbsp;<code>gas[i]</code><em>&nbsp;</em>升。</p>

<p>你有一辆油箱容量无限的的汽车，从第<em> </em><code>i</code><em> </em>个加油站开往第<em> </em><code>i+1</code><em>&nbsp;</em>个加油站需要消耗汽油&nbsp;<code>cost[i]</code><em>&nbsp;</em>升。你从其中的一个加油站出发，开始时油箱为空。</p>

<p>给定两个整数数组 <code>gas</code> 和 <code>cost</code> ，如果你可以按顺序绕环路行驶一周，则返回出发时加油站的编号，否则返回 <code>-1</code> 。如果存在解，则 <strong>保证</strong> 它是 <strong>唯一</strong> 的。</p>

<p>&nbsp;</p>

<p><strong>示例&nbsp;1:</strong></p>

<pre>
<strong>输入:</strong> gas = [1,2,3,4,5], cost = [3,4,5,1,2]
<strong>输出:</strong> 3
<strong>解释:
</strong>从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> gas = [2,3,4], cost = [3,4,3]
<strong>输出:</strong> -1
<strong>解释:
</strong>你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
因此，无论怎样，你都不可能绕环路行驶一周。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul> 
 <li><code>gas.length == n</code></li> 
 <li><code>cost.length == n</code></li> 
 <li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li> 
 <li><code>0 &lt;= gas[i], cost[i] &lt;= 10<sup>4</sup></code></li> 
</ul>

<div><li>👍 1768</li><li>👎 0</li></div>


这个问题可以通过贪心算法解决。我们遍历加油站数组，计算每个加油站的剩余汽油量，如果汽油不足以走完一圈，就尝试新的起始点。以下是 Java 的解法：

---

### **代码实现**
```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalTank = 0; // 总的油量差
        int currentTank = 0; // 当前从起点到某一站的油量差
        int startingStation = 0; // 起始加油站

        for (int i = 0; i < gas.length; i++) {
            int netGas = gas[i] - cost[i];
            totalTank += netGas;
            currentTank += netGas;

            // 如果当前油箱的油量小于 0，说明无法到达下一个加油站
            if (currentTank < 0) {
                startingStation = i + 1; // 更新起始加油站
                currentTank = 0; // 重置当前油量
            }
        }

        // 如果总油量差小于 0，说明不可能完成环路
        return totalTank >= 0 ? startingStation : -1;
    }
}
```

---

### **解法流程**

1. **初始化变量**：
    - `totalTank`：记录从头到尾所有站点油量与消耗的差值。
    - `currentTank`：模拟从起始点行驶时的油量变化。
    - `startingStation`：当前认为的起始加油站索引。

2. **遍历加油站**：
    - 计算当前站点的净油量 `netGas = gas[i] - cost[i]`。
    - 更新 `totalTank` 和 `currentTank`。
    - 如果 `currentTank < 0`，说明当前起点不行，需要重新选择起点为下一个站点。

3. **判断结果**：
    - 如果 `totalTank < 0`，表示不可能走完一圈，返回 -1。
    - 否则返回 `startingStation`。

---

### **示例执行流程**

#### 输入：
```java
gas = [1,2,3,4,5], cost = [3,4,5,1,2]
```

#### 执行过程：
| 站点索引 \(i\) | 油量 \(gas[i]\) | 耗油 \(cost[i]\) | 净油量 \(netGas\) | `currentTank` | `totalTank` | 起始站点更新 |
|:------------:|:---------------:|:----------------:|:-----------------:|:-------------:|:-----------:|:------------:|
| 0            | 1               | 3                | -2                | -2            | -2          | 1            |
| 1            | 2               | 4                | -2                | -2            | -4          | 2            |
| 2            | 3               | 5                | -2                | -2            | -6          | 3            |
| 3            | 4               | 1                | +3                | +3            | -3          | -            |
| 4            | 5               | 2                | +3                | +6            | 0           | -            |

- 总的 `totalTank = 0`，因此可以完成环路。
- 返回起始站点为 3。

#### 输出：
```
3
```

---

### **复杂度分析**

1. **时间复杂度**：O(n)
    - 只需遍历一次数组。

2. **空间复杂度**：O(1)
    - 仅使用了几个辅助变量，没有额外的空间开销。

---

### **思路总结**
- 如果总油量大于等于消耗的总油量，说明一定存在起始点可以完成环路。
- 遇到 `currentTank < 0` 时，我们知道当前起点无法满足要求，直接跳到下一个站点尝试，减少了不必要的计算。