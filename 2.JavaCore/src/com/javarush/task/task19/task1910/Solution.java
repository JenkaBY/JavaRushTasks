package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String firstFileName = br.readLine();
            String secondFileName = br.readLine();

            BufferedReader readerFirstFile = new BufferedReader(new FileReader(firstFileName));
            String line;
            BufferedWriter secondFileWriter = new BufferedWriter(new FileWriter(secondFileName));
            while ((line = readerFirstFile.readLine()) != null) {
                secondFileWriter.write(line.replaceAll("\\W", ""));
            }
            readerFirstFile.close();
            secondFileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
