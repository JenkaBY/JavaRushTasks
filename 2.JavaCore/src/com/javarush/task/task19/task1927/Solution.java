package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static TestString testString = new TestString();
    public static final String CONTEXT = "JavaRush - курсы Java онлайн";

    public static void main(String[] args) {
        PrintStream systemOutConsole = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newPrintStream = new PrintStream(outputStream);
        System.setOut(newPrintStream);

        testString.printSomething();
        AtomicInteger index = new AtomicInteger(1);
        String output = Stream.of(outputStream.toString().split(System.lineSeparator()))
                .map((line) -> {
                            int count = index.getAndIncrement();
                            return count % 2 == 0 ? String.format("%s%s%s", line, System.lineSeparator(), CONTEXT) : line;
                        }
                )
                .collect(Collectors.joining(System.lineSeparator()));

        System.setOut(systemOutConsole);
        System.out.println(output);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
