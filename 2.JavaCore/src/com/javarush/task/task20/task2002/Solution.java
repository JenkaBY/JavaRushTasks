package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or fix outputStream/inputStream according to your real file location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("c:/work/out.txt", null);
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

            JavaRush javaRush = new JavaRush();
            //initialize users field for the javaRush object here - инициализируйте поле users для объекта javaRush тут
            User user = new User();
            user.setFirstName("First name1");
            user.setLastName("Last name");
            user.setBirthDate(new Date());
            user.setCountry(User.Country.UKRAINE);
            user.setMale(true);
            javaRush.users.add(user);
            User user2 = new User();
            user2.setFirstName("First name2");
            user2.setLastName("Last name2");
            user2.setBirthDate(new Date());
            user2.setCountry(User.Country.RUSSIA);
            user2.setMale(false);
            javaRush.users.add(user2);
            javaRush.save(outputStream);
            outputStream.flush();

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);
            //check here that javaRush object equals to loadedObject object - проверьте тут, что javaRush и loadedObject равны
            System.out.println(javaRush.equals(loadedObject));
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            //implement this method - реализуйте этот метод
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.println("count=" + users.size());
            AtomicInteger count = new AtomicInteger();
            users.forEach((User user) -> {
                printWriter.println(count.getAndDecrement() +
                        ":" + user.getFirstName() +
                        ":" + user.getLastName() +
                        ":" + user.getBirthDate().getTime() +
                        ":" + user.getCountry().ordinal() +
                        ":" + user.isMale());
            });
            printWriter.flush();
        }

        public void load(InputStream inputStream) throws Exception {
            //implement this method - реализуйте этот метод
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            int count = Integer.parseInt(reader.readLine().replaceAll("count=", ""));
            if (count == 0) {
                return;
            }
            String parsedLine;
            while ((parsedLine = reader.readLine()) != null) {
                String[] parsedUser = parsedLine.split(":");
                User user = new User();
                user.setFirstName(parsedUser[1]);
                user.setLastName(parsedUser[2]);
                user.setBirthDate(new Date(Long.parseLong(parsedUser[3])));
                user.setCountry(User.Country.values()[Integer.parseInt(parsedUser[4])]);
                user.setMale(Boolean.parseBoolean(parsedUser[5]));
                users.add(user);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }
    }
}
