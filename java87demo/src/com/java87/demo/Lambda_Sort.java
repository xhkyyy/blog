package com.java87.demo;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by WY on 2015/10/20.
 */
public class Lambda_Sort {


    public static void main(String[] args) {

       String[] fruits = new String[]{"apple", "orange", "pear", "ab"};

        //Arrays.sort(fruits, (String first, String second) -> Integer.compare(first.length(), second.length()));
        Arrays.sort(fruits, (first, second) -> Integer.compare(first.length(), second.length()));

        Arrays.asList(fruits).forEach(System.out::println);

    }

}
