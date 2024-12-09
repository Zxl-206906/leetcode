package leetcode.editor.cn;

// Java: 分隔链表

public class T86_PartitionList {
    public static void main(String[] args) {
        Solution solution = new T86_PartitionList().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode partition(ListNode head, int x) {
        // 创建两个链表，一个存储小于 x 的节点，一个存储大于等于 x 的节点
        // 创建一个链表，存储小于 x 的节点
        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;

        // 创建一个链表，存储大于 x 的节点
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;


        while (head != null) {
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                after.next = head;
                after = after.next;
            }
            head = head.next;
        }

        // 将两个链表合并  终止大于等于 x 的链表
        after.next = null;

        // 连接两个链表
        before.next = afterHead.next;

        // 返回分割后的链表
        return beforeHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
