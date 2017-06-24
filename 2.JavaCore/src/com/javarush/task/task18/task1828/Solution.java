package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws Exception {
        File outputFile = getFile();
        FileReader fr = null;

        ArrayList<String> allStrings = readFile(outputFile);
        if (args.length > 0) {

            if (args[0].equals("-c")) {
                Integer lastId = getLastId(allStrings);
                allStrings.add(getFormattedString(args, lastId));
                writeToFile(allStrings, outputFile);
            }

            if (args[0].equals("-u")) {
                int id = Integer.valueOf(args[1]).intValue();
                int numberOfString = getNumberOfString(allStrings, id);
                allStrings.set(numberOfString, getFormattedString(new String[]{args[1], args[2], args[3], args[4]}, id-1));
                writeToFile(allStrings, outputFile);

            }

            if (args[0].equals("-d")) {
                int id = getNumberOfString(allStrings, Integer.valueOf(args[1]).intValue());
                allStrings.remove(id);
                writeToFile(allStrings, outputFile);

            }
        }
    }

    public static void writeToFile(ArrayList<String> allStrings, File outputFile) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(outputFile));
            for (String str : allStrings) {
                bw.write(str);
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> readFile(File outputFile) {
        FileReader fr = null;
        ArrayList<String> allStrings = new ArrayList<>();
        try {
            fr = new FileReader(outputFile);
            allStrings = getAllStrings(fr);
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStrings;
    }

    public static Integer getLastId(ArrayList<String> strings) throws IOException {
        ArrayList<Integer> ids = new ArrayList<>();
        strings.forEach(readLine -> {
            ids.add(Integer.valueOf(readLine.substring(0, 8).trim()));
        });
        Integer lastId;
        if (ids.size() > 0) {
            lastId = ids.stream().max(Integer::compareTo).get();
        } else lastId = 0;
        return lastId;
    }

    public static ArrayList<String> getAllStrings(FileReader fr) {
        ArrayList<String> allStrings = new ArrayList<>();
        String readLine = "";
        try (BufferedReader br = new BufferedReader(fr)) {
            while ((readLine = br.readLine()) != null) {
                allStrings.add(String.format("%s%n", readLine));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allStrings;
    }

    public static File getFile() {
        String fileName = "";
        try (BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in))) {
            fileName = readerFileName.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String getFormattedString(String[] args, Integer lastId) {
        String productName = args[1];
        if (productName.length() > 30) {
            productName = productName.substring(0, 30);
        }
        String price = args[2];
        if (price.length() > 8) {
            price = price.substring(0, 8);
        }
        String quantity = args[3];
        if (quantity.length() > 4) {
            quantity = quantity.substring(0, 4);
        }
        String result = String.format("%-8s%-30s%-8s%-4s%n", lastId + 1, productName, price, quantity);
        return result;
    }

    public static int getNumberOfString(ArrayList<String> allString, int id) {
        for (int i = 0; i < allString.size(); i++) {
            if (Integer.valueOf(allString.get(i).substring(0, 8).trim()).intValue() == id) {
                return i;
            }
        }
        return -1;
    }
}
