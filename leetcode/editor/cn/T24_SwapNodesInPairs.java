package leetcode.editor.cn;

// Java: 两两交换链表中的节点

public class T24_SwapNodesInPairs {
    public static void main(String[] args) {
        Solution solution = new T24_SwapNodesInPairs().new Solution();
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
        // 递归终止条件：链表没有节点或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 准备交换
        ListNode firstNode = head;
        ListNode secondNode = head.next;

        // 递归处理剩下的节点
        firstNode.next = swapPairs(secondNode.next);

        // 交换
        secondNode.next = firstNode;

        // 返回交换后新的头节点
        return secondNode;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
}
