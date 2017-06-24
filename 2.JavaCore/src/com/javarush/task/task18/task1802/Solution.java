package com.javarush.task.task18.task1802;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* 
Минимальный байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String pathToFile = consoleReader.readLine();
        consoleReader.close();

        FileInputStream file = new FileInputStream(pathToFile);
        int min = 500000000;
        int temp;
        while (file.available() > 0) {
            temp = file.read();
            if (temp < min) {
                min = temp;
            }
        }
        file.close();
        System.out.println(min);
    }
}
