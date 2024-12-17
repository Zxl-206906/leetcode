package leetcode.editor.cn;

// Java: 有序链表转换二叉搜索树

public class T109_ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
        Solution solution = new T109_ConvertSortedListToBinarySearchTree().new Solution();
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


        //计算链表的长度
        ListNode head;

        public TreeNode sortedListToBST(ListNode head) {
            this.head = head;
            int size = getSize(head);
            return sortedListToBSTHelper(0, size - 1);
        }

        //获取链表长度
        private int getSize(ListNode head) {
            int size = 0;
            while (head != null) {
                size++;
                head = head.next;
            }
            return size;
        }

        //递归函数 构建平衡二叉搜索树
        private TreeNode sortedListToBSTHelper(int left, int right) {
            //递归边界条件
            if (left > right) {
                return null;
            }

            //递归构建左子树
            int mid = (left + right) / 2;
            TreeNode leftChild = sortedListToBSTHelper(left, mid - 1);

            //当前节点就是树的根节点
            TreeNode root = new TreeNode(head.val);

            root.left = leftChild;

            //移动链表指针到下一个节点
            head = head.next;

            //递归构建右子树
            root.right = sortedListToBSTHelper(mid + 1, right);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
