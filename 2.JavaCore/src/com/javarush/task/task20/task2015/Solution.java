package com.javarush.task.task20.task2015;

import java.io.*;

/* 
Переопределение сериализации
*/
public class Solution implements Serializable, Runnable {
    private transient Thread runner;
    private int speed;

    public Solution(int speed) {
        this.speed = speed;
        runner = new Thread(this);
        runner.start();
    }

    public void run() {
        // do something here, does not matter
        System.out.println("Run");
    }

    /**
     * Переопределяем сериализацию.
     * Для этого необходимо объявить методы:
     * private void writeObject(ObjectOutputStream out) throws IOException
     * private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException
     * Теперь сериализация/десериализация пойдет по нашему сценарию :)
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        runner = new Thread(this);
        runner.start();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Solution origin = new Solution(10);
        File your_file_name = new File("c:/work/out.txt");
        OutputStream outputStream = new FileOutputStream(your_file_name);
        InputStream inputStream = new FileInputStream(your_file_name);

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(origin);
        oos.close();

//        Solution solution10 = new Solution(10);
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        Solution solution = (Solution) ois.readObject();
        ois.close();
    }
}
