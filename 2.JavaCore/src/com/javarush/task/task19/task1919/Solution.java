package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) {
        String file = args[0];
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.lines()
                    .map(Data::new)
                    .sorted((line1, line2) -> line1.getName().compareTo(line2.getName().toString()))
                    .collect(Collectors.toMap(
                            line -> line.getName(),
                            line -> line.getSalary(),
                            (salary1, salary2) -> salary1 + salary2,
                            LinkedHashMap::new
                            )
                    )
                    .forEach((key, value) -> System.out.println(key + " " + value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class Data {
        private String name;
        private double salary;

        public Data(String line) {
            name = line.split(" ")[0];
            salary = Double.valueOf(line.split(" ")[1]);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Data data = (Data) o;

            if (Double.compare(data.salary, salary) != 0) return false;
            return name != null ? name.equals(data.name) : data.name == null;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = name != null ? name.hashCode() : 0;
            temp = Double.doubleToLongBits(salary);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }

}
