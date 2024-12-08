package leetcode.editor.cn;

// Java: 删除排序链表中的重复元素 II

public class T82_RemoveDuplicatesFromSortedListIi {
    public static void main(String[] args) {
        Solution solution = new T82_RemoveDuplicatesFromSortedListIi().new Solution();
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
    public ListNode deleteDuplicates(ListNode head) {

        // 创建一个虚拟头节点，简化边界条件处理
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        dummy.next = head;

        while (p.next != null) {
            ListNode q = p.next;
            // 检查是否有重复元素
            while (q.next != null && q.next.val == q.val) {
                q = q.next;
            }

            // 如果没有重复，p 就可以向前移动
            if (q == p.next) {
                p = p.next;
            } else {
                // 有重复，跳过所有重复节点
                p.next = q.next;
            }
        }

        // 返回去除重复节点后的链表
        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
