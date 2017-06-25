package com.javarush.task.task19.task1915;

/* 
Дублируем текст
*/

import java.io.*;
import java.util.Arrays;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        String fileName = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))){
            fileName = br.readLine();
        } catch (Exception x){x.printStackTrace();}

        PrintStream systmOutConsole = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(outputStream);
        System.setOut(newPrintStream);

        testString.printSomething();

        String output = outputStream.toString();

        System.setOut(systmOutConsole);

        try(FileOutputStream bufferedWriter = new FileOutputStream(fileName)){

            bufferedWriter.write(outputStream.toByteArray());
            System.out.println(output);
        } catch (Exception e){e.printStackTrace();}
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("it's a text for testing");
        }
    }
}

