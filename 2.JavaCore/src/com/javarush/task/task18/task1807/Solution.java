package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String pathToFile = reader.readLine();
            FileInputStream inputStream = new FileInputStream(pathToFile);
            byte[] buffer;
            int count = 0;
            int countSym = 0;
            if (inputStream.available() > 0) {
                //читаем весь файл одним куском
                buffer = new byte[inputStream.available()];
                count = inputStream.read(buffer);
            } else buffer = new byte[0];
            for (int i = 0; i < count - 1; i++) {
                if ((buffer != null) && (buffer[i] == 44)) {
                    countSym++;
                }
            }
            System.out.println(countSym);
            reader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
