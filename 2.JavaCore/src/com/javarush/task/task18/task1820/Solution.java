package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        String firstPath = "";
        String secondPath = "";
        try(BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))){
            firstPath = consoleReader.readLine();
            secondPath = consoleReader.readLine();
        } catch(Exception e){
        }

        try(BufferedReader br = new BufferedReader(new FileReader(firstPath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(secondPath))){
            String sCurrentLine;
            ArrayList<Integer> numbers = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                List<Integer> rounded = Arrays.asList(sCurrentLine.split(" ")).stream().map(s -> Math.round(Float.valueOf(s))).collect(Collectors.toList());
                numbers.addAll(rounded);
            }
            bw.write(String.join(" ", numbers.stream().map(num -> String.valueOf(num)).collect(Collectors.toList())));
        } catch (Exception e){

        }
    }
}
