package leetcode.editor.cn;

// Java: 对链表进行插入排序

public class T147_InsertionSortList {
    public static void main(String[] args) {
        Solution solution = new T147_InsertionSortList().new Solution();
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
        public ListNode insertionSortList(ListNode head) {
            //创建一个哨兵节点 用于简化操作
            ListNode dummy = new ListNode(0);
            //当前需要插入的节点
            ListNode current = head;

            //当前插入的节点不为空的时候 一直循环
            while (current != null) {
                //记录当前节点的下一个节点
                ListNode next = current.next;
                //如果当前节点的值小于等于哨兵节点的值，则将当前节点插入到哨兵节点后面
                if (dummy.next == null || current.val <= dummy.next.val) {
                    current.next = dummy.next;
                    dummy.next = current;
                } else {
                    //否则，找到插入位置
                    ListNode pre = dummy;
                    while (pre.next != null && pre.next.val < current.val) {
                        pre = pre.next;
                    }
                    //将当前节点插入到插入位置的前面
                    current.next = pre.next;
                    pre.next = current;
                }
                //移动到下一个节点
                current = next;
            }
            //返回哨兵节点的下一个节点，即排序后的链表头节点
            return dummy.next;

        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
