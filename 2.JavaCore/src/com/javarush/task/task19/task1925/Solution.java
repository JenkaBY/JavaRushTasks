package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
//        String file1 = "c:/work/test.txt";
//        String file2 = "c:/work/out.txt";
        String file1 = args[0];
        String file2 = args[1];
        final String delimiter = " ";
        final String comma = ",";
        try (BufferedReader inputFile = new BufferedReader(new FileReader(file1));
             FileWriter fileWriter = new FileWriter(file2)) {
            String output = inputFile.lines()
                    .map(line -> Arrays.asList(line.split(delimiter)))
                    .flatMap(Collection::stream)
                    .filter(word -> word.length() > 6)
                    .collect(Collectors.joining(comma));
            fileWriter.write(output);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
