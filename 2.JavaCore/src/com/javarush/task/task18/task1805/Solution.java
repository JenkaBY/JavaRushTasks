package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String pathToFile = consoleReader.readLine();
        consoleReader.close();
        Set<Integer> byteCodes = new TreeSet<Integer>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        FileInputStream file = new FileInputStream(pathToFile);

        while (file.available() > 0) {
            byteCodes.add(file.read());
        }
        file.close();
        StringBuilder sb = new StringBuilder();
        for (Integer i: byteCodes){
            sb.append(i).append(" ");
        }
        if (sb.length() > 0) System.out.print(sb.toString().trim());
    }
}
