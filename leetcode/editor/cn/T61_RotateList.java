package leetcode.editor.cn;

// Java: 旋转链表

public class T61_RotateList {
    public static void main(String[] args) {
        Solution solution = new T61_RotateList().new Solution();
        // TO TEST
        System.out.println();
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
    public ListNode rotateRight(ListNode head, int k) {
        // 边界条件
        if(k == 0 || head == null || head.next == null) {
            return head;
        }
        // 获取链表的长度
        int sizeOfList = 1;
        // 创建一个虚拟头节点，简化边界条件处理
        ListNode tail = head;
        // 使用while循环遍历链表，同时记录链表的长度
        while(tail.next != null){
            tail = tail.next;//获取整个链表最末端的节点
            sizeOfList++;//计算链表长度
        }
        // 断开链表
        int breakPos = sizeOfList - k % sizeOfList;
        // 重新连接链表
        if(breakPos == sizeOfList)
            return head;

        tail.next = head;//将链表变为循环链表

        while(breakPos-- > 0){
            tail = tail.next;//移动到应该断开的位置
        }

        ListNode newHead = tail.next;
        tail.next = null;//断开
        return newHead;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
