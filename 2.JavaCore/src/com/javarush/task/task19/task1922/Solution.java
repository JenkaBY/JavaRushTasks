package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) {
        String delimiter = " ";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader inputFile = new BufferedReader(new FileReader(br.readLine()))) {
            inputFile.lines()
                    .map(line -> Arrays.asList(line.split(delimiter)))
                    .filter(parsedWords -> parsedWords
                            .stream()
                            .filter(word -> words.contains(word))
                            .collect(Collectors.toList()).size() == 2
                    )
                    .map(words -> words
                            .stream()
                            .collect(Collectors.joining(delimiter))
                    )
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
