package leetcode.editor.cn;

// Java: 最小栈

import java.util.Stack;

public class T155_MinStack {
    public static void main(String[] args) {
//        Solution solution = new T155_MinStack().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    //主栈
    private Stack<Integer> stack;
    //辅助栈
    private Stack<Integer> minStack;

    //初始化栈对象
    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    //将元素推入栈中
    public void push(int val) {
        stack.push(val);
        //如果辅助栈为空 或者 辅助栈栈顶元素大于等于当前元素，则将当前元素推入辅助栈
        if(minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (stack.isEmpty()){
            return;
        }
        if (stack.peek().equals(minStack.peek())){
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)

}
