package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String pathToFile = consoleReader.readLine();
        consoleReader.close();
        Map<Integer, Integer> counterByte = new HashMap<Integer, Integer>();
        FileInputStream file = new FileInputStream(pathToFile);

        int temp;
        while (file.available() > 0) {
            temp = file.read();
            if (counterByte.containsKey(temp)) {
                counterByte.put(temp, counterByte.get(temp) + 1);
            } else {
                counterByte.put(temp, 1);
            }
        }
        file.close();

        counterByte = sortByValue(counterByte);
        int maxValue = -1;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : counterByte.entrySet()) {
            if (maxValue <= entry.getValue()) {
                maxValue = entry.getValue();
                sb.append(entry.getKey()).append(" ");
            } else break;
        }
        if (sb.length() > 0) System.out.print(sb.toString().trim());
    }

    public static <K, V extends Comparable<? super V>> Map<K, V>
    sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list =
                new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
