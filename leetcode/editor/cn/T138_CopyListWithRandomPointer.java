package leetcode.editor.cn;

// Java: 随机链表的复制

public class T138_CopyListWithRandomPointer {
    public static void main(String[] args) {
        Solution solution = new T138_CopyListWithRandomPointer().new Solution();
        // TO TEST

    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

    class Solution {
        public Node copyRandomList(Node head) {
            if (head == null) {
                return null;
            }

            // 1. 插入新节点
            Node current = head;
            while (current != null) {
                Node newNode = new Node(current.val);
                newNode.next = current.next;
                current.next = newNode;
                current = newNode.next; // 跳到原链表的下一个节点
            }

            // 2. 设置新节点的 random 指针
            current = head;
            while (current != null) {
                if (current.random != null) {
                    current.next.random = current.random.next;
                }
                current = current.next.next; // 跳到原链表的下一个节点
            }

            // 3. 拆分链表
            Node newHead = head.next;
            Node oldNode = head;
            Node newNode = head.next;
            while (oldNode != null) {
                oldNode.next = oldNode.next.next; // 恢复原链表
                if (newNode.next != null) {
                    newNode.next = newNode.next.next; // 更新新链表
                }
                oldNode = oldNode.next;
                newNode = newNode.next;
            }

            return newHead;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
