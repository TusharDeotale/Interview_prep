package com.interviewprep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {

//        Test.sumOfTwoMaxNum(9,2,1,8,8,9);

       /* List<Integer> numbers = Arrays.asList(1, 1, 2, 3, 3, 3, 4, 4, 5);

        // output - 2, 5, 1, 1, 4, 4, 3,3,3

        Map<Integer, Long> freqMap = numbers.stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting())
                );

        List<Integer> sorted = numbers.stream()
                .sorted(Comparator.comparingLong(freqMap::get))
                .toList();

        sorted.forEach(System.out::println);*/
    }

   /* private static void sumOfTwoMaxNum(int... arr){
        int first_highest = Integer.MIN_VALUE;
        int second_highest = Integer.MAX_VALUE;

        for (int j : arr) {
            if (j > first_highest) {
                second_highest = first_highest;
                first_highest = j;
            } else if (j > second_highest && j != first_highest) {
                second_highest = j;
            }
        }

        System.out.println(first_highest + second_highest);
    }*/

}
