package com.javarush.task.task19.task1924;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        String delimiter = " ";
        Pattern pattern = Pattern.compile("\\d+");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader inputFile = new BufferedReader(new FileReader(br.readLine()))) {
            inputFile.lines()
                    .map(line -> line.split(delimiter))
                    .map(Arrays::asList)
                    .map(words ->
                            words.stream()
                                    .map(word -> {
                                                if (pattern.matcher(word).matches()) {
                                                    final Integer number = Integer.valueOf(word);
                                                    return map.getOrDefault(number, word);
                                                }
                                                return word;
                                            }
                                    )
                    )
                    .map(words -> words.collect(Collectors.joining(delimiter)))
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}