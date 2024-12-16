package leetcode.editor.cn.learnSource.lock;

import java.util.concurrent.locks.LockSupport;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/11/21 下午6:03
 * @注释  阿里面试官：有 3 个独立的线程，一个只会输出 A，一个只会输出 B，一个只会输出 C，在三个线程启动的情况下，请用合理的方式让他们按顺序打印 ABCABC。
 */
public class ABCPrinter {
    private static Thread t1, t2, t3;

    public static void main(String[] args) {
        t1 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                //线程启动之后 会调用park将自己阻塞
                LockSupport.park();
                System.out.print("A");
                LockSupport.unpark(t2);
            }
        });

        t2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                LockSupport.park();
                System.out.print("B");
                LockSupport.unpark(t3);
            }
        });

        t3 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                LockSupport.park();
                System.out.print("C");
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();
        t3.start();

        // 主线程稍微等待一下，确保其他线程已经启动并且进入park状态。
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 主线程稍微延迟后调用 t1 的 unpark，启动整个打印流程。 这样可以保证每个线程按照预期的顺序进行工作
        LockSupport.unpark(t1);
    }
}