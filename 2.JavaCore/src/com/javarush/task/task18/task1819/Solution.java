package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String firstPath = "";
        String secondPath = "";
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            firstPath = consoleReader.readLine();
            secondPath = consoleReader.readLine();
        } catch(Exception e){
        }

        FileOutputStream fos1 = null;
        FileInputStream fis1 = null;
        FileInputStream fis2 = null;
        try {
            ArrayList<Integer> buffer1 = new ArrayList<>();
            fis1 = new FileInputStream(firstPath);

            while (fis1.available() > 0){
                buffer1.add(fis1.read());
            }
            fis1.close();

            fis2 = new FileInputStream(secondPath);
            fos1 = new FileOutputStream(firstPath);
            byte[] buffer = new byte[1024];
            while (fis2.available() > 0){
                int count = fis2.read(buffer);
                fos1.write(buffer, 0, count);
            }

            for (Integer byt: buffer1){
                fos1.write(byt);
            }

        } catch (Exception e){ e.printStackTrace();}
        finally {
            try{
                fis2.close();
                fos1.close();
            } catch (Exception e){}
        }

    }

}
