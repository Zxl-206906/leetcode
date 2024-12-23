package leetcode.editor.cn;

// Java: 第二高的薪水

import java.util.*;

public class T176_SecondHighestSalary {
    public static void main(String[] args) {
        Solution solution = new T176_SecondHighestSalary().new Solution();

        // 测试用例
        int[][] scores1 = {{1, 100}, {2, 200}, {3, 300}};
        System.out.println(solution.secondHighestSalary(scores1)); // 200

        int[][] scores2 = {{1, 100}};
        System.out.println(solution.secondHighestSalary(scores2)); // null
    }

    class Solution {
        public Integer secondHighestSalary(int[][] scores) {
            // 使用一个集合来存储所有不同的薪水值
            Set<Integer> salarySet = new HashSet<>();

            // 遍历所有的薪水并加入集合
            for (int[] score : scores) {
                salarySet.add(score[1]);
            }

            // 如果集合的大小小于 2，说明没有第二高薪水
            if (salarySet.size() < 2) {
                return null;
            }

            // 将集合转换为列表并按降序排序
            List<Integer> sortedSalaries = new ArrayList<>(salarySet);
            Collections.sort(sortedSalaries, Collections.reverseOrder());

            // 返回第二高薪水
            return sortedSalaries.get(1);
        }
    }
}