package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<>();

    public static void main(String[] args) {
        String file = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<Person> people = reader.lines()
                    .filter(str -> !str.isEmpty())
                    .map(Solution::parse)
                    .collect(Collectors.toList());
            people.forEach(person -> PEOPLE.add(person));
//            printPeople();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printPeople() {
        PEOPLE.stream()
                .map(Solution::personToString)
                .forEach(System.out::println);
    }

    private static Person parse(String line) {
        DateResolver resolver = new DateResolver();
        List<String> names = new LinkedList<>();
        for (String splitted : line.split(" ")) {
            try {
                int parsed = Integer.parseInt(splitted);
                resolver.resolve(parsed);
            } catch (Exception e) {
                names.add(splitted);
            }
        }
        return new Person(names.stream().collect(Collectors.joining(" ")), resolver.getDate());
    }

    private static String personToString(Person person) {
        return String.format("%s %s", person.getName(), transformDate(person.getBirthday()));
    }

    private static String transformDate(Date date) {
        String pattern = "dd MM yyyy";
        DateFormat output = new SimpleDateFormat(pattern);
        return output.format(date);
    }

    static class DateResolver {
        private int day = -1;
        private int month = -1;
        private int year = -1;

        public void resolve(int number) {
            if (day == -1) {
                day = number;
                return;
            }
            if (month == -1) {
                month = number - 1;
                return;
            }
            if (year == -1) {
                year = number;
            }
        }

        private Date getDate() {
            Calendar calendar = new GregorianCalendar();
            calendar.set(year, month, day);
            return calendar.getTime();
        }
    }
}
