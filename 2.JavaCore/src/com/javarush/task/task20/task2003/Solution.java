package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() {
        //implement this method - реализуйте этот метод
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                FileInputStream fis = new FileInputStream(reader.readLine())) {
            load(fis);
        } catch (Exception e) {
        }
    }

    public void save(OutputStream outputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties propFromMap = new Properties();
        properties.forEach((key, value) -> propFromMap.setProperty(key, value));
        //propFromMap.putAll(properties); //вместо предидущей строки можно эту, по идее
        propFromMap.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        //implement this method - реализуйте этот метод
        Properties propFromIS = new Properties();
        propFromIS.load(inputStream);
        propFromIS.forEach((key, value) -> properties.put((String) key, (String) value));
    }

    public static void main(String[] args) {

    }
}
