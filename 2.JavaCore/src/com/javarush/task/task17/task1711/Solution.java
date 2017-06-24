package com.javarush.task.task17.task1711;

import com.javarush.task.task17.task1710.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();
    public static final String CREATE = "-c";
    public static final String UPDATE = "-u";
    public static final String DELETE = "-d";
    public static final String INFO   = "-i";

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
                    ArrayList<Integer> ids = new ArrayList();
                    for (int i = 1; i < args.length; ) {
                        name = args[i];
                        sex = args[i + 1];
                        birthday = args[i + 2];
                        synchronized (allPeople) {
                            try {
                                allPeople.add(createPerson(name, sex, inputDateFormat.parse(birthday)));
                                ids.add(allPeople.size() - 1);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        i += 3;
                    }
                    StringBuilder outputString = new StringBuilder();
                    for (Integer personId : ids) {
                        outputString.append(personId);
                        outputString.append(" ");
                    }
                    System.out.println(outputString.toString().trim());
                    break;
                }
                case UPDATE: {
//                  -u id name sex bd
                    for (int i = 1; i < args.length; ) {
                        id = Integer.parseInt(args[i]);
                        name = args[i + 1];
                        sex = args[i + 2];
                        birthday = args[i + 3];
                        synchronized (allPeople) {
                            try {
                                allPeople.set(id, createPerson(name, sex, inputDateFormat.parse(birthday)));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                        i += 4;
                    }
                    break;
                }
                case DELETE: {
                    for (int i = 1; i < args.length; i++) {
                        id = Integer.parseInt(args[i]);
                        synchronized (allPeople) {
                            if (allPeople.size() > id) {
                                Person deletedPerson = allPeople.get(id);
                                deletedPerson.setBirthDay(null);
                                deletedPerson.setName(null);
                                deletedPerson.setSex(null);
                                allPeople.set(id, deletedPerson);
                            }
                        }
                    }
                    break;
                }
                case INFO: {
                    for (int i = 1; i < args.length; i++) {
                        id = Integer.parseInt(args[i]);
                        StringBuilder personInfo = new StringBuilder();
                        Person person;
                        synchronized (allPeople) {
                            person = allPeople.get(id);
                        }
                        personInfo.append(person.getName());
                        personInfo.append(" ");

                        if (person.getSex() == Sex.MALE) {
                            personInfo.append("м");
                        } else if (person.getSex() == Sex.FEMALE) {
                            personInfo.append("ж");
                        } else personInfo.append("null");

                        personInfo.append(" ");

                        personInfo.append(outputDateFormat.format(person.getBirthDay()));
                        System.out.println(personInfo.toString());
                    }
                    break;
                }
            }
        }
    }

    public static Person createPerson(String name, String sex, Date birthday) {
        if (sex.compareToIgnoreCase("м") == 0) {
            return Person.createMale(name, birthday);
        }
        if (sex.compareToIgnoreCase("ж") == 0) {
            return Person.createFemale(name, birthday);
        }
        return null;
    }
}
