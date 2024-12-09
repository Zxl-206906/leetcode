package leetcode.editor.cn;

// Java: 合并两个有序数组

public class T88_MergeSortedArray {
    public static void main(String[] args) {
        Solution solution = new T88_MergeSortedArray().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // pos 指向 nums1 的最后一个位置
        int pos = m + n - 1;
        // 将 m 和 n 减一，让它们直接作为 nums1 和 nums2 的索引
        m--;
        n--;

        // 循环条件是 m 或 n 任一个还没遍历完
        while (m >= 0 || n >= 0) {
            // 如果 nums1 已经遍历完，只需将 nums2 的元素复制到 nums1
            if (m == -1) {
                nums1[pos--] = nums2[n--];
            } else {
                // 如果 nums2 已经遍历完，则 nums1 已在正确位置，只需移动 m
                if (n == -1) {
                    nums1[pos--] = nums1[m--];
                } else {
                    // 比较 nums1 和 nums2 当前元素，将较大的元素放到 nums1 的 pos 位置
                    if (nums1[m] >= nums2[n]) {
                        nums1[pos--] = nums1[m--];
                    } else {
                        nums1[pos--] = nums2[n--];
                    }
                }
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
