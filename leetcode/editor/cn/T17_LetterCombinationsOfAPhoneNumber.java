package leetcode.editor.cn;

// Java: 电话号码的字母组合

import java.util.LinkedList;
import java.util.List;

public class T17_LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        Solution solution = new T17_LetterCombinationsOfAPhoneNumber().new Solution();
        // TO TEST
        System.out.println(solution.letterCombinations("23"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> combinations = new LinkedList<>();
        //如果输入为空，直接返回空列表
        if (digits == null || digits.length() == 0) {
            return combinations;
        }
        //设置数字到字母的映射
        String[] mapping = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        //初始化添加一个空字符串到队列中
        combinations.add("");

        //遍历循环每一个数字
        for (int i = 0; i < digits.length(); i++) {
            //将当前字符转换为数字 使用Character.getNumericValue（）方法
            int digit = Character.getNumericValue(digits.charAt(i));
            //当队列中的字符串长度与当前处理的数字索引相同时，处理队列中的字符串
            while (combinations.peek().length() == i){
                //从队列中去除一个字符串
                String t = combinations.remove();
                //遍历当前数字所对应的所有字母
                for (char s : mapping[digit].toCharArray()) {
                    //将去除的字符串与当前字母向结合，然后加入到队列中
                    combinations.add(t + s);
                }
            }
        }
        //返回所有组合的列表
        return combinations;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
