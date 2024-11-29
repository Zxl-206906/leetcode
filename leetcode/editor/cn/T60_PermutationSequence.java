package leetcode.editor.cn;

// Java: 排列序列

import java.util.ArrayList;
import java.util.List;

public class T60_PermutationSequence {
    public static void main(String[] args) {
        Solution solution = new T60_PermutationSequence().new Solution();
        // TO TEST
        System.out.println(solution.getPermutation(3, 3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String getPermutation(int n, int k) {
        // Step 1: Prepare a list of numbers to pick from
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        // Step 2: Calculate the factorial of n
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact *= i;
        }

        // Step 3: Adjust k to be zero-indexed
        k--;

        StringBuilder result = new StringBuilder();

        // Step 4: Generate the permutation
        for (int i = n; i >= 1; i--) {
            // Find the index of the current number
            int index = k / fact;
            result.append(nums.get(index));  // Add the number at the index to the result
            nums.remove(index);  // Remove the number from the list

            // Update k and fact for the next iteration
            k %= fact;
            if (i > 1) {
                fact /= (i - 1);
            }
        }

        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
