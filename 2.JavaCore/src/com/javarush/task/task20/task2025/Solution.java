package com.javarush.task.task20.task2025;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {

        Object result1 = LongStream.range(0, N)
                .parallel()
                .boxed()
                .map(String::valueOf)
                .map((strNumber) -> strNumber.split(""))
                .map(Arrays::asList)
                .map((listNumbers) ->
                                listNumbers.stream()
                                        .map(Long::valueOf)
                                        .map(l -> {
//                            System.out.println(l);
                                            return l;
                                        })
                                        .collect(Collectors.toList())
//                                .collect(Collectors.toList())
                ).sorted().collect(Collectors.toList());

//                .sorted()
//                .mapToLong(l -> l).toArray();
//                .collect(Collectors.toList());
//        nums.stream().forEach((numbers) -> {
//            Arrays.asList(numbers);});
        long[] result = null;
        return result; //res.stream().mapToLong(l -> l).toArray();
    }

    public static void main(String[] args) {
        long[] numbers = getNumbers(99999999);
        System.out.println(numbers);
        System.out.println(3 * 3 * 3 + 7 * 7 * 7 + 0 * 0 * 0);
        System.out.println(8 * 8 * 8 * 8 + 2 * 2 * 2 * 2 + 0 * 0 * 0 * 0 + 8 * 8 * 8 * 8);
    }
}
