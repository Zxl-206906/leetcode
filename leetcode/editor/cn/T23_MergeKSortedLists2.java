package leetcode.editor.cn;

// Java: 合并 K 个升序链表

import java.util.PriorityQueue;

public class T23_MergeKSortedLists2 {
    public static void main(String[] args) {
        Solution solution = new T23_MergeKSortedLists2().new Solution();
        // TO TEST
        System.out.println(solution.mergeKLists(null));
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
        public ListNode mergeKLists(ListNode[] lists) {
            // 初始化优先级队列
            PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

            // 将 K 个链表的头节点加入到优先级队列中
            for (ListNode list : lists) {
                if (list != null) {
                    queue.add(list);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            // 从优先级队列中依次取出节点，构建新的链表
            while (!queue.isEmpty()) {
                ListNode minNode = queue.poll();
                tail.next = minNode; // 连接新节点
                tail = tail.next; // 移动尾指针
                if (minNode.next != null) {
                    queue.add(minNode.next); // 将下一个节点加入到优先级队列中
                }
            }

            tail.next = null; // 防止老的next指针影响新链表的结构
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
