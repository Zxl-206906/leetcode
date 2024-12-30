<p>给定一个文件&nbsp;<code>file.txt</code>，转置它的内容。</p>

<p>你可以假设每行列数相同，并且每个字段由&nbsp;<code>' '</code> 分隔。</p>

<p>&nbsp;</p>

<p><strong>示例：</strong></p>

<p>假设&nbsp;<code>file.txt</code>&nbsp;文件内容如下：</p>

<pre>
name age
alice 21
ryan 30
</pre>

<p>应当输出：</p>

<pre>
name alice ryan
age 21 30
</pre>

<div><li>👍 78</li><li>👎 0</li></div>

# Read from the file file.txt and print its transposed content to stdout.
```shell
awk '
{
    for (i = 1; i <= NF; i++) {
        if (NR == 1) { # 第一行
            transposed[i] = $i 
        } else {
            transposed[i] = transposed[i] " " $i
        }
    }
}
END {
    for (i = 1; i <= length(transposed); i++) {
        print transposed[i]
    }
}' file.txt

```
如果是第一行（NR == 1），初始化每列的内容 transposed[i] = $i
如果是后续行，将内容追加到对应列的字符串中：transposed[i] = transposed[i] " " $i
下半部：打印转换后的每一列的内容
