package leetcode.editor.cn;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/12/3 上午10:48
 * @注释
 */
public class T11_LogMain {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (isInterested(random.nextInt(10))) {
                count++;
            }
        }
        System.out.printf("Found %d interested values%n", count);
    }

    private static boolean isInterested(int i) {
        return i % 2 == 0;
    }
}
