<p>给你无向&nbsp;<strong><a href="https://baike.baidu.com/item/连通图/6460995?fr=aladdin" target="_blank">连通</a>&nbsp;</strong>图中一个节点的引用，请你返回该图的&nbsp;<a href="https://baike.baidu.com/item/深拷贝/22785317?fr=aladdin" target="_blank"><strong>深拷贝</strong></a>（克隆）。</p>

<p>图中的每个节点都包含它的值 <code>val</code>（<code>int</code>） 和其邻居的列表（<code>list[Node]</code>）。</p>

<pre>
class Node {
    public int val;
    public List&lt;Node&gt; neighbors;
}</pre>

<p>&nbsp;</p>

<p><strong>测试用例格式：</strong></p>

<p>简单起见，每个节点的值都和它的索引相同。例如，第一个节点值为 1（<code>val = 1</code>），第二个节点值为 2（<code>val = 2</code>），以此类推。该图在测试用例中使用邻接列表表示。</p>

<p><strong>邻接列表</strong> 是用于表示有限图的无序列表的集合。每个列表都描述了图中节点的邻居集。</p>

<p>给定节点将始终是图中的第一个节点（值为 1）。你必须将&nbsp;<strong>给定节点的拷贝&nbsp;</strong>作为对克隆图的引用返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/01/133_clone_graph_question.png" style="height: 500px; width: 500px;" /></p>

<pre>
<strong>输入：</strong>adjList = [[2,4],[1,3],[2,4],[1,3]]
<strong>输出：</strong>[[2,4],[1,3],[2,4],[1,3]]
<strong>解释：
</strong>图中有 4 个节点。
节点 1 的值是 1，它有两个邻居：节点 2 和 4 。
节点 2 的值是 2，它有两个邻居：节点 1 和 3 。
节点 3 的值是 3，它有两个邻居：节点 2 和 4 。
节点 4 的值是 4，它有两个邻居：节点 1 和 3 。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/02/01/graph.png" style="height: 148px; width: 163px;" /></p>

<pre>
<strong>输入：</strong>adjList = [[]]
<strong>输出：</strong>[[]]
<strong>解释：</strong>输入包含一个空列表。该图仅仅只有一个值为 1 的节点，它没有任何邻居。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>adjList = []
<strong>输出：</strong>[]
<strong>解释：</strong>这个图是空的，它不含任何节点。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li>这张图中的节点数在 <code>[0, 100]</code>&nbsp;之间。</li> 
 <li><code>1 &lt;= Node.val &lt;= 100</code></li> 
 <li>每个节点值&nbsp;<code>Node.val</code> 都是唯一的，</li> 
 <li>图中没有重复的边，也没有自环。</li> 
 <li>图是连通图，你可以从给定节点访问到所有节点。</li> 
</ul>

<div><li>👍 752</li><li>👎 0</li></div>


要解决克隆无向图的问题，深拷贝图的核心在于使用 **DFS** 或 **BFS** 遍历原图，同时维护一个映射表来记录原节点和克隆节点之间的映射关系，避免重复克隆和死循环。

### **解法：深拷贝图（DFS + 哈希表）**

---

### **代码实现**
```java
import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

class Solution {
    private Map<Node, Node> visited = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        // 如果该节点已经被访问过了，则直接从映射表中返回
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        // 创建克隆节点（注意：不要拷贝邻居列表）
        Node cloneNode = new Node(node.val, new ArrayList<>());
        visited.put(node, cloneNode); // 存储原节点和克隆节点的映射关系

        // 克隆邻居
        for (Node neighbor : node.neighbors) {
            cloneNode.neighbors.add(cloneGraph(neighbor));
        }

        return cloneNode;
    }
}
```

---

### **示例运行流程**

#### 输入：
```
adjList = [[2,4],[1,3],[2,4],[1,3]]
```

#### 图结构：
- 节点 1 的邻居是 [2, 4]
- 节点 2 的邻居是 [1, 3]
- 节点 3 的邻居是 [2, 4]
- 节点 4 的邻居是 [1, 3]

---

#### **步骤 1：克隆节点 1**
1. 克隆 `Node(1)` 并将其加入映射表：
   ```
   visited = {Node(1): CloneNode(1)}
   ```

2. 递归克隆邻居节点 2 和 4。

---

#### **步骤 2：克隆节点 2**
1. 克隆 `Node(2)` 并加入映射表：
   ```
   visited = {Node(1): CloneNode(1), Node(2): CloneNode(2)}
   ```

2. 递归克隆节点 2 的邻居节点 1 和 3。
    - 克隆 `Node(1)`：发现已存在于映射表中，直接返回。
    - 克隆 `Node(3)`。

---

#### **步骤 3：克隆节点 3**
1. 克隆 `Node(3)` 并加入映射表：
   ```
   visited = {Node(1): CloneNode(1), Node(2): CloneNode(2), Node(3): CloneNode(3)}
   ```

2. 递归克隆节点 3 的邻居节点 2 和 4。
    - 克隆 `Node(2)`：发现已存在于映射表中，直接返回。
    - 克隆 `Node(4)`。

---

#### **步骤 4：克隆节点 4**
1. 克隆 `Node(4)` 并加入映射表：
   ```
   visited = {Node(1): CloneNode(1), Node(2): CloneNode(2), Node(3): CloneNode(3), Node(4): CloneNode(4)}
   ```

2. 递归克隆节点 4 的邻居节点 1 和 3：
    - 两者都已存在于映射表中，直接返回。

---

#### 最终输出：
克隆图的邻接表为：
```
[[2,4],[1,3],[2,4],[1,3]]
```

---

### **复杂度分析**
1. **时间复杂度：O(N + E)**
    - 遍历每个节点和每条边（邻接表）。
    - N 是节点数，E 是边数。

2. **空间复杂度：O(N)**
    - 需要额外的哈希表存储已访问的节点。

---

### **扩展：BFS 实现**
如果需要使用 **BFS** 实现，可以使用队列来辅助完成图的克隆：

```java
public Node cloneGraph(Node node) {
    if (node == null) {
        return null;
    }

    Map<Node, Node> visited = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();

    // 克隆起始节点
    Node cloneNode = new Node(node.val, new ArrayList<>());
    visited.put(node, cloneNode);
    queue.add(node);

    while (!queue.isEmpty()) {
        Node curr = queue.poll();

        for (Node neighbor : curr.neighbors) {
            if (!visited.containsKey(neighbor)) {
                // 克隆邻居节点
                visited.put(neighbor, new Node(neighbor.val, new ArrayList<>()));
                queue.add(neighbor);
            }
            // 将克隆的邻居加入当前克隆节点的邻居列表中
            visited.get(curr).neighbors.add(visited.get(neighbor));
        }
    }

    return cloneNode;
}
```