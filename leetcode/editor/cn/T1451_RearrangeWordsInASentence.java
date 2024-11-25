package leetcode.editor.cn;

// Java: 重新排列句子中的单词
//leetcode解题地址：https://leetcode.cn/problems/rearrange-words-in-a-sentence/solutions/2999744/1451zhong-xin-pai-lie-ju-zi-zhong-de-dan-tk0l/

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class T1451_RearrangeWordsInASentence {
    public static void main(String[] args) {
        Solution solution = new T1451_RearrangeWordsInASentence().new Solution();
        // TO TEST
        System.out.println(solution.arrangeWords("sda sd dda id"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String arrangeWords(String text) {
        //1.将句子的首字母转换为小写
        String lowercaseText = text.substring(0, 1).toLowerCase() + text.substring(1);

        //2.按照长度排序 若长度相同则保持相对顺序
        String storedText = Arrays.stream(lowercaseText.split(" "))
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.joining(" "));

        //3.将首字母重新转换为大写
        return storedText.substring(0, 1).toUpperCase() + storedText.substring(1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
