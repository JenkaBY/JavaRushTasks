package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution origin = new Solution(4);

        File your_file_name = new File("c:/work/out.txt");
        OutputStream outputStream = new FileOutputStream(your_file_name);
        InputStream inputStream = new FileInputStream(your_file_name);

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(origin);
        oos.close();

        Solution solution10 = new Solution(10);
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        Solution solution = (Solution) ois.readObject();
        ois.close();

        System.out.println("Instance reference check : " + origin);
        System.out.println("Instance reference check : " + solution);
        System.out.println("Instance reference check : " + solution10);
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
