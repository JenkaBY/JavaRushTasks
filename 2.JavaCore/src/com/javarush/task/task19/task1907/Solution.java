package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static void main(String[] args) {


        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            long count = 0;
            String pathToFile = br.readLine();
            FileReader fr = new FileReader(pathToFile);
            StringBuilder sb = new StringBuilder();
            while(fr.ready()){
                sb.append((char) fr.read());
            }

//            BufferedReader bufferedReader = new BufferedReader(fr);
            String line = sb.toString().replaceAll("(\\r|\\n|\\r\\n)+",".");
            count = Arrays.asList(line.split("\\W")).stream().filter(word -> ((String) word).compareToIgnoreCase("world") == 0).count();
//            while ((line = bufferedReader.readLine()) != null){
//                long temp = Arrays.asList(line.split("\\p{Punct}")).stream().filter(word -> ((String) word).compareTo("world") == 0).count();
//                count += temp;
//            }
            System.out.println(count);
//            bufferedReader.close();
            fr.close();
        } catch (Exception e){}

    }
}


