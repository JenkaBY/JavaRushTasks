package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        First first = new First();
        Second second = new Second();
        Third third = new Third();
        Fourth fourth = new Fourth();
        Fifth fifth = new Fifth();

        threads.add(first);
        threads.add(second);
        threads.add(third);
        threads.add(fourth);
        threads.add(fifth);
    }

    public static void main(String[] args) {

    }


    public static class First extends Thread {
        public void run() {
            int i = Integer.MIN_VALUE;
            while (true) {
                i++;
            }
        }
    }

    public static class Second extends Thread {
        public void run() {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Third extends Thread {
        public void run() {
            try {
                while (true) {
                    System.out.println("Ура");
                    sleep(500);
                }
            } catch (InterruptedException e) {
            }
        }
    }

    public static class Fourth extends Thread implements Message {
        @Override
        public void showWarning() {
                interrupt();
        }

        @Override
        public void run() {
            try {
                while (isAlive()) {
                    join();
                }
            } catch (InterruptedException e){

            }
        }
    }

    public static class Fifth extends Thread {
        public void run() {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int sum = 0;
            String read;

            try {
                read = reader.readLine();
                while (read.compareTo("N") != 0) {
                    sum += Integer.parseInt(read);
                    read = reader.readLine();
                }
                System.out.println(sum);
            } catch (IOException e) {
            }

        }
    }
}