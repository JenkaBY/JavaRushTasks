package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];
        int count = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            while (fis.available() > 0){
                if (Pattern.matches("[a-zA-Z]", String.valueOf((char) fis.read()))){
                    count++;
                }
            }
            System.out.println(count);
        } catch (Exception fnfe){

        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
