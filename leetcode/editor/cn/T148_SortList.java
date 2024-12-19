package leetcode.editor.cn;

// Java: 排序链表

public class T148_SortList {
//    private static void printList(ListNode head) {
//        while (head != null) {
//            System.out.print(head.val + " ");
//            head = head.next;
//        }
//        System.out.println();
//    }

    public void main(String[] args) {
        Solution solution = new T148_SortList().new Solution();
        // TO TEST
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));

        //对链表进行排序
        ListNode sorted = solution.sortList(head);

        //打印排序后的链表
        System.out.println(sorted);
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

    /*    */

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
        public ListNode sortList(ListNode head) {
            //如果链表为空或者只有一个节点 直接返回
            if (head == null || head.next == null) {
                return head;
            }

            //使用快慢指针找到链表的重点
            ListNode slow = head;
            ListNode fast = head;
            ListNode pre = null;  //中断链表指针

            /* 初始化指针 slow和fast指针都指向链表的头结点head pre指针用于记录slow的前一个节点
            遍历链表 通过while循环 当fast指针及下一个节点不为空的时候进行循环 slow指针每次前进一步 fast指针每次前进两步
            这样当fast到达末尾时，slow指针恰好到达链表的中点
            记录中间节点 每次循环中 指针跟随slow指针移动 最终pre指向中间节点的前一个节点 用于断开连接*/
            while (fast != null && fast.next != null) {
                pre = slow;
                slow = slow.next;
                fast = fast.next.next;
            }

            //将链表分为两部分
            pre.next = null;

            //递归地对两个部分进行排序
            ListNode left = sortList(head);
            ListNode right = sortList(slow);

            //合并两个已排序的链表
            return merge(left, right);
        }

        public ListNode merge(ListNode l1, ListNode l2) {
            //定义一个哨兵节点
            ListNode dummy = new ListNode(0);
            //当前节点
            ListNode current = dummy;

            while (l1 != null && l2 != null) {
                //比较两个链表的节点值，将较小的节点链接到当前节点后面，并将指针向后移动
                if (l1.val <= l2.val) {
                    current.next = l1;
                    l1 = l1.next;
                } else {
                    current.next = l2;
                    l2 = l2.next;
                }
                current = current.next;
            }

            //将剩余的链表节点链接到当前节点后面
            if (l1 != null) {
                current.next = l1;
            }
            if (l2 != null) {
                current.next = l2;
            }
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
