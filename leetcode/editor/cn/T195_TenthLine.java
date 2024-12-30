package leetcode.editor.cn;

// Java: 第十行

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class T195_TenthLine {
    public static void main(String[] args) {
        //Solution solution = new T195_TenthLine().new Solution();
        // TO TEST
        String fileName = "file.txt";
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineConunt = 0;
            while ((line = br.readLine()) != null) {
                lineConunt++;
                if (lineConunt == 10) {
                    System.out.println(line);
                    break;
                }
            }
            System.out.println("File has less than 10 lines.");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //There is no code of Java type for this problem
}
