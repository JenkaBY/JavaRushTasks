package com.javarush.task.task18.task1818;

/* 
Два в одном
*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        String firstPath = "";
        String secondPath = "";
        String thirdPath = "";
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            firstPath = consoleReader.readLine();
            secondPath = consoleReader.readLine();
            thirdPath = consoleReader.readLine();
        } catch(Exception e){
        }
        FileOutputStream fos1 = null;
        FileInputStream fis2 = null;
        FileInputStream fis3 = null;
        try {
            fos1 = new FileOutputStream(firstPath);
            fis2 = new FileInputStream(secondPath);
            fis3 = new FileInputStream(thirdPath);
            write(fis2, fos1);
            write(fis3, fos1);
        } catch (Exception e){ e.printStackTrace();}
        finally {
            try{
                fos1.close();
                fis2.close();
                fis3.close();
            } catch (Exception e){}
        }
    }

    public static void write(FileInputStream fis, FileOutputStream fos) throws Exception{
        byte[] buffer = new byte[1000];
        while (fis.available() > 0){
            int count = fis.read(buffer);
            fos.write(buffer, 0, count);
        }
    }
}
