<div class="title__3Vvk">
 请你设计并实现一个满足&nbsp; 
 <a href="https://baike.baidu.com/item/LRU" target="_blank">LRU (最近最少使用) 缓存</a> 约束的数据结构。
</div>

<div class="title__3Vvk">
 实现 
 <code>LRUCache</code> 类：
</div>

<div class="original__bRMd"> 
 <div> 
  <ul> 
   <li><code>LRUCache(int capacity)</code> 以 <strong>正整数</strong> 作为容量&nbsp;<code>capacity</code> 初始化 LRU 缓存</li> 
   <li><code>int get(int key)</code> 如果关键字 <code>key</code> 存在于缓存中，则返回关键字的值，否则返回 <code>-1</code> 。</li> 
   <li><code>void put(int key, int value)</code>&nbsp;如果关键字&nbsp;<code>key</code> 已经存在，则变更其数据值&nbsp;<code>value</code> ；如果不存在，则向缓存中插入该组&nbsp;<code>key-value</code> 。如果插入操作导致关键字数量超过&nbsp;<code>capacity</code> ，则应该 <strong>逐出</strong> 最久未使用的关键字。</li> 
  </ul> 
 </div>
</div>

<p>函数 <code>get</code> 和 <code>put</code> 必须以 <code>O(1)</code> 的平均时间复杂度运行。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入</strong>
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
<strong>输出</strong>
[null, null, null, 1, null, -1, null, -1, 3, 4]

<strong>解释</strong>
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= capacity &lt;= 3000</code></li> 
 <li><code>0 &lt;= key &lt;= 10000</code></li> 
 <li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li> 
 <li>最多调用 <code>2 * 10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code></li> 
</ul>

<div><li>👍 3334</li><li>👎 0</li></div>


实现一个 **LRUCache** 可以通过使用双向链表和哈希表结合的方式来完成。这种方式可以保证 `put` 和 `get` 操作的时间复杂度为 **O(1)**。

以下是完整的代码实现：

---

### **Java代码实现**
```java
import java.util.HashMap;
import java.util.Map;

class LRUCache {
    private class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> cache; // 哈希表用于快速查找
    private final Node head, tail; // 双向链表的哨兵节点

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(-1, -1); // 虚拟头节点
        this.tail = new Node(-1, -1); // 虚拟尾节点
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        Node node = cache.get(key);
        moveToHead(node); // 如果访问了某节点，则将其移到链表头部
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // 如果 key 存在，则更新值并移动到链表头部
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // 如果 key 不存在，则插入新节点
            if (cache.size() >= capacity) {
                removeLRU(); // 删除最久未使用的节点
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode); // 将新节点添加到链表头部
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void removeLRU() {
        Node lruNode = tail.prev;
        removeNode(lruNode);
        cache.remove(lruNode.key);
    }
}
```

---

### **执行示例**

```java
public class Main {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);

        lRUCache.put(1, 1); // 缓存为 {1=1}
        lRUCache.put(2, 2); // 缓存为 {1=1, 2=2}
        System.out.println(lRUCache.get(1)); // 返回 1
        lRUCache.put(3, 3); // 缓存为 {3=3, 1=1}, 2 被淘汰
        System.out.println(lRUCache.get(2)); // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 缓存为 {4=4, 3=3}, 1 被淘汰
        System.out.println(lRUCache.get(1)); // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3)); // 返回 3
        System.out.println(lRUCache.get(4)); // 返回 4
    }
}
```

---

### **设计思路**

1. **核心思想**：
    - 使用 **哈希表 (HashMap)** 保存每个键值对，保证 `O(1)` 的查找时间。
    - 使用 **双向链表** 维护访问的顺序，以便快速定位和更新最久未使用的节点。

2. **双向链表的使用**：
    - 每次访问或更新节点时，将其移到链表头部（表示最近使用）。
    - 当缓存容量超出限制时，删除链表尾部的节点（表示最久未使用）。

3. **哨兵节点**：
    - 为了方便操作链表（如插入或删除节点），使用虚拟的头节点 `head` 和尾节点 `tail`。

4. **主要方法**：
    - `get(key)`：从哈希表中查找节点，将其移动到链表头部。
    - `put(key, value)`：
        - 如果 `key` 已存在，更新值并移动到链表头部。
        - 如果 `key` 不存在，插入新节点并检查是否需要删除最久未使用的节点。

---

### **时间复杂度**
- **`get` 和 `put` 操作**：均为 **O(1)**。
    - 哈希表的查找时间复杂度为 O(1)。
    - 双向链表的插入和删除操作时间复杂度也为 O(1)。

### **空间复杂度**
- 哈希表和双向链表的空间复杂度为 **O(capacity)**，其中 `capacity` 为缓存的最大容量。

---

### **运行示例输出**
```text
1
-1
-1
3
4
```

---

通过这种设计，`LRUCache` 可以高效地处理最近最少使用的缓存问题，并在性能上满足要求。