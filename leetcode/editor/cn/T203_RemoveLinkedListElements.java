package leetcode.editor.cn;

// Java: 移除链表元素

public class T203_RemoveLinkedListElements {
    public static void main(String[] args) {
        Solution solution = new T203_RemoveLinkedListElements().new Solution();
        // TO TEST

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    class Solution {
        public ListNode removeElements(ListNode head, int val) {

            // 创建虚拟头节点
            ListNode dummy = new ListNode(-1);
            dummy.next = head;

            // 当前指针
            ListNode current = dummy;

            // 遍历链表
            while (current.next != null) {
                if (current.next.val == val) {
                    // 删除当前节点
                    current.next = current.next.next;
                } else {
                    current = current.next;
                }
            }

            // 返回新的头节点
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
