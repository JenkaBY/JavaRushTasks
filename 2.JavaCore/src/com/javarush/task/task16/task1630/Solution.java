package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            firstFileName = bf.readLine();
            secondFileName = bf.readLine();
        }
        catch (IOException e){}
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements Runnable, ReadFileInterface {
        StringBuilder sb = new StringBuilder();
        BufferedReader readerFile;

        @Override
        public void setFileName(String fullFileName) {
            try {
                readerFile = new BufferedReader(new FileReader(fullFileName));
            } catch (FileNotFoundException e) {
            }
        }

        @Override
        public String getFileContent() {

            return sb.toString();
        }


        @Override
        public void start() {
            run();
        }

        @Override
        public void run() {
            try {
                String lineFromFile = readerFile.readLine();
                while (lineFromFile != null) {
                    sb.append(lineFromFile);
                    sb.append(" ");
                    lineFromFile = readerFile.readLine();
                }
                readerFile.close();
            } catch (IOException e) {
            }
        }
    }
}
