package com.javarush.task.task17.task1710;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD
*/

public class Solution {
    public static List<Person> allPeople = new ArrayList<Person>();
    public static final String CREATE   = "-c";
    public static final String UPDATE   = "-u";
    public static final String DELETE   = "-d";
    public static final String INFO     = "-i";

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут
        String name, sex, birthday;
        int id;
        String inputDatePattern = "dd/MM/yyyy";
        String outputDatePattern = "dd-MMM-yyyy";
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputDatePattern, Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputDatePattern, Locale.ENGLISH);

        if (args.length > 0) {
            switch (args[0]) {
                case CREATE: {
//                  -c name sex bd
                    if (args.length == 4) {
                        name = args[1];
                        sex = args[2];
                        birthday = args[3];
                        try {
                            if (sex.compareToIgnoreCase("м") == 0) {
                                allPeople.add(Person.createMale(name, inputDateFormat.parse(birthday)));
                            }
                            if (sex.compareToIgnoreCase("ж") == 0) {
                                allPeople.add(Person.createFemale(name, inputDateFormat.parse(birthday)));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        System.out.println(allPeople.size() - 1);
                    }
                    break;
                }
                case UPDATE: {
//                  -u id name sex bd
                    if (args.length == 5) {
                        id = Integer.parseInt(args[1]);
                        name = args[2];
                        sex = args[3];
                        birthday = args[4];
                        try {
                            if (sex.compareToIgnoreCase("м") == 0) {
                                allPeople.set(id, Person.createMale(name, inputDateFormat.parse(birthday)));
                            }
                            if (sex.compareToIgnoreCase("ж") == 0) {
                                allPeople.set(id,Person.createFemale(name, inputDateFormat.parse(birthday)));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                }
                case DELETE: {
                    if (args.length == 2){
                        id = Integer.parseInt(args[1]);
                        if (allPeople.size() > id){
                            Person deletedPerson = allPeople.get(id);
                            deletedPerson.setBirthDay(null);
                            deletedPerson.setName(null);
                            deletedPerson.setSex(null);
                            allPeople.set(id, deletedPerson);
                        }
                    }
                    break;
                }
                case INFO: {
                    if (args.length == 2){
                        id = Integer.parseInt(args[1]);
                        StringBuilder personInfo = new StringBuilder();
                        if (allPeople.size() > id){
//                            Миронов м 15-Apr-1990
                            Person person = allPeople.get(id);
                            personInfo.append(person.getName());
                            personInfo.append(" ");

                            if (person.getSex()==Sex.MALE){
                                personInfo.append("м");
                            } else if (person.getSex()==Sex.FEMALE){
                                personInfo.append("ж");
                            } else personInfo.append("null");

                            personInfo.append(" ");

                            personInfo.append(outputDateFormat.format(person.getBirthDay()));
                        }
                        System.out.println(personInfo.toString());
                    }
                    break;
                }
            }
        }
    }

}
