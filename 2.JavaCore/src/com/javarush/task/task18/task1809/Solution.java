package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String in = reader.readLine();
            String out1 = reader.readLine();
            reader.close();

            FileInputStream fis = new FileInputStream(in);
            FileOutputStream fos2 = new FileOutputStream(out1);

            byte[] bufferFile1;

            while (fis.available() > 0) {
                int sizeFile1 = fis.available();

                bufferFile1 = new byte[sizeFile1];

                fis.read(bufferFile1);
                for (int i = bufferFile1.length-1; i >= 0 ; i--) {
                    fos2.write(bufferFile1[i]);
                }
            }

            fis.close();
            fos2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
