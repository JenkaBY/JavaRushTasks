package com.javarush.task.task17.task1721;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) {
        Solution files = new Solution();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstPathToFile;
        String secondPathToFile;

        try {
            firstPathToFile = reader.readLine();
            secondPathToFile = reader.readLine();
            reader.close();

            BufferedReader readerFirstFile = new BufferedReader(new FileReader(firstPathToFile));
            BufferedReader readerSecondFile = new BufferedReader(new FileReader(secondPathToFile));

            String lineFromFile = readerFirstFile.readLine();
            while (lineFromFile != null) {
                allLines.add(lineFromFile);
                lineFromFile = readerFirstFile.readLine();
            }
            readerFirstFile.close();

            lineFromFile = readerSecondFile.readLine();
            while (lineFromFile != null) {
                forRemoveLines.add(lineFromFile);
                lineFromFile = readerSecondFile.readLine();
            }
            readerSecondFile.close();

            files.joinData();
        } catch (CorruptedDataException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinData() throws CorruptedDataException {
        if (allLines.containsAll(forRemoveLines)) {
            allLines.removeAll(forRemoveLines);
        } else {
            allLines.clear();
            throw new CorruptedDataException();
        }
    }
}
