package leetcode.editor.interview.T01_腾讯天美日常实习一面凉经;

import java.util.*;

/**
 * @version 1.0
 * @Author zxilong
 * @Date 2024/12/20 下午8:17
 * @注释
 */
public class ReverseSentence {


    public static String reverseSentence(String input) {
        
        if (input == null || input.length() == 0) {
            return input;
        }

        //按照逗号存储字符串
        String[] parts = input.split(",");
        List<String> reversedParts = new ArrayList<>();

        //遍历每个部分 反转单词顺序
        for(String part : parts){
            String[] words = part.trim().split(" ");
            Collections.reverse(Arrays.asList(words));
            reversedParts.add(String.join(" ", words));
        }

        Collections.reverse(reversedParts);
        return String.join(",", reversedParts);
    }

    
    public static void main(String[] args) {
        String input = "I am a student.";
        String output = reverseSentence(input);
        System.out.println(output);
    }
}
