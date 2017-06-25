package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String firstFileName = br.readLine();
            String secondFileName = br.readLine();

            BufferedReader readerFirstFile = new BufferedReader(new FileReader(firstFileName));
            String line;
            BufferedWriter secondFileWrier = new BufferedWriter(new FileWriter(secondFileName));
            while ((line = readerFirstFile.readLine()) != null) {
                String[] splittedLine = line.split(" ");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String word : splittedLine) {
                    try {
                        Integer number = Integer.parseInt(word);
                        numbers.add(number);
                    } catch (RuntimeException re) {
//                        re.printStackTrace();
                    }
                }
                if (!numbers.isEmpty()) {
                    secondFileWrier.write(numbers.stream().
                            map(Object::toString).
                            collect(Collectors.joining(" ")).toString());
                }
            }
            readerFirstFile.close();
            secondFileWrier.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
