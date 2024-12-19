package leetcode.editor.cn;

// Java: 逆波兰表达式求值

import java.util.Stack;

public class T150_EvaluateReversePolishNotation {
    public static void main(String[] args) {
        Solution solution = new T150_EvaluateReversePolishNotation().new Solution();
        // TO TEST
        System.out.println(solution.evalRPN(new String[]{"2", "1", "+", "3", "*"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            // 检查是否是运算符
            if ("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token)) {
                int b = stack.pop(); // 栈顶是第二个操作数
                int a = stack.pop(); // 第二个栈顶是第一个操作数

                // 根据运算符计算结果
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        stack.push(a / b); // 向零截断
                        break;
                }
            } else {
                // 如果是数字，直接压入栈
                stack.push(Integer.parseInt(token));
            }
        }

        // 栈中最后剩下的就是结果
        return stack.pop();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
