package leetcode.editor.cn;

// Java: 电话号码的字母组合

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class T17_LetterCombinationsOfAPhoneNumber2 {
    public static void main(String[] args) {
        Solution solution = new T17_LetterCombinationsOfAPhoneNumber2().new Solution();
        // TO TEST
        System.out.println(solution.letterCombinations("23"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {
        //还是判断digit的长度以及判断是否为空
        if (digits == null || digits.length() == 0) {
            //判断好了之后，直接new一个新的LinkedList()链表
            return new LinkedList<>();
        };
        //设置数字到字母的映射
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //创建一个StringBuilder的流式对象 可以直接使用StringBuilder直接进行拼接字符串
        LinkedList<StringBuilder> combinations = new LinkedList<>();
        combinations.add(new StringBuilder());

        for (int i = 0; i < digits.length(); i++) {
            //还是将字符串转换为数字
            int digit = Character.getNumericValue(digits.charAt(i));
            //进行while循环 然后取出peek头顶元素的对象
            while (combinations.peek().length() == i) {
                //移除peek的元素
                StringBuilder prefix = combinations.remove();
                //进行for循环，直接进行拼接
                for (char letter : mapping[digit].toCharArray()){
                    //使用StringBuilder直接进行拼接字符串
                    combinations.add(new StringBuilder(prefix).append(letter));
                }
            }
        }
        //返回所有组合的列表
        List<String> result = new ArrayList<>();
        for (StringBuilder sb : combinations) {
            result.add(sb.toString());
        }
        //返回最终的结果
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
