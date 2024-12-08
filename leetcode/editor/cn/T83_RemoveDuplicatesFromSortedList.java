package leetcode.editor.cn;

// Java: 删除排序链表中的重复元素

public class T83_RemoveDuplicatesFromSortedList {
    public static void main(String[] args) {
        Solution solution = new T83_RemoveDuplicatesFromSortedList().new Solution();
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
        //TODO p为初始化头结点
        ListNode p = head;

        //TODO 遍历列表 查到最后一个节点
        while (p != null && p.next != null) {
            //TODO 如果当前节点和下一个节点的值相同，则将当前节点的下一个节点指向当前节点的下下个节点，即跳过当前节点的下一个节点
            if (p.val == p.next.val) {
                p.next = p.next.next;
            } else {
                //TODO 如果当前节点和下一个节点的值不相同，则将当前节点向后移动一位，继续遍历下一个节点
                p = p.next;
            }
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
