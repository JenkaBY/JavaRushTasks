package com.javarush.task.task18.task1825;

/* 
Собираем файл
*/

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        String partFileName = "";
        String fileName = "";
        Map<Integer, FileInputStream> partsOfFile = new TreeMap<>();
        FileOutputStream fos = null;
        try(BufferedReader readerFileName = new BufferedReader(new InputStreamReader(System.in))){
            while (!(partFileName = readerFileName.readLine()).equals("end")){
                partsOfFile.put(getNumber(partFileName), new FileInputStream(partFileName));
                if (fileName.equals("")) fileName = getFileName(partFileName);
            }

            fos = new FileOutputStream(fileName);
            for (Map.Entry<Integer, FileInputStream> entry: partsOfFile.entrySet()){
                if (entry != null) {
                    write(entry.getValue(),fos);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                fos.close();
                for (Map.Entry<Integer, FileInputStream> entry: partsOfFile.entrySet()){
                    if (entry != null) entry.getValue().close();
                }
            } catch (IOException e){}
        }
    }

    public static Integer getNumber(String partFileName){
        String[] split = spltFileName(partFileName);
        return Integer.valueOf(split[2].replace("part",""));
    }

    public static String getFileName(String partFileName){
        String[] split = spltFileName(partFileName);
        return split[0] +  "." + split[1];
    }

    public static String[] spltFileName(String partFileName){
        return  partFileName.split("\\.");
    }

    public static void write(FileInputStream fis, FileOutputStream fos) throws Exception{
        byte[] buffer = new byte[1024];
        while (fis.available() > 0){
            int count = fis.read(buffer);
            fos.write(buffer, 0, count);
        }
    }
}
