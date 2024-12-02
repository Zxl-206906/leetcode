package leetcode.editor.cn;

// Java: 简化路径

import java.util.Stack;

public class T71_SimplifyPath {
    public static void main(String[] args) {
        Solution solution = new T71_SimplifyPath().new Solution();
        // TO TEST
        System.out.println(solution.simplifyPath("/home/"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String simplifyPath(String path) {
        StringBuilder res = new StringBuilder();
        String[] path_names = path.split("/");
        Stack<String> sta = new Stack<>();
        for (String str : path_names) {
            if(str.equals("..")){
                if(!sta.empty())
                    sta.pop();
            } else {
                if(!str.equals(".") && str.length() > 0)
                    sta.push(str);
            }
        }
        if(sta.empty())
            res.append("/");
        else {
            String[] ans = new String[sta.size()];
            int cnt = 0;
            while(!sta.empty()){
                ans[cnt++] = sta.pop();
            }
            for(int i = cnt - 1;i >= 0;i--){
                res.append("/").append(ans[i]);
            }
        }
        return res.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
