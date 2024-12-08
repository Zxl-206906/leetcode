<p>给你一个字符串 <code>path</code> ，表示指向某一文件或目录的&nbsp;Unix 风格 <strong>绝对路径 </strong>（以 <code>'/'</code> 开头），请你将其转化为 <strong>更加简洁的规范路径</strong>。</p>

<p class="MachineTrans-lang-zh-CN">在 Unix 风格的文件系统中规则如下：</p>

<ul> 
 <li class="MachineTrans-lang-zh-CN">一个点&nbsp;<code>'.'</code>&nbsp;表示当前目录本身。</li> 
 <li class="MachineTrans-lang-zh-CN">此外，两个点 <code>'..'</code>&nbsp;表示将目录切换到上一级（指向父目录）。</li> 
 <li class="MachineTrans-lang-zh-CN">任意多个连续的斜杠（即，<code>'//'</code>&nbsp;或 <code>'///'</code>）都被视为单个斜杠 <code>'/'</code>。</li> 
 <li class="MachineTrans-lang-zh-CN">任何其他格式的点（例如，<code>'...'</code>&nbsp;或 <code>'....'</code>）均被视为有效的文件/目录名称。</li> 
</ul>

<p>返回的 <strong>简化路径</strong> 必须遵循下述格式：</p>

<ul> 
 <li>始终以斜杠 <code>'/'</code> 开头。</li> 
 <li>两个目录名之间必须只有一个斜杠 <code>'/'</code> 。</li> 
 <li>最后一个目录名（如果存在）<strong>不能 </strong>以 <code>'/'</code> 结尾。</li> 
 <li>此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 <code>'.'</code> 或 <code>'..'</code>）。</li> 
</ul>

<p>返回简化后得到的 <strong>规范路径</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">path = "/home/"</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>"/home"</span></p>

<p><strong>解释：</strong></p>

<p>应删除尾随斜杠。</p>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>path = "/home//foo/"</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>"/home/foo"</span></p>

<p><strong>解释：</strong></p>

<p>多个连续的斜杠被单个斜杠替换。</p>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block"> 
 <p><strong>输入：</strong><span class="example-io">path = "/home/user/Documents/../Pictures"</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>"/home/user/Pictures"</span></p>

<p><strong>解释：</strong></p>

<p>两个点&nbsp;<code>".."</code>&nbsp;表示上一级目录（父目录）。</p>

<p><strong class="example">示例 4：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>path = "/../"</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>"/"</span></p>

<p><strong>解释：</strong></p>

<p>不可能从根目录上升一级目录。</p>

<p><strong class="example">示例 5：</strong></p>

<div class="example-block"> 
 <p><span class="example-io"><b>输入：</b>path = "/.../a/../b/c/../d/./"</span></p> 
</div>

<p><span class="example-io"><b>输出：</b>"/.../b/d"</span></p>

<p><strong>解释：</strong></p>

<p><code>"..."</code>&nbsp;在这个问题中是一个合法的目录名。</p>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul> 
 <li><code>1 &lt;= path.length &lt;= 3000</code></li> 
 <li><code>path</code> 由英文字母，数字，<code>'.'</code>，<code>'/'</code> 或 <code>'_'</code> 组成。</li> 
 <li><code>path</code> 是一个有效的 Unix 风格绝对路径。</li> 
</ul>

<div><li>👍 766</li><li>👎 0</li></div>



这道题目实际上并没有考察太多 **“高大上”** 的东西，我们来认真分析分析。



首先，目录只能够按级次来访问，只能从当前级次到达它的上一层级次或者下一层级次，不能直接跨越两个或两个级次以上。



接着我们来观察 `..` ，返回上一级的这个操作，是不是跟数据结构——栈的出栈操作一样，而进入下一级次，则跟入栈操作一样了。



所以我们就可以用栈来模拟这个简化路径的过程。



接下来就是几个特殊条件的处理，首先是 `.` 可以直接忽略，如果遇到根目录 `..` `/`  的时候，就无需处理，因为根目录的上一级是不存在的。

这样子我们就可以写出这样子的代码：



```java
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
```

