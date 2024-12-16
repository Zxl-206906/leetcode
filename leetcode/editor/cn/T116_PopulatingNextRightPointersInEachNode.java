package leetcode.editor.cn;

// Java: 填充每个节点的下一个右侧节点指针

public class T116_PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Solution solution = new T116_PopulatingNextRightPointersInEachNode().new Solution();
        // TO TEST

    }

    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
    //本地需要打开这个，不然idea报错，但是上传leetcode时，需要注释掉不然报两个类错误，无法进行提交解答
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    class Solution {
        public Node connect(Node root) {

            //边界条件
            if (root == null) {
                return null;
            }


            // 如果左子节点存在 也就是说左子树不为空
            if (root.left != null) {
                root.left.next = root.right;
                // 如果右侧存在下一个节点，连接右子节点和其下一个节点的左子节点
                if (root.next != null) {
                    root.right.next = root.next.left;
                }
            }

            // 递归处理左子树和右子树
            connect(root.left);
            connect(root.right);

            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
