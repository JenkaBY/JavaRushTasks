package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader inputFile = new BufferedReader(new FileReader(br.readLine()));
        br.close();
        String tag = args[0];

        StringBuilder fileAsOneString = new StringBuilder();

        while (inputFile.ready()) {
            fileAsOneString.append(inputFile.readLine());
        }
        if (hasGivenTag(fileAsOneString.toString(), tag)) {
            outputTags(fileAsOneString.toString(), tag);
        }
        inputFile.close();
    }

    private static void outputTags(String currentString, String tag) {
        ArrayList<String> givenTags = parseStringByTag(currentString, tag);
        for (String s : givenTags) {
            System.out.println(s);
        }
    }

    private static ArrayList<String> parseStringByTag(String currentString, String tag) {

        currentString = currentString.replace("\r?\n", ""); //remove all line breaks

        ArrayList<Tag> allStartTags = new ArrayList<>();
        ArrayList<Tag> allEndTags = new ArrayList<>();
        ArrayList<String> tagsAsStrings = new ArrayList<>();

        String startTag = "<" + tag + ".*?>";
        String endTag = "</" + tag + ">";

        Pattern tagStart = Pattern.compile(startTag);//find only one occurence of nothing or something inside a tag at a time
        Pattern tagEnd = Pattern.compile(endTag);

        int startIndex = 1, endIndex = 1;

        Matcher matcher = tagStart.matcher(currentString);
        while (matcher.find()) {

            allStartTags.add(new Tag(startIndex, true, matcher.start()));
            startIndex++;
        }

        matcher = tagEnd.matcher(currentString);
        while (matcher.find()) {
            allEndTags.add(new Tag(endIndex, false, matcher.start()));
            endIndex++;
        }

        TreeMap<Integer, Integer> tagPairs = new TreeMap<>();
        int currentStartIndex = 0;
        int nextStartPositionDifference;

        //Находит пары индексов, соответствюущие друг другу
        for (int i = 0; i < allEndTags.size(); ) {
            nextStartPositionDifference = Integer.MAX_VALUE;
            if (currentStartIndex + 1 < allEndTags.size()) {
                nextStartPositionDifference = (allEndTags.get(i).position - allStartTags.get(currentStartIndex + 1).position);
                if (nextStartPositionDifference < 0) {
                    tagPairs.put(i, currentStartIndex);
                    i = ++currentStartIndex;
                } else {
                    currentStartIndex++;
                }
            } else {
                tagPairs.put(i, currentStartIndex);
                break;
            }
        }

        int startSubString, endSubString;

        for (Map.Entry<Integer, Integer> tagPair : tagPairs.entrySet()) {
            startSubString = allStartTags.get(tagPair.getKey()).getPosition();
            endSubString = allEndTags.get(tagPair.getValue()).getPosition() + endTag.length();
            tagsAsStrings.add(currentString.substring(startSubString, endSubString));
        }
        return tagsAsStrings;
    }

    private static boolean hasGivenTag(String s, String tag) {
        return s.contains("<" + tag) ? true : false;
    }

    static class Tag {
        int idx;
        boolean isOpen;
        int position;

        public Tag(int idx, boolean isOpen, int position) {
            this.idx = idx;
            this.isOpen = isOpen;
            this.position = position;
        }

        public int getIdx() {
            return idx;
        }

        public void setIdx(int idx) {
            this.idx = idx;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        public int getPosition() {
            return position;
        }

        public void setPosition(int position) {
            this.position = position;
        }
    }
}