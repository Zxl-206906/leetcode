package leetcode.editor.cn;

// Java: 重排链表

public class T143_ReorderList {
    public static void main(String[] args) {
        Solution solution = new T143_ReorderList().new Solution();
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
        public void reorderList(ListNode head) {
            // TODO 链表为空 或者只有一个节点 无需重新排列
            if (head == null || head.next == null) {
                return;
            }
            //1.找到链表的中间节点
            ListNode fast = head;
            ListNode slow = head;

            // 快慢指针
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            //2.拆分链表 将后半部分反转
            ListNode second = slow.next;  //后半部分的头结点
            slow.next = null; //截取前半部分
            second = reverse(second);


            // 3. 交替合并两个链表
            ListNode first = head;
            while (second != null) {
                ListNode tmp1 = first.next;
                ListNode tmp2 = second.next;

                first.next = second;  // 将第二部分节点接到第一部分
                second.next = tmp1;   // 将第一部分节点接到第二部分

                first = tmp1; // 移动指针
                second = tmp2;
            }
        }

        // 反转链表
        private ListNode reverse(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }
            return prev;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
