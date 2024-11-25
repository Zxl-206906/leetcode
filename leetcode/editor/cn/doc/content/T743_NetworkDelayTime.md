<p>有 <code>n</code> 个网络节点，标记为&nbsp;<code>1</code>&nbsp;到 <code>n</code>。</p>

<p>给你一个列表&nbsp;<code>times</code>，表示信号经过 <strong>有向</strong> 边的传递时间。&nbsp;<code>times[i] = (u<sub>i</sub>, v<sub>i</sub>, w<sub>i</sub>)</code>，其中&nbsp;<code>u<sub>i</sub></code>&nbsp;是源节点，<code>v<sub>i</sub></code>&nbsp;是目标节点， <code>w<sub>i</sub></code>&nbsp;是一个信号从源节点传递到目标节点的时间。</p>

<p>现在，从某个节点&nbsp;<code>K</code>&nbsp;发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回&nbsp;<code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2019/05/23/931_example_1.png" style="height: 220px; width: 200px;" /></p>

<pre>
<strong>输入：</strong>times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 1
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>times = [[1,2,1]], n = 2, k = 2
<strong>输出：</strong>-1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= k &lt;= n &lt;= 100</code></li> 
 <li><code>1 &lt;= times.length &lt;= 6000</code></li> 
 <li><code>times[i].length == 3</code></li> 
 <li><code>1 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n</code></li> 
 <li><code>u<sub>i</sub> != v<sub>i</sub></code></li> 
 <li><code>0 &lt;= w<sub>i</sub> &lt;= 100</code></li> 
 <li>所有 <code>(u<sub>i</sub>, v<sub>i</sub>)</code> 对都 <strong>互不相同</strong>（即，不含重复边）</li> 
</ul>

<div><li>👍 789</li><li>👎 0</li></div>

### 问题本质
题目是关于图的最短路径问题：
- 给定一个有向图，每条边有一个权重，求从某个起点 \(k\) 到所有节点的最短路径。
- 如果所有节点都能到达，返回最长的路径；否则返回 -1。

这是一个典型的 **单源最短路径问题**，可以用 **Dijkstra 算法** 来高效求解。

---

### Step 1: 构建图的邻接表
我们用邻接表来存储图的信息，邻接表是一种压缩存储形式。

**为什么用邻接表？**
- 相较于邻接矩阵，邻接表只存储实际存在的边，因此在稀疏图中更节省空间。

**构建方式：**
1. 遍历 `times` 数组（每条边的信息）。
2. 将每条边的源节点 \(u_i\) 和目标节点 \(v_i\) 及权重 \(w_i\) 存入一个 `Map<Integer, List<int[]>>` 中。  
   这里，`int[]` 是一个长度为 2 的数组，表示目标节点和权重。

**示例：**
```text
times = [[2, 1, 1], [2, 3, 1], [3, 4, 1]];
```
对应的邻接表为：
```text
{
  2: [[1, 1], [3, 1]],
  3: [[4, 1]]
}
```

---

### Step 2: 初始化数据结构
为了实现 **Dijkstra 算法**，我们需要以下数据结构：

1. **优先队列（最小堆）**：
    - 存储节点的「当前路径长度」和「节点编号」，按路径长度升序排序。
    - 使用 Java 的 `PriorityQueue` 实现，构造时通过 `Comparator` 指定排序规则。

2. **距离数组 `dist[]`**：
    - 存储从起点 \(k\) 到每个节点的最短路径，初始时设置为正无穷大 `Integer.MAX_VALUE`。
    - 起点 \(k\) 的距离设为 0。

3. **起点入堆**：
    - 起点 \(k\) 的路径长度为 0，将其加入堆。

---

### Step 3: Dijkstra 算法核心逻辑
这是算法的主要部分，利用优先队列实现：

1. **取出当前堆中路径最短的节点**：
    - 从堆中弹出「路径长度最小」的节点 `current`。
    - 如果当前路径长度已经大于已记录的最短路径，跳过（优化）。

2. **更新邻居节点的最短路径**：
    - 遍历当前节点的所有邻居节点。
    - 如果从当前节点到邻居的路径比之前记录的更短，则更新 `dist[]` 数组，并将新的路径加入堆。

3. **重复以上步骤**：
    - 直到堆为空（所有可达节点都已访问）。