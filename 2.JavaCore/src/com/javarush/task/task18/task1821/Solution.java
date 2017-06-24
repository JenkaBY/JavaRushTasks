package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.util.Map;
import java.util.TreeMap;

public class Solution {

    public static void main(String[] args) {
        Map<Character,Integer> map = new TreeMap<>();
        try(FileInputStream fis = new FileInputStream(args[0])){
            byte[] buffer = new byte[1024];
            while (fis.available() > 0){
                int count = fis.read(buffer);
                for (int i = 0; i < count; i++) {
                    Character ch = Character.valueOf((char) buffer[i]);
                    if (map.containsKey(ch)){
                       map.put(ch, map.get(ch) + 1);
                    } else {
                        map.put(ch, 1);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        map.forEach((k,value) -> System.out.println(k + " " + value));
    }


}
