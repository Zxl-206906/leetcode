package leetcode.editor.cn.lock;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/11/25 下午4:38
 * @注释
 */
public class RejectedExecutionHandlerTest {
    public static void main(String[] args) {
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        };
    }
}
