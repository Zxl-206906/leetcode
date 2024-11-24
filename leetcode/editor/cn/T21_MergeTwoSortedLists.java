package leetcode.editor.cn;

// Java: 合并两个有序链表



public class T21_MergeTwoSortedLists {
    public static void main(String[] args) {
        Solution solution = new T21_MergeTwoSortedLists().new Solution();
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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //1.定义三个指针执行三条链
        ListNode head1 = list1;
        ListNode head2 = list2;
        //这是一个新的链表 用于拼接head1和head2的元素值
        ListNode newHead = new ListNode(-1);
        //ptr 代表的就是结果链的末尾
        ListNode ptr = newHead;

        //2.移动待合并的两条链的指针 --》两个链表只要是不为空一直循环
        while (head1 != null && head2 != null) {
            if(head1.val < head2.val) {
                ptr.next = head1;
                head1 = head1.next;
            }else {
                ptr.next = head2;
                head2 = head2.next;
            }
            ptr = ptr.next;
        }

        //3.将没有走完的链表链接到结果链的尾部
        if(head1 != null) {
            ptr.next = head1;
        }else {
            ptr.next = head2;
        }
        return newHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
