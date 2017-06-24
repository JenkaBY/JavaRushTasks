package com.javarush.task.task18.task1814;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* 
UnsupportedFileName
*/

public class TxtInputStream extends FileInputStream {
    FileInputStream fis;

    public TxtInputStream(String fileName) throws UnsupportedFileNameException, IOException {
        super(fileName);
        String sub = fileName.substring(fileName.length()-4);
        if (sub.compareTo(".txt") == 0){
            fis = new FileInputStream(fileName);
        } else {
            super.close();
            throw new UnsupportedFileNameException();
        }
    }

    public static void main(String[] args) {

    }
}

