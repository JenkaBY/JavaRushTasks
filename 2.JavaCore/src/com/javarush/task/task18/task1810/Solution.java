package com.javarush.task.task18.task1810;

/* 
DownloadException
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws DownloadException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {

            String in = reader.readLine();
            FileInputStream fis = new FileInputStream(in);
            while (fis.available()>0){
                if (fis.available()<1000){
                    reader.close();
                    fis.close();
                    throw new DownloadException();
                }
                fis.close();
                fis = new FileInputStream(reader.readLine());
            }
            reader.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class DownloadException extends Exception {

    }
}
