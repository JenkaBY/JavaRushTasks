package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {
        PrintStream systmOutConsole = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(outputStream);
        System.setOut(newPrintStream);

        testString.printSomething();

        List<String> result = Arrays.asList(outputStream.toString().split(" "));

        calculate(result);

        String output = result.stream().
                map(Object::toString).
                collect(Collectors.joining(" "));

        System.setOut(systmOutConsole);

        System.out.println(output);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }

    public static void calculate(List<String> expression){
        String operator = expression.get(1);
        switch (operator){
            case "+":
            {
                Integer result = parseString(expression.get(0)) + parseString(expression.get(2));
                expression.set(4, String.valueOf(result));
                break;
            }
            case "-":
            {
                Integer result = parseString(expression.get(0)) - parseString(expression.get(2));
                expression.set(4, String.valueOf(result));
                break;
            }
            case "*":
            {
                Integer result = parseString(expression.get(0)) * parseString(expression.get(2));
                expression.set(4, String.valueOf(result));
                break;
            }
        }

    }

    public static Integer parseString(String number){
        return Integer.parseInt(number);
    }
}

