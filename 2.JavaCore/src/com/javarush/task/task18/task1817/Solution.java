package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        String fileName = args[0];
        int whiteSpaces = 0;
        int allSymbols = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(fileName);
            while (fis.available() > 0){
                if (String.valueOf((char) fis.read()).compareTo(" ") == 0){
                    whiteSpaces++;
                    allSymbols++;
                } else allSymbols ++;
            }
            float result = (float) whiteSpaces/allSymbols*100;
            String str = String.format("%.2f", result);
            System.out.println(str);
        } catch (Exception fnfe){
            fnfe.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
