package leetcode.editor.cn;

// Java: 有序链表转换二叉搜索树

public class T109_ConvertSortedListToBinarySearchTree2 {
    public static void main(String[] args) {
        Solution solution = new T109_ConvertSortedListToBinarySearchTree2().new Solution();
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
    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
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

    class Solution {

        public TreeNode sortedListToBST(ListNode head) {

            //终止条件
            if (head == null) {
                return null;
            }

            //如果只有一个节点的时候应该怎么处理
            if (head.next == null) {
                return new TreeNode(head.val);
            }

            //定义两个缓慢指针  定义这个pre指针就是为了找到中点然后递归的时候进行与中间节点的断开操作
            ListNode slow = head, fast = head, pre = null;

            //循环找到链表的中点
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                pre = slow;
                slow = slow.next;
            }
            //与中间节点进行断开
            pre.next = null;


            TreeNode root = new TreeNode(slow.val);
            TreeNode left = sortedListToBST(head);
            TreeNode right = sortedListToBST(slow.next);

            slow.next = null;

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
