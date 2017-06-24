package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String in = reader.readLine();
            String out1 = reader.readLine();
            String out2 = reader.readLine();
            reader.close();

            FileInputStream fis = new FileInputStream(in);
            FileOutputStream fos2 = new FileOutputStream(out1);
            FileOutputStream fos3 = new FileOutputStream(out2);

            byte[] bufferFile1;
            byte[] bufferFile2;
            byte[] bufferFile3;

            while (fis.available() > 0) {
                int sizeFile1 = fis.available();

                bufferFile2 = new byte[sizeFile1 / 2 + sizeFile1 % 2];
                bufferFile3 = new byte[sizeFile1 / 2];
                fis.read(bufferFile2, 0, bufferFile2.length);
                fis.read(bufferFile3, 0, bufferFile3.length);
                fos2.write(bufferFile2);
                fos3.write(bufferFile3);
            }


            fis.close();
            fos2.close();
            fos3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
