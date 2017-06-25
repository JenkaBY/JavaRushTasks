package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        String file1 = "";
        String file2 = "";
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            file1 = br.readLine();
            file2 = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FileReader fr1;
        FileReader fr2;
        try {
            fr1 = new FileReader(file1);
            fr2 = new FileReader(file2);

            List<String> lines1 = readFile(fr1);
            List<String> lines2 = readFile(fr2);
            fr1.close();
            fr2.close();
            compareToList(lines1, lines2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        lines.forEach(System.out::println);
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }

//        @Override
//        public String toString() {
//            return type.name() + " " + line;
//        }
    }

    public static List<String> readFile(FileReader fr) throws IOException {
        BufferedReader br = new BufferedReader(fr);
        List<String> lines = br.lines().collect(Collectors.toList());
        return lines;
    }

    public static void compareToList(List<String> lines1, List<String> lines2) {
        int index1file = 0;
        while (lines1.size() > 0){
            String first = lines1.get(index1file);
            String second = lines2.get(index1file);
            if (first.compareTo(second) == 0) {
                lines.add(new LineItem(Type.SAME, first));
                lines1.remove(index1file);
                lines2.remove(index1file);
            } else {
                if (lines2.size() > index1file + 1) {
                    if (first.compareTo(lines2.get(index1file + 1)) == 0){
                        lines.add(new LineItem(Type.ADDED, second));
                        lines2.remove(index1file);
                    } else {
                        lines.add(new LineItem(Type.REMOVED, first));
                        lines1.remove(index1file);
                    }
                } else {
                    lines.add(new LineItem(Type.REMOVED, first));
                    lines1.remove(index1file);
                }

            }

        }
        if (lines1.size() == 1) {
            lines.add(new LineItem(Type.REMOVED, lines1.get(0)));
            lines1.remove(0);
        } else if (lines2.size() == 1) {
            lines.add(new LineItem(Type.ADDED, lines2.get(0)));
            lines2.remove(0);
        }
    }
}
