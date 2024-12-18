package leetcode.editor.cn;

// Java: 环形链表 II

public class T142_LinkedListCycleIi {
    public static void main(String[] args) {
        Solution solution = new T142_LinkedListCycleIi().new Solution();
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
        public ListNode detectCycle(ListNode head) {

            //链表为空或者只有一个节点
            if (head == null || head.next == null) {
                return null;
            }

            //分别定义快慢指针
            ListNode slow = head;
            ListNode fast = head;

            //1.快慢指针相遇哦按段链表是否有环
            while (fast != null && fast.next != null) {
                //慢指针走一步
                slow = slow.next;
                //快指针走两步
                fast = fast.next.next;
                //快慢指针相遇，说明有环
                if (slow == fast) {
                    // 2. 如果有环，找到环的入口节点
                    ListNode entry = head;
                    while (entry != slow) {
                        //entry从头开始走
                        entry = entry.next;
                        //slow从相遇点开始走
                        slow = slow.next;
                    }
                    return entry;
                }
            }
            //如果没有环 则返回null
            return null;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
