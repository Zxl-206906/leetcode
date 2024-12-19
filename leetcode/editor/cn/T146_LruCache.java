package leetcode.editor.cn;

// Java: LRU 缓存

import java.util.HashMap;
import java.util.Map;

public class T146_LruCache {
    public static void main(String[] args) {
//        Solution solution = new T146_LruCache().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {
        private class Node {
            int key, value;
            Node prev, next;

            Node() {
            }
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
            head.next = tail;  //主要就是构建形成一个双向的链表
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
}
