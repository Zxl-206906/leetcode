package leetcode.editor.cn;

// Java: 相交链表

public class T160_IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        Solution solution = new T160_IntersectionOfTwoLinkedLists().new Solution();
        // TO TEST

    }

    public class ListNode {
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
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) {
     * val = x;
     * next = null;
     * }
     * }
     */
    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }

            // 初始化两个指针
            ListNode pA = headA;
            ListNode pB = headB;

            // 遍历两个链表
            while (pA != pB) {
                // 如果 pA 到达末尾，切换到 headB；否则继续向下移动
                pA = (pA == null) ? headB : pA.next;

                // 如果 pB 到达末尾，切换到 headA；否则继续向下移动
                pB = (pB == null) ? headA : pB.next;
            }

            // 返回相交节点或 null
            return pA;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
