package leetcode.editor.cn;

// Java: 环形链表

public class T141_LinkedListCycle {
    public static void main(String[] args) {
        Solution solution = new T141_LinkedListCycle().new Solution();
        // TO TEST

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */

    public class Solution {
        public boolean hasCycle(ListNode head) {

            if (head == null || head.next == null) {
                return false; // 空链表或者只有一个节点，肯定没有环
            }

            ListNode slow = head; // 慢指针
            ListNode fast = head; // 快指针

            while (fast != null && fast.next != null) {
                slow = slow.next;           // 慢指针走一步
                fast = fast.next.next;      // 快指针走两步

                if (slow == fast) {
                    return true; // 快慢指针相遇，说明有环
                }
            }

            return false; // 快指针走到空，说明没有环
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
