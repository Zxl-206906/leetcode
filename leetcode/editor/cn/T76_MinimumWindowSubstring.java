package leetcode.editor.cn;

// Java: 最小覆盖子串

import java.util.HashMap;

public class T76_MinimumWindowSubstring {
    public static void main(String[] args) {
        Solution solution = new T76_MinimumWindowSubstring().new Solution();
        // TO TEST
        
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        StringBuilder res = new StringBuilder();
        HashMap<Character,Integer> bucket1 = new HashMap<>();
        HashMap<Character,Integer> bucket2 = new HashMap<>();
        for(int i = 0;i < t.length();i++)
            bucket1.put(t.charAt(i),bucket1.getOrDefault(t.charAt(i),0) + 1);
        int lef = 0,rig = 0;
        int num = 0;
        int ansLength = s.length() + 1;
        int ansLef = 0,ansRig = 0;
        while(rig < s.length()){
            char nowPos = s.charAt(rig);
            rig++;
            if(bucket1.getOrDefault(nowPos,0) != 0){
                bucket2.put(nowPos,bucket2.getOrDefault(nowPos,0) + 1);
                if(bucket2.get(nowPos).equals(bucket1.get(nowPos)))
                    num++;
            }
            while(num == bucket1.size()){
                if(rig - lef < ansLength){
                    ansLef = lef;
                    ansRig = rig;
                    ansLength = rig - lef;
                }
                char pushPos = s.charAt(lef);
                lef++;
                if(bucket1.containsKey(pushPos)){
                    bucket2.put(pushPos,bucket2.get(pushPos) - 1);
                    if(bucket2.get(pushPos) < bucket1.get(pushPos)){
                        num--;
                    }
                }
            }
        }
        if(ansLength == s.length() + 1)
            return res.toString();
        for(int i = ansLef;i < ansRig;i++)
            res.append(s.charAt(i));
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
