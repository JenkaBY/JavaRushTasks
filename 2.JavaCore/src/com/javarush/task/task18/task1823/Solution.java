package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) {

        String fileName = "";
        try(BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in))){
            while (!(fileName = readerFileName.readLine()).equals("exit")){
                resultMap.put(fileName,0);
            }
        } catch (Exception e){
        }

        resultMap.forEach((name, countChar) -> { ReadThread thread1 = new ReadThread(name);
                                                thread1.start();
                                                try {
                                                    thread1.join();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
        );
//        resultMap.forEach((k,v) -> System.out.println(k + " " + v));
    }

    public static class ReadThread extends Thread {
        String fileName;
        public ReadThread(String fileName) {
            //implement constructor body
            this.fileName = fileName;
        }
        // implement file reading here - реализуйте чтение из файла тут
        public void run(){
            Map<Character,Integer> map = new HashMap<>();

            try(FileInputStream fis = new FileInputStream(fileName)){
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
                ArrayList<Character> characters = new ArrayList<>();
                Map<Character, Integer> sortedMap = sortByValue(map);
                sortedMap.forEach((k,v) -> {characters.add(k);
//                    System.out.println(fileName + ":" + k.toString() + " = " + v.toString());
                });
                resultMap.replace(fileName, (int) characters.get(characters.size()-1).charValue());

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            return map.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(/*Collections.reverseOrder()*/))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));
        }
    }
}
