package leetcode.editor.cn;

// Java: 两两交换链表中的节点

public class T24_SwapNodesInPairs2 {
    public static void main(String[] args) {
        Solution solution = new T24_SwapNodesInPairs2().new Solution();
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
    public ListNode swapPairs(ListNode head) {
        //创建一个哑节点（用来简化边界处理）
        ListNode dummy = new ListNode(-1);
        //哑节点的下一个节点指向头节点
        dummy.next = head;

        ListNode prev = dummy;


        while (prev.next != null && prev.next.next != null) {
            //记录当前节点
            ListNode curr = prev.next;
            //记录当前节点的下一个节点
            ListNode next = curr.next;
            //将当前节点的下一个节点指向下一个节点的下一个节点，完成两两交换
            curr.next = next.next;
            //将下一个节点的下一个节点指向当前节点，完成两两交换
            next.next = curr;
            //将prev的下一个节点指向下一个节点，完成两两交换
            prev.next = next;
            //将prev移动到下一个节点，继续下一轮两两交换
            prev = curr;
        }
        //返回哑节点的下一个节点，即交换后的链表头节点
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
