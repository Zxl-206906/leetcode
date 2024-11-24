package leetcode.editor.cn;

// Java: 串联所有单词的子串

import com.google.common.collect.Collections2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T30_SubstringWithConcatenationOfAllWords {
    public static void main(String[] args) {
        Solution solution = new T30_SubstringWithConcatenationOfAllWords().new Solution();
        // TO TEST
        System.out.println(solution.findSubstring("barfoothefoobarman", new String[]{"foo","bar"}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        //使用 Collections2 的 `Collections2.permutations` 方法来生成所有 words 的排列。借助 API 可以帮我们减轻排列组合的工作量，当然了，也可以直接使用回溯算法来完成
        List<Integer> result = new ArrayList<>();
        //生成所有words的排列
        List<String> permutations = generatePermutations(words);
        //检查所有排列是否为s的子串
        for (String permutation : permutations) {
            int index = s.indexOf(permutation);
            while (index != -1) {
                // 如果找到，添加到结果列表中
                if (!result.contains(index)) {
                    result.add(index);
                }
                // 继续查找下一个匹配的子串
                index = s.indexOf(permutation, index + 1);
            }
        }
        return result;
    }


    private List<String> generatePermutations(String[] words) {
        List<String> permutations = new ArrayList<>();

        // 把数组转成集合
        List<String> list = new ArrayList<>();
        Collections.addAll(list, words);

        // 生成所有排列
        for (List<String> permutation : Collections2.permutations(list)) {
            StringBuilder sb = new StringBuilder();
            for (String word : permutation) {
                sb.append(word);
            }
            permutations.add(sb.toString());
        }

        return permutations;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
