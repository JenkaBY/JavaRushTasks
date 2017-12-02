package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        String file1 = args[0];
        String file2 = args[1];
        final String delimiter = " ";
        Pattern pattern = Pattern.compile("\\w*\\d+\\w*");
        try (BufferedReader inputFile = new BufferedReader(new FileReader(file1));
             FileWriter fileWriter = new FileWriter(file2)) {
            String output = inputFile.lines()
                    .map(line -> Arrays.asList(line.split(delimiter)))
                    .flatMap(Collection::stream)
                    .filter(word -> pattern.matcher(word).find())
                    .collect(Collectors.joining(delimiter));
            fileWriter.write(output);
            fileWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
