package leetcode.editor.cn;

// Java: 颜色分类

public class T75_SortColors {
    public static void main(String[] args) {
        Solution solution = new T75_SortColors().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void sortColors(int[] nums) {
        int[] cnt = new int[3];
        for (int num : nums) cnt[num]++;
        for(int i = 0;i < cnt[0];i++)
            nums[i] = 0;
        for(int i = cnt[0];i < cnt[0] + cnt[1];i++)
            nums[i] = 1;
        for(int i = cnt[0] + cnt[1];i < cnt[0] + cnt[1] + cnt[2];i++)
            nums[i] = 2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
