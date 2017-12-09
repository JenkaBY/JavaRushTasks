package com.javarush.task.task20.task2025;

import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/*
Алгоритмы-числа
*/
public class Solution {

    public static long[] getNumbers(long N) {
        Permutation permutation = new Permutation();
        Set<Long> result = LongStream.range(0, N)
                .boxed()
                .filter(aLong -> {
                    if (permutation.contains(aLong)) {
                        return false;
                    }
                    permutation.permute(aLong);
                    return true;
                })
                .map(Solution::poweredNumber)
                .filter(Solution::isArmstrongNumber)
                .collect(Collectors.toSet());
        return result.stream().mapToLong(l -> l).sorted().toArray();
    }

    public static boolean isArmstrongNumber(Long number) {

        return number == poweredNumber(number);
    }

    public static long poweredNumber(Long number) {
        String numberStr = String.valueOf(number);
        final int power = numberStr.length();
        return Arrays.stream(numberStr.split(""))
                .mapToInt(Integer::valueOf)
                .reduce(0, (sum, num) -> sum += MathPower.power(num, power));
    }

    public static void main(String[] args) {
        long[] numbers = getNumbers(9800818);
        System.out.println(numbers);
        System.out.println(3 * 3 * 3 + 7 * 7 * 7 + 0 * 0 * 0);
        System.out.println(8 * 8 * 8 * 8 + 2 * 2 * 2 * 2 + 0 * 0 * 0 * 0 + 8 * 8 * 8 * 8);
//        Permutation perm = new Permutation();
//        perm.permute(1234L);
//        perm.printList();
    }

    static class Permutation {
        Set<Long> permutations;

        public Permutation() {
            permutations = new HashSet<>();
        }

        public boolean contains(Long number) {
            return permutations.contains(number);
        }

        public boolean contains(String number) {
            return contains(Long.valueOf(number));
        }

        private void permute(Long number) {
            if (contains(number)) {
                return;
            }
            permute(number.toString());
        }

        public void permute(String str) {
            permute("", str);
        }

        private void permute(String prefix, String str) {
            int n = str.length();
            if (n == 0) {
                permutations.add(Long.valueOf(prefix));
            } else {
                for (int i = 0; i < n; i++)
                    permute(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }

        public void printList() {
            permutations.forEach(System.out::println);
        }
    }

    static class MathPower {
        static Map<Pair<Integer, Integer>, Long> powers = new HashMap<>();

        public static long power(int number, int power) {
            Pair<Integer, Integer> pair = new Pair<>(number, power);
            powers.putIfAbsent(pair, (long) Math.pow(number, power));
            return powers.get(pair);
        }
    }
}
