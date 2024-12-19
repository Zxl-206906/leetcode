package leetcode.editor.cn;

// Java: 克隆图

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T133_CloneGraph {
    public static void main(String[] args) {
        Solution solution = new T133_CloneGraph().new Solution();
        // TO TEST

    }

    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

    class Solution {

        private Map<Node, Node> visited = new HashMap<>();

        public Node cloneGraph(Node node) {
            if (node == null) {
                return null;
            }

            // 如果该节点已经被访问过了，则直接从映射表中返回
            if (visited.containsKey(node)) {
                return visited.get(node);
            }

            // 创建克隆节点（注意：不要拷贝邻居列表）
            Node cloneNode = new Node(node.val, new ArrayList<>());
            visited.put(node, cloneNode); // 存储原节点和克隆节点的映射关系

            // 克隆邻居
            for (Node neighbor : node.neighbors) {
                cloneNode.neighbors.add(cloneGraph(neighbor));
            }

            return cloneNode;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
