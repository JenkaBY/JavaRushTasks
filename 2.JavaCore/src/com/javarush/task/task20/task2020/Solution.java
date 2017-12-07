package com.javarush.task.task20.task2020;

import java.io.*;
import java.util.logging.Logger;

/* 
Сериализация человека
*/
public class Solution {

    public static class Person implements Serializable {
        String firstName;
        String lastName;
        transient String fullName;
        transient final String greetingString = "Hello, ";
        ;
        String country;
        Sex sex;
        transient PrintStream outputStream;
        transient Logger logger;

        public Person() {
        }

        Person(String firstName, String lastName, String country, Sex sex) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.country = country;
            this.sex = sex;
            this.outputStream = System.out;
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }

        private void writeObject(ObjectOutputStream outputStream) throws IOException {
            outputStream.defaultWriteObject();
//            outputStream.writeObject(sex.name());
//            outputStream.writeObject(greetingString);
        }

        private void readObject(ObjectInputStream inputStream) throws IOException, ClassNotFoundException {
            inputStream.defaultReadObject();
//            this.sex = Sex.valueOf((String)inputStream.readObject());
//            this.sex = Sex.valueOf((String)inputStream.readObject());
            this.outputStream = System.out;
            this.fullName = String.format("%s, %s", lastName, firstName);
            this.logger = Logger.getLogger(String.valueOf(Person.class));
        }
    }

    enum Sex implements Serializable {
        MALE,
        FEMALE
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutput = new FileOutputStream("c:/work/out.txt");
        ObjectOutputStream outputStream = new ObjectOutputStream(fileOutput);

        Person solution = new Person("Max", "Baskov", "USA", Sex.FEMALE);
        outputStream.writeObject(solution);

        fileOutput.close();
        outputStream.close();

        //loading
        FileInputStream fiStream = new FileInputStream("c:/work/out.txt");
        ObjectInputStream objectStream = new ObjectInputStream(fiStream);

        Person loadedObject = (Person) objectStream.readObject();

        fiStream.close();
        objectStream.close();

    }
}
