package leetcode.editor.cn.learnSource.collection;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/12/15 下午8:13
 * @注释
 */
public class test01 {
    public static void main(String[] args) {
        // 初始化一个 String 类型的数组
        String[] stringArr = new String[]{"hello", "world", "!"};
        // 修改数组元素的值
        stringArr[0] = "goodbye";
        // [goodbye, world, !]
        System.out.println(Arrays.toString(stringArr));
        // 删除数组中的元素，需要手动移动后面的元素
        for (int i = 0; i < stringArr.length - 1; i++) {
            stringArr[i] = stringArr[i + 1];
        }
        stringArr[stringArr.length - 1] = null;
        // [world, !, null]
        System.out.println(Arrays.toString(stringArr));




        // 初始化一个 String 类型的 ArrayList
        ArrayList<String> stringList = new ArrayList<>(Arrays.asList("hello", "world", "!"));
        // 添加元素到 ArrayList 中
        stringList.add("goodbye");
        // [hello, world, !, goodbye]
        System.out.println(stringList);
        // 修改 ArrayList 中的元素
        stringList.set(0, "hi");
        // [hi, world, !, goodbye]
        System.out.println(stringList);
        // 删除 ArrayList 中的元素
        stringList.remove(0);
        // [world, !, goodbye]
        System.out.println(stringList);

    }
}
