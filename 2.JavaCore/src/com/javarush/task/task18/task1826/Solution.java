package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Solution {
    public static void main(String[] args) {
        String encodeDecode = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(fileName);
            fos = new FileOutputStream(fileOutputName);

            if (encodeDecode.equals("-e")) {
                encode(fis, fos);
            }

            if (encodeDecode.equals("-d")) {
                decode(fis, fos);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                fis.close();
                fos.close();
            } catch (Exception e){}
        }
    }

    public static void encode(FileInputStream fis, FileOutputStream fos) throws Exception {
        while (fis.available() > 0){
            fos.write(fis.read() + 1);
        }
    }

    public static void decode(FileInputStream fis, FileOutputStream fos) throws Exception {
        while (fis.available() > 0){
            fos.write(fis.read() - 1);
        }
    }
}
