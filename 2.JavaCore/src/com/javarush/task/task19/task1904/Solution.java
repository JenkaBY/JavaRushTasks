package com.javarush.task.task19.task1904;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/* 
И еще один адаптер
*/

public class Solution {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new File("d:\\file.txt"))){
            PersonScannerAdapter adapter = new PersonScannerAdapter(scanner);
            System.out.println(adapter.read());
            System.out.println(adapter.read());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class PersonScannerAdapter implements PersonScanner{
        private Scanner fileScanner;

        public PersonScannerAdapter(Scanner fileScanner) {
            this.fileScanner = fileScanner;
        }

        @Override
        public Person read() throws IOException {
            String sPerson = fileScanner.nextLine();
            String firstName = sPerson.split(" ")[1];
            String middleName = sPerson.split(" ")[2];
            String lastName = sPerson.split(" ")[0];
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.valueOf(sPerson.split(" ")[5]),Integer.valueOf(sPerson.split(" ")[4])-1, Integer.valueOf(sPerson.split(" ")[3]));
            Date birthday = cal.getTime();
            Person person = new Person(firstName, middleName,lastName,birthday);
            return person;
        }

        @Override
        public void close() throws IOException {
            fileScanner.close();
        }
    }
}
