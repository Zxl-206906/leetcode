package leetcode.editor.cn;

// Java: 合并 K 个升序链表

import java.util.PriorityQueue;

public class T23_MergeKSortedLists {
    public static void main(String[] args) {
        Solution solution = new T23_MergeKSortedLists().new Solution();
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
        public ListNode mergeKLists(ListNode[] lists) {
            // 初始化优先级队列
            PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> n1.val - n2.val);

            // 将所有链表的所有节点都添加到优先级队列中
            for (ListNode list : lists) {
                while (list != null) {
                    queue.add(list);
                    list = list.next;
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;

            // 从优先级队列中依次取出节点，构建新的链表
            while (!queue.isEmpty()) {
                tail.next = queue.poll(); // 连接新节点
                tail = tail.next; // 移动尾指针
            }

            tail.next = null; // 防止老的next指针影响新链表的结构
            return dummy.next;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
