package com.javarush.task.task16.task1629;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    public static volatile BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws InterruptedException {
        Read3Strings t1 = new Read3Strings();
        Read3Strings t2 = new Read3Strings();

        //add your code here - добавьте код тут
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        t1.printResult();
        t2.printResult();
    }

    //add your code here - добавьте код тут
    public static class Read3Strings extends Thread{
        StringBuilder sb = new StringBuilder();
        AtomicInteger countReadStrings = new AtomicInteger(0);

        public void run(){
            try {
                while (countReadStrings.get() != 3) {
                    if (reader.ready()) {
                        sb.append(reader.readLine());
                        if (countReadStrings.incrementAndGet() != 3) {
                            sb.append(" ");
                        }
                    }
                }
            } catch (IOException e) {
            }
        }
        public void printResult(){
            System.out.println(sb.toString());
        }
    }
}
