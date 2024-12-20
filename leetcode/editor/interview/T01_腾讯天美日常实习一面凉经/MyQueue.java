package leetcode.editor.interview.T01_腾讯天美日常实习一面凉经;

import java.util.Stack;

/**
 * @version 1.0
 * @Author zxilong  使用两个栈实现队列
 * @Date 2024/12/20 下午8:28
 * @注释
 */
public class MyQueue {

    // 用于存储入队的元素
    private Stack<Integer> stack1;
    // 用于辅助实现出队操作
    private Stack<Integer> stack2;

    //初始化两个栈
    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    // 入队操作
    public void push(int x) {
        // 直接将元素推入 stack1
        stack1.push(x);
    }

    // 出队操作
    public int pop() {
        // 如果 stack2 为空，则将 stack1 中的所有元素倒入 stack2
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        // 如果 stack2 仍为空，则队列为空，抛出异常（可根据需要处理）
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack2.pop(); // 从 stack2 中弹出最上面的元素
    }

    // 获取队首元素
    public int peek() {
        // 如果 stack2 为空，则将 stack1 中的所有元素倒入 stack2
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        // 如果 stack2 仍为空，则队列为空，抛出异常（可根据需要处理）
        if (stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return stack2.peek(); // 返回 stack2 顶部元素
    }

    // 判断队列是否为空
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek()); // 输出 1
        System.out.println(queue.pop());  // 输出 1
        System.out.println(queue.empty()); // 输出 false
    }
}
