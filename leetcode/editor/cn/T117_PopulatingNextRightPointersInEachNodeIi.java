package leetcode.editor.cn;

// Java: 填充每个节点的下一个右侧节点指针 II

public class T117_PopulatingNextRightPointersInEachNodeIi {
    public static void main(String[] args) {
        Solution solution = new T117_PopulatingNextRightPointersInEachNodeIi().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    ;


    class Solution {
        public Node connect(Node root) {
            if (root == null) {
                return null;
            }

            Node current = root; // 当前层的起始节点

            while (current != null) {
                Node dummy = new Node(0); // 虚拟节点，用于构建下一层链表
                Node tail = dummy; // 下一层链表的尾指针

                // 遍历当前层
                while (current != null) {
                    if (current.left != null) {
                        tail.next = current.left; // 连接左子节点
                        tail = tail.next;
                    }
                    if (current.right != null) {
                        tail.next = current.right; // 连接右子节点
                        tail = tail.next;
                    }
                    current = current.next; // 移动到当前层的下一个节点
                }

                // 移动到下一层
                current = dummy.next;
            }

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
