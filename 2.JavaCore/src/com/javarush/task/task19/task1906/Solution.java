package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        FileReader fr = null;
        FileWriter wr = null;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String firstFileName = br.readLine();
            String secondFileName = br.readLine();
            fr = new FileReader(firstFileName);
            wr = new FileWriter(secondFileName);
            int i = 1;
            while (fr.ready()) {
                int data = fr.read();
                if (i % 2 == 0 && i !=0) {
                    System.out.println(i);
                    wr.write(data);
                }
                i++;
            }
        } catch (Exception e) {
        } finally {
            try {
                fr.close();
                wr.close();
            } catch (Exception e){}
        }
    }
}
