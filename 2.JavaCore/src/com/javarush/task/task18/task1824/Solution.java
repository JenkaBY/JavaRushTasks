package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        ArrayList<FileInputStream> readers = new ArrayList<>();
        try(BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in))){
            while ((fileName = readerFileName.readLine()) != null){
                FileInputStream fis = new FileInputStream(fileName);
                readers.add(fis);
                fis.getFD();
            }
        } catch (IOException e){
            System.out.println(fileName);
        } finally {
            try{
                for (FileInputStream fis: readers){
                    if (fis != null) fis.close();
                }
            } catch (IOException e){}
        }
    }

}
