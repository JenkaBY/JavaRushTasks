package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) {
        String fileName = "";
        try(BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in))){
            fileName = readerFileName.readLine();
        } catch (Exception e){
        }

        try(BufferedReader fr = new BufferedReader(new FileReader(fileName))){
            String readLine = "";
            while (fr.ready()){
                readLine = fr.readLine();
                String[] splitted =  readLine.split(" ");
                if (splitted.length > 0 && args.length > 0){
                    if (splitted[0].compareTo(args[0]) == 0){
                        System.out.println(readLine);
                    }
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
