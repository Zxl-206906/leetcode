package leetcode.editor.cn;

// Java: 有效的括号

import java.util.Stack;

public class T20_ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new T20_ValidParentheses().new Solution();
        // TO TEST
        System.out.println(solution.isValid("()"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        //创建一个栈来存放括号
        Stack<Character> stack = new Stack<>();

        //遍历字符串中的每个字符
        for (char c : s.toCharArray()) {
            //如果是开括号，压入栈中
            if(c == '(' || c == '{' || c == '['){
                stack.push(c);
            }else{
                //如果是闭括号 检查栈是否为空 以及栈顶元素是否与之匹配
                if(stack.isEmpty()){
                    return false; // 栈为空，说明没有对应的开括号
                }

                //检查栈顶元素
                char top = stack.pop();
                if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')) {
                    return false; // 栈顶元素与当前闭括号不匹配
                }
            }
        }
        // 如果栈为空，说明所有括号都正确匹配
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
